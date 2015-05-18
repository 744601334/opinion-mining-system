package TextMining.entity;

/**
 * �����ʻ���
 * @author lyq
 *
 */
public class EWord implements Comparable<EWord>{
	//������������
	private String name;
	//�����ʵĴ�Ƶ
	private Integer count;

	public EWord(String name, int count){
		this.name = name;
		this.count = count;
	}
	
	/**
	 * ���ش�������
	 * @return
	 */
	public String getName(){
		return name;
	}
	
	@Override
	public int compareTo(EWord o) {
		// TODO Auto-generated method stub
		return o.count.compareTo(this.count);
	}

}
