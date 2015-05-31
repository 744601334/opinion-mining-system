package TextMining.chart;

import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import static TextMining.tool.ConstantUtils.*;

/**
 * ����ͼ������
 * 
 * @author lyq
 * 
 */
public class BarChart extends BaseChart {
	public BarChart(String chartName, ArrayList<Double> totalEWordLevels) {
		this.chartName = chartName;
		this.totalEWordLevels = totalEWordLevels;
	}

	@Override
	public void buildChart() {
		// TODO Auto-generated method stub
		String titleName;
		String levelName;
		double count;
		ChartFrame frame;
		DefaultCategoryDataset dataset;
		HashMap<String, Double> mapData;

		titleName = chartName + CHART_BAR;
		dataset = new DefaultCategoryDataset();

		mapData = parseEWordLevels();
		for (Map.Entry<String, Double> entry : mapData.entrySet()) {
			levelName = entry.getKey();
			count = entry.getValue();

			dataset.addValue(count, levelName, levelName);
		}

		jChart = ChartFactory.createBarChart3D(titleName, CHART_BAR_X_TITLE,
				CHART_BAR_Y_TITLE, dataset, PlotOrientation.VERTICAL, true,
				true, true);

		frame = new ChartFrame(MINING_SYSTEM_NAME, jChart, true);
		frame.pack();
		frame.setVisible(true);
		initFrameLocation(frame);
		
		super.buildChart();
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
}
