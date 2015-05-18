package TextMining.entity;

import java.util.ArrayList;

import static TextMining.tool.ConstantUtils.*;

/**
 * �ʻ�ʱ�伫����
 * @author lyq
 *
 */
public class TimeEWordLevel implements Comparable<TimeEWordLevel>{
	//�ʻ��ͳ��ʱ��
	String time;
	//���ڸö�ʱ���ڵļ����б�
	ArrayList<Double> levels;
	
	/**
	 * ��ȡʱ���ֵ
	 * @return
	 */
	public String getTime(){
		return time;
	}
	
	public TimeEWordLevel(String time, ArrayList<Double> levels) {
		this.time = time;
		this.levels = levels;
	}
	
	/**
	 * ͳ�Ƽ����б��еļ������򣬷ֻ���������������
	 * @return
	 */
	public double[] statLevels(){
		double[] array;
		
		array = new double[3];
		for(double value: levels){
			if(value > 0){
				array[EWORD_LEVEL_NEGATIVE]++;
			}else if(value == 0){
				array[EWORD_LEVEL_NEUTRAL]++;
			}else{
				array[EWORD_LEVEL_POSITIVE]++;
			}
		}
		
		//��ͳ��Ƶ������һ������
		for(int i=0; i<array.length; i++){
			array[i] /= levels.size();
		}
		
		return array;
	}

	@Override
	public int compareTo(TimeEWordLevel o) {
		// TODO Auto-generated method stub
		return o.time.compareTo(this.time);
	}
}
