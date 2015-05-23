package TextMining.tool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static TextMining.tool.ConstantUtils.*;

/**
 * �ĵ�Ԥ��������
 * 
 * @author lyq
 * 
 */
public class PreTreatTool {
	// �������Ч�ʼ���
	private ArrayList<ArrayList<String>> effectWords;

	/**
	 * ��ȡ�ĵ���Ч���ļ�·��
	 * 
	 * @return
	 */
	public ArrayList<ArrayList<String>> getEFWPaths() {
		return this.effectWords;
	}

	/**
	 * ���ı�������Ч�ʵĹ��˺ʹʸ���Ϣ����ȡ
	 * 
	 * @param sentence
	 */
	public String preTreatWords(String sentence) {
		// ����ƥ��
		Pattern numberPattern;
		Matcher numberMatcher;
		String[] strArray;
		String resultSentence;

		numberPattern = Pattern.compile("[0-9]+(.[0-9]+)?");

		strArray = new String[sentence.length()];
		for (int i = 0; i < sentence.length(); i++) {
			strArray[i] = sentence.charAt(i) + "";

			numberMatcher = numberPattern.matcher(strArray[i]);
			// ����ַ������ֽ��й���
			if (numberMatcher.matches()) {
				strArray[i] = "";
			}

			// ���й��˴ʵĹ���
			for (String filerWord : FILTERED_WORDS) {
				if (strArray[i].equals(filerWord)) {
					strArray[i] = "";
					break;
				}
			}
		}

		resultSentence = "";
		// �����˺�Ĵ�������װ
		for (String s : strArray) {
			resultSentence += s;
		}

		return resultSentence;
	}

}
