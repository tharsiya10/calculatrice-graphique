package application;


import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;


public class Graphique {
	
	private double yBorneInf;
	private double yBorneSup;
	private double xBorneInf;
	private double xBorneSup;
	protected LineChart<Double,Double>lineChart;
	
	public void setYBorneInf(double yinf) {
		yBorneInf = yinf;
	}
	
	public void setYBorneSup(double ysup) {
		yBorneSup = ysup;
	}
	
	public void setXBorneInf(double xinf) {
		xBorneInf = xinf;
	}
	
	public void setXBorneSup(double xsup) {
		xBorneSup = xsup;
	}
	
	public void setLineChart(LineChart<Double,Double> linechart) {
		this.lineChart = linechart;
	}
 
	//modifier la couleur de la courbe
    public void colorLine(ArrayList<XYChart.Series<Double, Double>> listSeries ,Color color) {
    	for(XYChart.Series<Double, Double> series : listSeries) {
    		Node line = series.getNode().lookup(".chart-series-line");
    		String rgb = String.format("%d, %d, %d",
    				(int)(color.getRed()*255), 
    				(int) (color.getGreen()*255),
    				(int) (color.getBlue()*255));
    		line.setStyle("-fx-stroke: rgba("+rgb+", 1.0);");
    	}
    }
    
    
    public void plotLine(String expr, 
    		ArrayList<XYChart.Series<Double, Double>> listSeries,Color color) { 
    	try {
    		int i = 0;
    		boolean previousInGraph = true;
	    	for(double x = xBorneInf; //intervalle de x
	    			x <= xBorneSup; x+=0.01) {
	    		double y = Parser.parser(expr, x);
	    		
	    		if(y >= yBorneInf && y <= yBorneSup){ 
	    			
	    					
	    			if(!previousInGraph) {
	    				listSeries.add(new XYChart.Series<Double,Double>());
		    			i++;
		    			previousInGraph = true;
		    			plotPoint(x-0.01,Parser.parser(expr,x-0.01),listSeries,i);
	    			}
	    			plotPoint(x,Parser.parser(expr,x),listSeries,i);
	    				    				
	    		}
	    		else {
	    			if(previousInGraph) {
	    				plotPoint(x,Parser.parser(expr,x),listSeries,i);
	    				previousInGraph = false;
	    			}
	    		}
	    		
	    		
	    		
	    	}
	    	
	    	lineChart.getData().addAll(listSeries);
	    	colorLine(listSeries, color);
	    	
	    	
    	}
    	catch(NumberFormatException e) {
    		
    	}   	
    }

    //ajouter les points
    public void plotPoint(double x, double y, ArrayList<XYChart.Series<Double, Double>> listSeries, int i) {
    	listSeries.get(i).getData().add(new XYChart.Data<Double, Double>(x,y));
    }
    
    //effacer la courbe
    public void clear(ArrayList<XYChart.Series<Double, Double>> listSeries) {
    	lineChart.getData().removeAll(listSeries);
    }

    

}
