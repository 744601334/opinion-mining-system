package TextMining.entity;

import java.util.ArrayList;

/**
 * �۵����
 * 
 * @author lyq
 * 
 */
public class Sentence {
	// �۵�����е�ÿ���Ӿ�
	public ArrayList<String> childSentences;
	// �۵���ӵķ���ʱ��
	public long time;
	
	public Sentence(ArrayList<String> childSentences, long time) {
		this.childSentences = childSentences;
		this.time = time;
	}
}
