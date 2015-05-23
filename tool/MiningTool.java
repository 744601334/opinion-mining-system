package TextMining.tool;

import static TextMining.tool.ConstantUtils.CHINESE_PUNCTUATION_COMMA;
import static TextMining.tool.ConstantUtils.CHINESE_PUNCTUATION_END;
import static TextMining.tool.ConstantUtils.CHINESE_PUNCTUATION_EXCLAMATORY;
import static TextMining.tool.ConstantUtils.CHINESE_PUNCTUATION_QUESTION;
import static TextMining.tool.ConstantUtils.CRAWLER_PER_REQ_NUM;
import static TextMining.tool.ConstantUtils.CRAWLER_TOTAL_REQ_NUM;
import static TextMining.tool.ConstantUtils.DICT_WORD_CLASS;
import static TextMining.tool.ConstantUtils.DICT_WORD_CONTENT;
import static TextMining.tool.ConstantUtils.DICT_WORD_STRONG;
import static TextMining.tool.ConstantUtils.HASH_NUM;
import static TextMining.tool.ConstantUtils.NEGATIVE_WORDS_LEVEL;
import static TextMining.tool.ConstantUtils.POSITIVE_WORDS_LEVEL;
import static TextMining.tool.ConstantUtils.isContained;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import TextMining.chart.BarChart;
import TextMining.chart.LineChart;
import TextMining.crawler.QQNewsCrawler;
import TextMining.crawler.entity.Comment;
import TextMining.entity.Sentence;
import TextMining.res.Resource;

/**
 * �ı��ھ��㷨������
 * 
 * @author lyq
 * 
 */
public class MiningTool {
	// �����ʿ��ļ�·��
	private String ewFilePath;
	// ������������·��
	private String existDataPath;
	// ÿ������������ı���
	private int sampleNum;
	// �����������
	private Random random;
	// ���۵����·��
	private String outputPath;
	// ��Ѷ����ҳurl����
	private String newsUrl;
	// ��ȡ�������ű���
	private String newsTitle;
	// ��Ѷ�������湤��
	private QQNewsCrawler crawler;
	// Ԥ������
	private PreTreatTool preTreatTool;
	// �ܵ��ı�����
	private ArrayList<Sentence> mainWords;
	// �����ʿ�������
	private ArrayList<String> emotionLines;
	// �����ʿ�����
	private ArrayList<String> emotionWords;
	// �ܵĳ�����ȡ�����۵ļ���
	private ArrayList<Double> totalLevels;
	// �����ʴ�Ƶͳ��ͼ
	private Map<String, Integer> eWordCount;
	// ʱ��۵㼫��ͼ
	private Map<String, ArrayList<Double>> eWordTimeLevel;
	// �۵���������ù�ϣ�㷨������������Ĺ۵㼫��
	private double[] eword2Level;

	public MiningTool(String ewFilePath, String existDataPath,
			String outputPath, String newsUrl) {
		this.ewFilePath = ewFilePath;
		this.existDataPath = existDataPath;
		this.outputPath = outputPath;
		this.newsUrl = newsUrl;

		this.random = new Random();
		this.crawler = new QQNewsCrawler(newsUrl, CRAWLER_TOTAL_REQ_NUM,
				CRAWLER_PER_REQ_NUM, outputPath);

		initEWord2Level();
		initCommemtDatas();
	}

	/**
	 * ��ȡ������
	 * 
	 * @return
	 */
	public QQNewsCrawler getCrawler() {
		return crawler;
	}

	/**
	 * ��ȡ�۵��Ƶͳ��ͼ
	 * 
	 * @return
	 */
	public Map<String, Integer> geteWordCount() {
		return eWordCount;
	}

	/**
	 * ��ȡ���ű���
	 * 
	 * @return
	 */
	public String getNewsTitle() {
		return newsTitle;
	}

	/**
	 * ��ȡ�ܵĹ۵㼫��
	 * 
	 * @return
	 */
	public ArrayList<Double> getTotalLevels() {
		return totalLevels;
	}

	/**
	 * ��ȡʱ��۵㼫��ͼ����
	 * 
	 * @return
	 */
	public Map<String, ArrayList<Double>> geteWordTimeLevel() {
		return eWordTimeLevel;
	}

	/**
	 * ���ļ��ж�ȡ����
	 * 
	 * @param filePath
	 *            �����ļ�
	 */
	private ArrayList<String> readDataFile(String filePath) {
		File file = new File(filePath);
		ArrayList<String> sentences = new ArrayList<>();

		try {
			BufferedReader in = new BufferedReader(new FileReader(file));
			String str;
			while ((str = in.readLine()) != null) {
				sentences.add(str);
			}
			in.close();
		} catch (IOException e) {
			e.getStackTrace();
		}

		return sentences;
	}

	/**
	 * ��ʼ�������ʼ��Թ�ϣ����
	 */
	private void initEWord2Level() {
		double level = 0;
		long pos = 0;
		String[] array;

		emotionWords = new ArrayList<>();
		eword2Level = new double[ConstantUtils.HASH_NUM];

		//�ж��Ƿ���ϵͳ����ֵ�����
		if(ewFilePath == null || ewFilePath.equals("")){
			//û�еĻ�������ϵͳ�Դ����ֵ��
			emotionLines = new Resource().getResource();
		}else{
			//�����ļ��м���
			emotionLines = readDataFile(ewFilePath);
		}
		emotionLines.remove(0);

		for (String str : emotionLines) {
			array = str.split(ConstantUtils.PUNCTUATION_TAB);

			pos = HashTool.BKDRHash(array[DICT_WORD_CONTENT]);
			pos %= ConstantUtils.HASH_NUM;

			level = calWordLevel(array[DICT_WORD_CLASS],
					array[DICT_WORD_STRONG]);

			// �����ϣ������
			eword2Level[(int) pos] = level;
			// ���׸������ʼ����б���
			emotionWords.add(array[0]);
		}
	}

	// ��������������С���ݼ��Ĳ���
	private void initCommemtDatas() {
		String reg;
		String[] array;
		Sentence sentence;
		long time;
		String s;

		// �����б�����
		ArrayList<Comment> commentList;
		ArrayList<String> commentLines;
		ArrayList<String> words;

		this.mainWords = new ArrayList<>();
		this.preTreatTool = new PreTreatTool();

		// ��װ���Ӳ������ƥ���ַ�
		reg = CHINESE_PUNCTUATION_END + "|" + CHINESE_PUNCTUATION_COMMA + "|"
				+ CHINESE_PUNCTUATION_EXCLAMATORY + "|"
				+ CHINESE_PUNCTUATION_QUESTION;

		// ���������������δ�գ���˵������ȡ���ݵķ�ʽ
		if (existDataPath == null || existDataPath.equals("")) {
			// ��������������ȡ
			crawler.crawlNewsComments();
			commentList = crawler.getCommentLists();
			newsTitle = crawler.getNewsTitle();
			System.out.println("�ɹ�ץȡ����������");
			System.out.println("��ʼ���й۵����ݷ���");

			for (Comment c : commentList) {
				time = c.getTime();
				s = c.getContent();

				// ����Ԥ�������
				s = preTreatTool.preTreatWords(s);
				array = s.split(reg);

				words = new ArrayList<>();
				for (String w : array) {
					words.add(w);
				}

				sentence = new Sentence(words, time);
				mainWords.add(sentence);
			}
		} else {
			// ���ļ��ж�ȡ���۵ķ�ʽ
			commentLines = readDataFile(existDataPath);

			for (String c : commentLines) {
				s = c;

				// ����Ԥ�������
				s = preTreatTool.preTreatWords(s);
				array = s.split(reg);

				words = new ArrayList<>();
				for (String w : array) {
					words.add(w);
				}

				sentence = new Sentence(words, 0);
				mainWords.add(sentence);
			}
		}
	}

	/**
	 * ���������ʵķ����ǿ�ȣ��������ļ���
	 * 
	 * @param classType
	 * @param strong
	 * @return
	 */
	private double calWordLevel(String classType, String strong) {
		int index;
		int strongLevel;
		double resultLevel;
		boolean isPositive;

		index = -1;
		isPositive = false;
		strongLevel = Integer.parseInt(strong);

		for (int i = 0; i < POSITIVE_WORDS_LEVEL.length; i++) {
			if (POSITIVE_WORDS_LEVEL[i].equals(classType)) {
				index = i;
				isPositive = true;
				break;
			}
		}

		if (!isPositive) {
			for (int i = 0; i < NEGATIVE_WORDS_LEVEL.length; i++) {
				if (NEGATIVE_WORDS_LEVEL[i].equals(classType)) {
					index = i;
					isPositive = false;
					break;
				}
			}
		}

		resultLevel = index * 0.1 + strongLevel * 1.0 / 100;
		// ����һ���Ĵ���
		resultLevel /= (NEGATIVE_WORDS_LEVEL.length * 0.1);

		// ���ݴ������ʣ���ӷ���
		if (!isPositive) {
			resultLevel *= -1;
		}

		return resultLevel;
	}

	/**
	 * ���ݸ����ʻ�ȡ�ʵļ���
	 * 
	 * @param word
	 */
	private double getWordLevel(String word) {
		boolean isContained;
		double level;
		long pos;

		// Ĭ�ϴʵļ���������Ϊ0
		level = 0;

		// �����ֵ�ʱ���ɨ��
		for (String ew : emotionWords) {
			isContained = isContained(word, ew);

			if (isContained) {
				countEWord(ew);

				// ���ƥ�����ˣ�������ʶ�Ӧ�ļ��Դӹ�ϣ������ȡ��
				pos = HashTool.BKDRHash(ew);
				pos %= HASH_NUM;

				level += eword2Level[(int) pos];
			}
		}

		return level;
	}

	/**
	 * ͳ�������ʵļ�����
	 * 
	 * @param ew
	 *            ��ǰƥ�䵽�������
	 */
	private void countEWord(String ew) {
		int count;

		if (eWordCount.containsKey(ew)) {
			count = eWordCount.get(ew);
		} else {
			count = 0;
		}

		// ���м�����1�����´���ͼ��
		count++;
		eWordCount.put(ew, count);
	}

	/**
	 * ���й۵��ھ�Ĺ���
	 */
	public void opinionMining() {
		this.eWordCount = new HashMap<String, Integer>();
		this.eWordTimeLevel = new HashMap<>();

		// ͨ�������ķ�ʽ��ȡ���ֹ۵�ļ���
		totalLevels = getAllEWordLevels();
	}

	/**
	 * ��ȡ���������еĹ۵㼫�Դ���
	 * 
	 * @return
	 */
	private ArrayList<Double> getAllEWordLevels() {
		ArrayList<Double> totalLevels;
		ArrayList<String> childSentences;
		double sentenceLevel;
		String time;

		totalLevels = new ArrayList<>();
		for (Sentence sentence : mainWords) {
			sentenceLevel = 0;
			time = sentence.time + "";
			childSentences = sentence.childSentences;

			// ����ÿ�����ۣ������ִʵĴ���ʶ��
			for (String childWord : childSentences) {
				sentenceLevel += getWordLevel(childWord);
			}

			// �Գ�����Χ�ļ�������������
			if (sentenceLevel > 0.99) {
				sentenceLevel = 0.99;
			} else if (sentenceLevel < -0.99) {
				sentenceLevel = -0.99;
			}

			insertTimeLevelToMap(time, sentenceLevel);
			totalLevels.add(sentenceLevel);
		}

		return totalLevels;
	}

	/**
	 * �������ʱ�伫�Ե�ͼ��
	 * 
	 * @param time
	 * @param level
	 */
	private void insertTimeLevelToMap(String time, double level) {
		ArrayList<Double> levelList;

		if (eWordTimeLevel.containsKey(time)) {
			levelList = eWordTimeLevel.get(time);
		} else {
			levelList = new ArrayList<>();
		}

		// ���뵱ǰ��level�б���
		levelList.add(level);
		eWordTimeLevel.put(time, levelList);
	}

	/**
	 * ���ı���Ч���������ȡ��Ч�Ĺ۵㼫��
	 * 
	 * @return
	 */
	/*
	 * private ArrayList<Double> getRandomEWordLevels() { boolean keepRunning;
	 * int temp; // �۵㼫�� double level = 0; // �ϴβ�ȡ�����ֵ double avgLevel; double
	 * sumLevel = 0; double tempLevelSum; double tempAVgLevel; long pos; //
	 * ÿ�����۰����Ĵʸɹؼ��� ArrayList<String> tempWords; ArrayList<Integer>
	 * sampleRandomNum; // �ܵĳ�����ȡ�����۵ļ��� ArrayList<Double> totalLevels;
	 * 
	 * keepRunning = true; avgLevel = 0; sumLevel = 0; totalLevels = new
	 * ArrayList<>(); while (keepRunning) { tempLevelSum = 0; sampleRandomNum =
	 * new ArrayList<>(); for (int i = 0; i < sampleNum;) { temp =
	 * random.nextInt(mainWords.size());
	 * 
	 * if (!sampleRandomNum.contains(temp)) { sampleRandomNum.add(temp); i++; }
	 * }
	 * 
	 * for (int i : sampleRandomNum) { tempWords = mainWords.get(i); level = 0;
	 * // ���ڶ�ÿ�������е����δʵļ��� temp = 0;
	 * 
	 * // ȡ��������е�ÿ������ for (String w : tempWords) { // �ӹ�ϣ����ȡ��Ӧֵ pos =
	 * HashTool.BKDRHash(w); pos %= ConstantUtils.HASH_NUM;
	 * 
	 * if (eword2Level[(int) pos] != 0) { level += eword2Level[(int) pos];
	 * temp++; } }
	 * 
	 * if (level != 0) { level /= temp; totalLevels.add(level); tempLevelSum +=
	 * level; } }
	 * 
	 * sumLevel += tempLevelSum; tempAVgLevel = tempLevelSum /
	 * sampleRandomNum.size(); // ���ȡһ������ƽ��ֵ����Ϊ�������յ� avgLevel = sumLevel /
	 * totalLevels.size();
	 * 
	 * // �ոտ�ʼ�ĵ�һ�������Ϊû�вο�ֵ��ֱ������ if (sumLevel == tempLevelSum) { continue; }
	 * 
	 * // ��Ŀǰ�����вɼ�����ƽ�����Աȣ�С��һ������ֵ������ֹ���� if (Math.abs(avgLevel - tempAVgLevel) <
	 * 0.1) { // ��������Ե�ƽ��ֵ�Ѿ�С��������ֵ����������� keepRunning = false; } }
	 * 
	 * return totalLevels; }
	 */
}
