package TextMining.chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.JFreeChart;

import static TextMining.tool.ConstantUtils.*;

/**
 * ͼ�������
 * @author lyq
 *
 */
public abstract class BaseChart {
	//ͳ��ͼ������
	protected String chartName;
	//ͳ����ɵ������ʵļ�������
	protected ArrayList<Double> totalEWordLevels;
	//ͼ�࣬��������ͼ����ʽ
	protected JFreeChart jChart;
	
	/**
	 * ����ͳ��ͼ�������ݣ��õ�ͳ�Ƽ�ֵ��
	 * @return
	 */
	public HashMap<String, Double> parseEWordLevels(){
		String levelName;
		int pos;
		HashMap<String, Double> map;
		
		map = new HashMap<>();
		for(Double level: totalEWordLevels){
			//�������Ϊ��������ֱ�Ӵ���
			if(level == 0){
				levelName = EWORD_NEUTRAL_LEVEL;
			}else if(level > 0){
				pos = (int) (level/EWORD_PER_LEVEL);
				levelName = EWORD_POSITIVE_LEVEL[pos];
			}else{
				pos = (int) (-1.0 * level/EWORD_PER_LEVEL);
				levelName = EWORD_NEGATIVE_LEVEL[pos];
			}
			
			insertDataToMap(levelName, map);
		}
		
		return map;
	}
	
	/**
	 * �������ݵ�ͼ��
	 * @param levelName
	 * @param map
	 */
	private void insertDataToMap(String levelName, HashMap<String, Double> map){
		double count;
		
		if(map.containsKey(levelName)){
			count = map.get(levelName);
		}else{
			count = 0;
		}
		
		//���м�����1�����´���ͼ��
		count++;
		map.put(levelName, count);
	}
	
	/**
	 * ��ʼ��ͼ����ʽ
	 * @return
	 */
	public abstract void initChartStyle();
	
	/**
	 * ����ͼ�ķ���
	 */
	public void buildChart(){
		initChartStyle();
	}
}
