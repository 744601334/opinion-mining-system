package TextMining.chart;

import static TextMining.tool.ConstantUtils.CHART_LINE;
import static TextMining.tool.ConstantUtils.CHART_LINE_X_TITLE;
import static TextMining.tool.ConstantUtils.CHART_LINE_Y_TITLE;
import static TextMining.tool.ConstantUtils.EWORD_LEVEL_NEGATIVE;
import static TextMining.tool.ConstantUtils.EWORD_LEVEL_NEGATIVE_NAME;
import static TextMining.tool.ConstantUtils.EWORD_LEVEL_NEUTRAL;
import static TextMining.tool.ConstantUtils.EWORD_LEVEL_NEUTRAL_NAME;
import static TextMining.tool.ConstantUtils.EWORD_LEVEL_POSITIVE;
import static TextMining.tool.ConstantUtils.EWORD_LEVEL_POSITIVE_NAME;
import static TextMining.tool.ConstantUtils.MINING_SYSTEM_NAME;
import static TextMining.tool.ConstantUtils.initFrameLocation;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import TextMining.entity.TimeEWordLevel;
import TextMining.tool.ConstantUtils;

/**
 * ����ͳ��ͼ
 * 
 * @author lyq
 * 
 */
public class LineChart extends BaseChart {
	//��ʾ��ʱ��ά�ȵ�λ
	public int timeDimension;
	// �ʻ�ʱ�伫������
	private Map<String, ArrayList<Double>> mapData;

	public LineChart(String chartName, int timeDimension, Map<String, ArrayList<Double>> mapData) {
		this.timeDimension = timeDimension;
		this.chartName = chartName;
		this.mapData = mapData;
	}

	@Override
	public void initChartStyle() {
		// TODO Auto-generated method stub
		CategoryPlot plot;
		CategoryAxis domainAxis;
		ValueAxis valueAxis;
		TextTitle textTitle;
		LegendTitle legend;

		textTitle = jChart.getTitle();
		textTitle.setFont(new Font("����", Font.BOLD, 20));
		legend = jChart.getLegend();
		if (legend != null) {
			legend.setItemFont(new Font("����", Font.BOLD, 20));
		}

		plot = (CategoryPlot) jChart.getPlot();
		// (��״ͼ��x��)
		domainAxis = plot.getDomainAxis();
		// ����x�������ϵ�����
		domainAxis.setTickLabelFont(new Font("����", Font.BOLD, 20));
		// ����x���ϵı��������
		domainAxis.setLabelFont(new Font("����", Font.BOLD, 20));
		// (��״ͼ��y��)
		valueAxis = plot.getRangeAxis();
		// ����y�������ϵ�����
		valueAxis.setTickLabelFont(new Font("����", Font.BOLD, 20));
		// ����y�������ϵı��������
		valueAxis.setLabelFont(new Font("����", Font.BOLD, 20));
	}

	@Override
	public void buildChart() {
		// TODO Auto-generated method stub
		double[] infoCount;
		String chartTitle;
		String time;
		ChartFrame frame;
		TimeEWordLevel tEwl;
		ArrayList<Double> levelList;
		// ʱ�伫���б�
		ArrayList<TimeEWordLevel> timeLevels;
		//����ʱ��ά��ת�����ͼ����
		Map<String, ArrayList<Double>> timeMapData;

		//��ʱ���ͼ���ݰ�ʱ�䵥λά�ȷָ�
		timeMapData = timeTransForm(mapData);
		
		timeLevels = new ArrayList<>();
		for (Map.Entry<String, ArrayList<Double>> entry : timeMapData.entrySet()) {
			time = entry.getKey();
			levelList = entry.getValue();

			tEwl = new TimeEWordLevel(time, levelList);

			timeLevels.add(tEwl);
		}
		Collections.sort(timeLevels);

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		TimeEWordLevel t;
		timeLevels = selectTopData(timeLevels);
		
		//����ʱ���Ⱥ�˳�������ֵ
		for(int i=timeLevels.size()-1; i>=0; i--){
			t = timeLevels.get(i);
			
			time = t.getTime();
			infoCount = t.statLevels();

			dataset.addValue(infoCount[EWORD_LEVEL_POSITIVE],
					EWORD_LEVEL_POSITIVE_NAME, time);
			dataset.addValue(infoCount[EWORD_LEVEL_NEUTRAL],
					EWORD_LEVEL_NEUTRAL_NAME, time);
			dataset.addValue(infoCount[EWORD_LEVEL_NEGATIVE],
					EWORD_LEVEL_NEGATIVE_NAME, time);
		}

		chartTitle = this.chartName + CHART_LINE;
		// ����ͼ�����
		jChart = ChartFactory.createLineChart(chartTitle, CHART_LINE_X_TITLE,
				CHART_LINE_Y_TITLE, dataset, PlotOrientation.VERTICAL, true,
				true, false);

		frame = new ChartFrame(MINING_SYSTEM_NAME, jChart, true);
		frame.pack();
		frame.setVisible(true);
		initFrameLocation(frame);
		
		super.buildChart();
	}
	
	/**
	 * ��ͼ���ݽ���ʱ��ά�ȵĻ���
	 * @param mapData
	 * @return
	 */
	private Map<String , ArrayList<Double>> timeTransForm(Map<String , ArrayList<Double>> mapData){
		Map<String , ArrayList<Double>> newMapData;
		long time;
		String newTime;
		ArrayList<Double> list;
		ArrayList<Double> levels;
		newMapData = new HashMap<String, ArrayList<Double>>();
		
		//����ѡ����ʱ��ά�Ƚ��л���
		for(Map.Entry<String, ArrayList<Double>> entry: mapData.entrySet()){
			time = Long.parseLong(entry.getKey());
			list = entry.getValue();
			
			newTime = ConstantUtils.timeTransFormat(time, timeDimension);
			
			if(newMapData.containsKey(newTime)){
				levels = newMapData.get(newTime);
			}else{
				levels = new ArrayList<>();
			}
			
			//�������ݵĻ���
			levels.addAll(list);
			
			//���´�����ͼ��
			newMapData.put(newTime, levels);
		}
		
		return newMapData;
	}
	
	/**
	 * ��ȡ����
	 */
	private ArrayList<TimeEWordLevel> selectTopData(ArrayList<TimeEWordLevel> data){
		ArrayList<TimeEWordLevel> newData;
		//ֻ��ȡ5��ʱ��ε�ʱ��
		int limit = 0;
		
		newData = new ArrayList<>();
		for(TimeEWordLevel t: data){
			if(limit == ConstantUtils.CHART_LINE_SHOW_NUMS_LIMIT){
				break;
			}
			
			newData.add(t);
			
			limit++;
		}
		
		return newData;
	}
}