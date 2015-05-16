package TextMining.crawler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import TextMining.crawler.entity.Comment;
import TextMining.crawler.entity.Data;
import TextMining.crawler.entity.DataContainer;

import com.google.gson.Gson;

/**
 * ��Ѷ�������湤����
 * 
 * @author lyq
 * 
 */
public class QQNewsCrawler {
	// ��Ѷ������������url�ĸ�ʽ
	public static final String NEWS_COMMENTS_URL_FORMAT = "http://coral.qq.com/article/{0}/comment?commentid={1}&reqnum={2}&tag=&callback=mainComment&_=1389623278900";

	// ��Ѷ��������ҳ������
	private String newsUrl;
	//��ȡ�������ű���
	private String newsTitle;
	//�����������·��
	private String outputPath;
	// ��Ҫ��ȡ������������
	private int totalCommentcount;
	// ÿ���������������һ������50������
	private int reqCommentNum;
	// �����б�
	private ArrayList<Comment> commentLists;

	public QQNewsCrawler(String newUrl, int totalCommentcount, int reqCommentNum, String outputPath) {
		this.newsUrl = newUrl;
		this.totalCommentcount = totalCommentcount;
		this.outputPath = outputPath;
		
		if (reqCommentNum > 50) {
			// ÿ���������ֻ��50��
			reqCommentNum = 50;
		}
		this.reqCommentNum = reqCommentNum;
	}

	/**
	 * ��ȡ������������
	 * @return
	 */
	public ArrayList<Comment> getCommentLists() {
		return commentLists;
	}
	
	/**
	 * ��ȡ���ű���
	 * @return
	 */
	public String getNewsTitle(){
		return this.newsTitle;
	}

	/**
	 * ����������ҳ����ȡ���ű��������ID
	 * 
	 * @return
	 */
	public String[] crawlCmtIdAndTitle() {
		String[] array;
		String[] tempArray;
		// ҳ��HTML�ַ�
		String htmlStr;
		String cmtId;
		String newsTitle;
		String filePath = "C:\\Users\\lyq\\Desktop\\icon\\input2.txt";
		Pattern p;
		Matcher m;

		cmtId = null;
		newsTitle = null;
		array = new String[2];
		htmlStr = sendGet(newsUrl);
		// htmlStr = readDataFile(filePath);

		p = Pattern.compile("cmt_id = (.*);");
		m = p.matcher(htmlStr);

		while (m.find()) {
			cmtId = m.group();
			System.out.println(cmtId);
			break;
		}

		p = Pattern.compile("<title>(.*)</title>");
		m = p.matcher(htmlStr);

		while (m.find()) {
			newsTitle = m.group();
			System.out.println(newsTitle);
			break;
		}

		// ��ƥ�䵽������id�ַ�������
		if (cmtId != null && !cmtId.equals("")) {
			tempArray = cmtId.split(";");
			cmtId = tempArray[0];
			tempArray = cmtId.split("=");
			cmtId = tempArray[1].trim();
			System.out.println(cmtId);
		}

		int pos1;
		int pos2;
		// ��ƥ�䵽�����ű���������
		if (newsTitle != null && !newsTitle.equals("")) {
			pos1 = newsTitle.indexOf(">");
			pos2 = newsTitle.lastIndexOf("<");

			newsTitle = newsTitle.substring(pos1 + 1, pos2);
			System.out.println(newsTitle);
		}

		array[0] = cmtId;
		array[1] = newsTitle;
		this.newsTitle = newsTitle;
		
		return array;
	}

	/**
	 * ������������ID��ȡ��Ѷ������������
	 * 
	 * @throws
	 */
	public void crawlNewsComments() {
		String resultCommentStr;
		String requestUrl;
		String cmtId;
		String[] info;
		String startCommentId;
		int index1;
		int index2;
		// ��ǰ��ȡ����������
		int currentCommentNum;
		int sleepTime;
		Random random;

		startCommentId = "";
		currentCommentNum = 0;
		random = new Random();
		commentLists = new ArrayList<>();

		info = crawlCmtIdAndTitle();
		cmtId = info[0];
		// cmtId = "1004703995";

		// �����������ﵽҪ�����ʱ������ѭ��
		while (currentCommentNum < totalCommentcount) {
			requestUrl = MessageFormat.format(NEWS_COMMENTS_URL_FORMAT, cmtId,
					startCommentId, reqCommentNum);
			resultCommentStr = sendGet(requestUrl);

			// ��ȡ��json��ʽ����������
			index1 = resultCommentStr.indexOf("{");
			index2 = resultCommentStr.lastIndexOf("}");
			resultCommentStr = resultCommentStr.substring(index1, index2 + 1);

			System.out.println(resultCommentStr);
			// ���ϴ����һ�����۵�idΪ��ʼID��������ȡ����
			startCommentId = parseJSONData(resultCommentStr);

			// ������������쳣���������˳�
			if (startCommentId == null) {
				break;
			}

			try {
				// ���˯��1��5��
				sleepTime = random.nextInt(5) + 1;
				Thread.sleep(1000 * sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			currentCommentNum += reqCommentNum;
		}

		// ��󽫱�����ȡ����������д�뵽�ļ���
		writeStringToFile(commentLists, outputPath);
	}

	/**
	 * �����������ݵ�json��ʽ�ַ���
	 * 
	 * @param dataStr
	 *            json����
	 * @return ���ش˴λ�ȡ�����һ�����۵�id
	 */
	private String parseJSONData(String dataStr) {
		String lastId;
		Gson gson = new Gson();
		DataContainer dataContainer;
		Data data;
		ArrayList<Comment> cList;

		dataContainer = gson.fromJson(dataStr, DataContainer.class);
		// �����ȡ�����쳣���򷵻ؿ���
		if (dataContainer == null || dataContainer.getErrCode() != 0) {
			return null;
		}

		data = dataContainer.getData();
		//һ�������Ѿ�û��������,�򷵻�
		if (data == null) {
			return null;
		}

		cList = data.getCommentid();
		if(cList == null || cList.size() == 0){
			return null;
		}
		commentLists.addAll(cList);

		lastId = dataContainer.getData().getLast();

		return lastId;
	}

	/**
	 * ��ָ��URL����GET����������
	 * 
	 * @param url
	 *            ���������URL
	 * @return URL ������Զ����Դ����Ӧ���
	 */
	private String sendGet(String requestUrl) {
		String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(requestUrl);
			// �򿪺�URL֮�������
			URLConnection connection = realUrl.openConnection();
			// ����ͨ�õ���������
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// ����ʵ�ʵ�����
			connection.connect();

			// ���� BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			System.out.println("����GET��������쳣��" + e);
			e.printStackTrace();
		}
		// ʹ��finally�����ر�������
		finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * ���ļ��ж�ȡ����
	 */
	private String readDataFile(String filePath) {
		File file = new File(filePath);
		String resultStr = "";

		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String str;
			while ((str = in.readLine()) != null) {
				resultStr = resultStr + str;
			}
			in.close();
		} catch (IOException e) {
			e.getStackTrace();
		}

		return resultStr;
	}

	/**
	 * д���۵�Ŀ���ļ���
	 * 
	 * @param resultStr
	 */
	public void writeStringToFile(ArrayList<Comment> commentList,
			String desFilePath) {
		File file;
		PrintStream ps;

		try {
			file = new File(desFilePath);
			ps = new PrintStream(new FileOutputStream(file));

			for (Comment c : commentList) {
				ps.println(c.getTime() + ":" + c.getContent());// ���ļ���д���ַ���
			}

			ps.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
