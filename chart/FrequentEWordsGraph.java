package TextMining.chart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import TextMining.entity.EWord;

/**
 * ��Ƶ�����ֵ����δ���ͼ
 * @author lyq
 *
 */
public class FrequentEWordsGraph {
	//��Ҫ���ǰ���ٵĴ���
	private int k;
	//ͳ�Ƴ������еĹ۵�������
	private Map<String, Integer> eWordsCount;
	
	public FrequentEWordsGraph(int k, Map<String, Integer> eWordsCount){
		this.k = k;
		this.eWordsCount = eWordsCount;
	}
	
	/**
	 * ��ʾ�����������Ĺ۵��
	 */
	public void showTopKEWords(){
		ArrayList<EWord> wordList;
		
		wordList = eWordsSort();
		try {
			new ShowEWords(wordList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * ���ݴ�Ƶ�����н�������
	 */
	public ArrayList<EWord> eWordsSort(){
		String wordName;
		int count;
		EWord ew;
		ArrayList<EWord> wordList;
		
		wordList = new ArrayList<>();
		for(Map.Entry<String, Integer> entry: eWordsCount.entrySet()){
			wordName = entry.getKey();
			count = entry.getValue();
			
			ew = new EWord(wordName, count);
			wordList.add(ew);
		}
		
		//���н�������
		Collections.sort(wordList);
		
		return wordList;
	}
	
	class ShowEWords extends JFrame
	{
	JTextArea ta = null; 
	JScrollPane jsp = null;
	EWord ew;
	
	ShowEWords(ArrayList<EWord> wordList) throws Exception {
	this.setVisible(true);
	this.setSize(300,300);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	ta = new JTextArea();
	jsp = new JScrollPane(ta);
	ta.setText(null);
	
	//ɸѡ��ǰk���۵��
	for(int i=0; i<k; i++){
		ew = wordList.get(i);
		
		ta.append(ew.getName());
		ta.append("\n");
	}
	
	add(jsp);
	validate();
	}
	}
}
