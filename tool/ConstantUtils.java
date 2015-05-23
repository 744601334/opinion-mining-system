package TextMining.tool;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;

/**
 * ����������
 * 
 * @author lyq
 * 
 */
public class ConstantUtils {
	// �ھ�ϵͳ����
	public static final String MINING_SYSTEM_NAME = "���Ź۵������ھ�ϵͳ";

	// ���ĵľ�Ž����ַ�
	public static final String CHINESE_PUNCTUATION_END = "��";
	// ���ĵĶ����ַ�
	public static final String CHINESE_PUNCTUATION_COMMA = "��";
	// ���ĵĸ�̾���ַ�
	public static final String CHINESE_PUNCTUATION_EXCLAMATORY = "��";
	// ���ĵ��ʺ��ַ�
	public static final String CHINESE_PUNCTUATION_QUESTION = "��";
	// Ӣ��ľ�Ž����ַ�
	public static final String ENGLISH_PUNCTUATION_END = ".";
	// Ӣ�ĵĶ����ַ�
	public static final String ENGLISH_PUNCTUATION_COMMA = ",";
	// Ӣ���еķָ���
	public static final String PUNCTUATION_LINK = "-";
	// �Ʊ��
	public static final String PUNCTUATION_TAB = "\t";
	// ��ſո��
	public static final String PUNCTUATION_BLANK = " ";

	// Ԥ�����еĲ��ֹ��˴ʣ�Ŀǰ�����Ƚ���
	public static final String[] FILTERED_WORDS = new String[] { "��", "-" };

	// ͳ���������
	public static final String CHART_PIE = "�����۵��״ͼ";
	public static final String CHART_BAR = "�����۵�����ͼ";
	public static final String CHART_LINE = "�����۵�����ͼ";
	public static final String CHART_BAR_X_TITLE = "�۵����";
	public static final String CHART_BAR_Y_TITLE = "Ƶ��";
	public static final String CHART_LINE_X_TITLE = "����ʱ��";
	public static final String CHART_LINE_Y_TITLE = "�۵����";

	// ����ͼά�Ȼ��ֵ�λ
	public static final int CHART_LINE_DIMENSION_DAY = 0;
	public static final int CHART_LINE_DIMENSION_HOUR = 1;
	public static final int CHART_LINE_DIMENSION_DAY_AND_HOUR = 2;
	public static final int CHART_LINE_SHOW_NUMS_LIMIT = 5;

	// �����ʻ�ȼ����ּ��
	public static final double EWORD_PER_LEVEL = 0.333;
	public static final String EWORD_NEUTRAL_LEVEL = "������̬��";
	/**
	 * ���������ʻ�ĵȼ�����
	 */
	public static final String[] EWORD_NEGATIVE_LEVEL = new String[] { "�е�����",
			"��ϲ��", "�ǳ����" };

	/**
	 * ���������ʻ�ĵȼ�����
	 */
	public static final String[] EWORD_POSITIVE_LEVEL = new String[] { "�е�ϲ��",
			"ϲ��", "�ǳ��õ�" };

	// ����ʻ�۵�����
	public static final int EWORD_LEVEL_POSITIVE = 0;
	public static final int EWORD_LEVEL_NEUTRAL = 1;
	public static final int EWORD_LEVEL_NEGATIVE = 2;

	public static final String EWORD_LEVEL_POSITIVE_NAME = "����";
	public static final String EWORD_LEVEL_NEUTRAL_NAME = "����";
	public static final String EWORD_LEVEL_NEGATIVE_NAME = "����";

	// �����Ĵ��Լ��𻮷�
	public static final String[] NEGATIVE_WORDS_LEVEL = new String[] { "NA",
			"NB", "NC", "ND", "NE", "NF", "NG", "NH", "NI", "NJ", "NK", "NL",
			"NN" };

	// �����Ĵ��Ｖ�𻮷�
	public static final String[] POSITIVE_WORDS_LEVEL = new String[] { "PA",
			"PB", "PC", "PD", "PE", "PF", "PG", "PH", "PI", "PJ", "PK", "PL",
			"PN" };

	// �ֵ��·��������ʱʹ��
	public static String DICT_PATH = "C:\\Users\\lyq\\Desktop\\icon\\dict.txt";
	//�м���������Ĭ�����λ��
	public static String DEFAULT_OUTPUT_PATH = "C:\\Users\\lyq\\Desktop\\�ҵı�ҵ���\\newsComments2.txt";
	//�������������
	public static String DEFAULT_OUTPUT_FILE_NAME = "newsComments.txt";

	// �ֵ��ÿ�����Ժ���
	public static int DICT_WORD_CONTENT = 0;
	public static int DICT_WORD_CLASS = 4;
	public static int DICT_WORD_STRONG = 5;

	// �洢�۵㼫�Ե������Ĭ��10W��
	public static final int HASH_NUM = 100000;

	// ����ÿ����ȡ���۵���Ŀ
	public static final int CRAWLER_PER_REQ_NUM = 50;
	// ������ȡ����������
	public static final int CRAWLER_TOTAL_REQ_NUM = 100;

	// ������ʾ������λ��
	public static final int FRAME_LEFT = 0;
	public static final int FRAME_TOP = 0;
	public static final int FRAME_WIDTH_SIZE = 500;
	public static final int FRAME_HEIGHT_SIZE = 400;
	
	public static final int FRAME_TOP_FREQUENT_WORDS_SIZE = 10;
	
	//����������ɫ
	public static final Color BACK_COLOR = new Color(41, 91, 126);
	//����������ɫ
	public static final Color FONT_COLOR = Color.WHITE;

	// ������ʾ���ּ���ʶ
	// ��һ�������õ��Ĳ�������
	public static final String FRAME_START_MINING_BTN_TEXT = "��ʼ�۵��ھ�";
	public static final String FRAME_START_MINING_BTN_NAME = "miningButton";
	public static final String FRAME_URL_LABEL_TEXT = "��������";
	public static final String FRAME_EXIST_PATH_TEXT = "�����ļ�·��";
	public static final String FRAME_OUTPUT_PATH_TEXT = "�����������·��";
	public static final String FRAME_BROWSER_BTN_TEXT = "���";
	public static final String FRAME_BROWSER_BTN_NAME = "awButton";
	public static final String FRAME_BROWSER_OUTPUT_BTN_TEXT = "���";
	public static final String FRAME_BROWSER_OUTPUT_BTN_NAME = "outputBwButton";

	// �ڶ��������õ��Ĳ�������
	public static final String FRAME_NEWS_TITLE_TEXT = "���ű���";
	public static final String FRAME_QUICK_OPERATION_TEXT = "��ݲ���";
	public static final String FRAME_BROWSER_COMMENT_TEXT = "�鿴����";
	public static final String FRAME_BROWSER_COMMENT_NAME = "commentContentBtn";
	public static final String FRAME_HOT_WORDS_TEXT = "�������۴�";
	public static final String FRAME_HOT_WORDS_NAME = "freqWordsBtn";
	public static final String FRAME_STAT_RESULT_TEXT = "ͳ�ƽ��";
	public static final String FRAME_STAT_PIE_TEXT = "��״ͼ";
	public static final String FRAME_STAT_PIE_NAME = "pieBtn";
	public static final String FRAME_STAT_BAR_TEXT = "����ͼ";
	public static final String FRAME_STAT_BAR_NAME = "barBtn";
	public static final String FRAME_STAT_LINE_TEXT = "����ͼ";
	public static final String FRAME_STAT_LINE_NAME = "lineBtn";
	public static final String FRAME_STAT_LINE_SPLIT_DIMENSION_TEXT = "����ͼ����ά��";
	public static final String FRAME_STAT_LINE_SPLIT_DAY_TEXT = "���컮��";
	public static final String FRAME_STAT_LINE_SPLIT_DAY_NAME = "dayCheck";
	public static final String FRAME_STAT_LINE_SPLIT_HOUR_TEXT = "��Сʱ����";
	public static final String FRAME_STAT_LINE_SPLIT_HOUR_NAME = "hourCheck";
	public static final String FRAME_BACK_BTN_TEXT = "��һ��";
	public static final String FRAME_BACK_BTN_NAME = "backBtn";

	// ��������ҳ����
	public static final String FRAME_COMMENT_CONTENT_VIEW_NAME = "��������";
	
	//�ļ�ѡ��ģʽ��ѡ��Ŀ¼
	public static final int FILE_CHOSEN_DIR_MODE = 1;
	//ѡ���ļ�
	public static final int FILE_CHOSEN_FILE_MODE = 0;

	/**
	 * �ж��ַ�str1�Ƿ�����ַ�str2
	 * 
	 * @param srt1
	 * @param str2
	 * @return
	 */
	public static boolean isContained(String str1, String str2) {
		boolean isContained;
		String temp;

		// Ĭ�ϲ�����
		isContained = false;

		// ����ַ�str1�ĳ���С��str2�ĳ��ȣ���ֱ�ӷ���
		if (str1.length() < str2.length()) {
			return isContained;
		}

		for (int i = 0; i <= str1.length() - str2.length(); i++) {
			// ��str1�н�ȡ��str2��Ӧ���ȵ��Ӵ�����ƥ��
			temp = str1.substring(i, i + str2.length());

			if (temp.equals(str2)) {
				isContained = true;
				break;
			}
		}

		return isContained;
	}

	/**
	 * ʱ���ת������Сʱ��ʽ
	 * 
	 * @param timeStr
	 */
	public static String timeTransFormat(long time, int dimen) {
		String date;
		long t;
		SimpleDateFormat sdf;

		if (dimen == CHART_LINE_DIMENSION_DAY) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		} else if (dimen == CHART_LINE_DIMENSION_HOUR) {
			sdf = new SimpleDateFormat("yyyy-MM-dd-HHʱ");
		} else {
			sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
		}

		date = sdf.format(new Date(time * 1000L));

		return date;
	}

	/**
	 * ��ʼ������λ�ã�������ʾ
	 */
	public static void initFrameLocation(JFrame frame) {
		int windowWidth = frame.getWidth(); // ��ô��ڿ�
		int windowHeight = frame.getHeight(); // ��ô��ڸ�
		
		Toolkit kit = Toolkit.getDefaultToolkit(); // ���幤�߰�
		Dimension screenSize = kit.getScreenSize(); // ��ȡ��Ļ�ĳߴ�
		
		int screenWidth = screenSize.width; // ��ȡ��Ļ�Ŀ�
		int screenHeight = screenSize.height; // ��ȡ��Ļ�ĸ�
		
		frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2
				- windowHeight / 2);// ���ô��ھ�����ʾ
	}
}
