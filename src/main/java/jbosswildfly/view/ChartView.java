package jbosswildfly.view;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

@Named
@RequestScoped
public class ChartView {

	public ChartView() {
		// TODO Auto-generated constructor stub
		System.out.println("ChartView init called!!!" + this.toString());
	}

	@PostConstruct
	public void init() {
		createAreaModel();
	}

	private void createAreaModel() {
		areaModel = new LineChartModel();

		LineChartSeries boys = new LineChartSeries();
		boys.setFill(true);
		boys.setLabel("Boys");
		for (int i = 0; i < 35; i++) {
			int year = 1904 + i;
			int births = (int) (Math.random() * 200);
			boys.set(Integer.toString(year), births);
		}

		LineChartSeries girls = new LineChartSeries();
		girls.setFill(true);
		girls.setLabel("Girls");
		for (int i = 0; i < 35; i++) {
			int year = 1904 + i;
			int births = (int) (Math.random() * 100);
			girls.set(Integer.toString(year), births);
		}

		areaModel.addSeries(boys);
		areaModel.addSeries(girls);
		
		areaModel.setTitle("Area Chart");
		areaModel.setLegendPosition("ne");
		areaModel.setStacked(true);
		areaModel.setShowPointLabels(false);
		
		areaModel.setExtender("chartExtender");

		Axis xAxis = new CategoryAxis("Years");
		xAxis.setTickInterval("10");
		
        areaModel.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = areaModel.getAxis(AxisType.Y);
		yAxis.setLabel("Births");
		yAxis.setMin(0);
		yAxis.setMax(300);
		
		
	}

	private LineChartModel areaModel;

	public LineChartModel getAreaModel() {
		return areaModel;
	}
}
