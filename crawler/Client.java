package TextMining.crawler;

/**
 * ��Ѷ����������������
 * @author lyq
 *
 */
public class Client {
	public static void main(String[] args){
		//ÿ��������������
		int reqNum;
		//��������
		int totalCommentCount;
		//���۵����·��
		String outputPath;
		//��Ѷ����ҳurl����
		String newsUrl;
		QQNewsCrawler crawler;
		
		reqNum = 50;
		totalCommentCount = 100;
		newsUrl = "http://news.qq.com/a/20150508/004453.htm";
		outputPath = "C:\\Users\\lyq\\Desktop\\�ҵı�ҵ���\\newsComments2.txt";
		
		crawler = new QQNewsCrawler(newsUrl, totalCommentCount, reqNum, outputPath);
		crawler.crawlNewsComments();
	}
}
