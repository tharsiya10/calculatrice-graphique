package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;


import javafx.scene.control.SplitPane;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controleur {
	@FXML
	private SplitPane splitLeft;
	@FXML
	private AnchorPane paneLeft;
	@FXML
	private MenuItem addTextField;
	@FXML
	private MenuItem clavier;
	@FXML
	protected VBox leftControl;
	@FXML
	protected AnchorPane addPane;
	@FXML
	protected ScrollPane scrollPane;
	@FXML
	protected AnchorPane paneScroll;
	@FXML
	protected VBox leftChamp;
	@FXML
	private AnchorPane paneRight;
    @FXML
    private AnchorPane graphique;
	@FXML
	protected LineChart<Double,Double>lineChart;
	@FXML
	private NumberAxis xAxis, yAxis;
    @FXML
    private AnchorPane formReglage;
    @FXML
    private RadioButton contraste;
    @FXML
    private RadioButton grille;
    @FXML
    private RadioButton axeNombre;
    @FXML
    private RadioButton abscisses;
    @FXML
    private TextField xLegende;
    @FXML
    protected TextField xBorneInf;
    @FXML
    protected TextField xBorneSup;
    @FXML
    private TextField xPas;
    @FXML
    private RadioButton coordonnees;
    @FXML
    private TextField yLegende;
    @FXML
    protected TextField yBorneInf;
    @FXML
    protected TextField yBorneSup;
    @FXML
    private TextField yPas;
    @FXML
    private Button reglage;
    
    protected ArrayList<ChampFonction> champs;

    protected Graphique afficheGraphique;
	
    
    @FXML
    void initialize() {
    	scrollPane.prefHeightProperty().bind(paneLeft.heightProperty());
    	scrollPane.prefWidthProperty().bind(paneLeft.widthProperty());
    	
    	lineChart.setLegendVisible(false);
    	lineChart.setCreateSymbols(false);
    	
    	SplitPane.setResizableWithParent(paneLeft, false);
    	
    	champs = new ArrayList<ChampFonction>();
    	ChampFonction i = new ChampFonction();
    	champs.add(i);
    	initializeChamp(i);
    	addChampInLeftControl(i);
    	
    	afficheGraphique = new Graphique();
    	afficheGraphique.setLineChart(lineChart);
    	try {
    		afficheGraphique.setYBorneInf(Double.parseDouble(yBorneInf.getText()));
    		afficheGraphique.setYBorneSup(Double.parseDouble(yBorneSup.getText()));
    		afficheGraphique.setXBorneInf(Double.parseDouble(xBorneInf.getText()));
    		afficheGraphique.setXBorneSup(Double.parseDouble(xBorneSup.getText()));
    	}
    	catch(NumberFormatException e) {
    		
    	}
    	handleLeftPart();
    	handleReglage();
    }
    
    private void initializeChamp(ChampFonction c) {
    	c.setCtrleur(this);
    	c.handleChampFonction();
    	c.handleLegendColor();
    }
    
    private void addChampInLeftControl(ChampFonction c) {
    	leftChamp.getChildren().add(c.champ);
    }
    
    public void removeChampInLeftControl(ChampFonction c) {
    	leftChamp.getChildren().remove(c.champ);
    }
    
    public void removeChampInList(ChampFonction c) {
    	champs.remove(c);
    }
    
    private void handleFonction() {
    	
    	for(ChampFonction c : champs) {
    		c.modifCourbe();
    	}
    	
    	
    }
    
    private void handleLeftPart() {
    	try {
    		clavier.setGraphic(new ImageView("Photos/Photo1.png"));
    		addTextField.setGraphic(new ImageView("Photos/Photo2.png"));
    	}
    	catch(IllegalArgumentException e) {
    		
    	}
    	addTextField.setOnAction((event) -> {
    		ChampFonction c = new ChampFonction();
    		champs.add(c);
    		initializeChamp(c);
    		addChampInLeftControl(c);
    	});
    	
    	clavier.setOnAction((event)->{   		
    		Clavier clavier=new Clavier();
    		try {
				clavier.start(new Stage());
			} catch (Exception e) {
				
			}
    		
    		ChampFonction c = new ChampFonction();
    		champs.add(c);
    		initializeChamp(c);
    		addChampInLeftControl(c);
    		
    		clavier.setCF(c);
    	});
    	
    }
    
    private void handleReglage() {
    	//bouton reglage pour afficher formulaire
    	reglage.setOnAction((event) -> {
    		setFormulaireReglage(formReglage.isVisible());
    	});
    	 
    	grille.setOnAction((event) -> {
    		setGridLinesVisible(grille.isSelected());
    	});
    	
    	axeNombre.setOnAction((event) -> {
    		setTickLabelsVisible(axeNombre.isSelected());
    	});
    	
    	abscisses.setOnAction((event) -> {
    		setAbscissesVisible(abscisses.isSelected());
    	});
    	
    	coordonnees.setOnAction((event) -> {
    		setCoordonneesVisible(coordonnees.isSelected());
    	});
    	
    	xLegende.setOnKeyTyped((event) -> {
    		setLegende(xAxis,xLegende.getText());
    	});
    	
    	xPas.setOnKeyTyped((event) -> {
    		setPas(xAxis,xPas);
    	});
    	
    	xBorneInf.setOnKeyReleased((event) -> {
    		setBorneInf(xAxis, xBorneInf);
    		try {
    			afficheGraphique.setXBorneInf(Double.parseDouble(xBorneInf.getText()));
    		}
    		catch(NumberFormatException e) {
    			
    		}
    		handleFonction();

    	});
    	
    	xBorneSup.setOnKeyReleased((event) -> {
    		setBorneSup(xAxis, xBorneSup);
    		try {
    			afficheGraphique.setXBorneSup(Double.parseDouble(xBorneSup.getText()));
    		}
    		catch(NumberFormatException e) {
    			
    		}
    		handleFonction();

    	});
    	
    	yLegende.setOnKeyTyped((event) -> {
    		setLegende(yAxis,yLegende.getText());
    	});
    	
    	yPas.setOnKeyTyped((event) -> {
    		setPas(yAxis,yPas);
    	});
    	yBorneInf.setOnKeyReleased((event) -> {
    		setBorneInf(yAxis, yBorneInf);
    		try {
    			afficheGraphique.setYBorneInf(Double.parseDouble(yBorneInf.getText()));
    		}
    		catch(NumberFormatException e) {
    			
    		}
    		handleFonction();

    	});
    	
    	yBorneSup.setOnKeyReleased((event) -> {
    		setBorneSup(yAxis, yBorneSup);
    		try {
    			afficheGraphique.setYBorneSup(Double.parseDouble(yBorneSup.getText()));
    		}
    		catch(NumberFormatException e) {
    			
    		}
    		handleFonction();

    	});
    	
    	contraste.setOnAction((event) -> {
    		setContraste(contraste.isSelected());
    	});
    }
    
    private void setFormulaireReglage(boolean b) {
    	formReglage.setVisible(!b);
    }
    
    private void setGridLinesVisible(boolean b) {
    	lineChart.setHorizontalGridLinesVisible(b);
    	lineChart.setVerticalGridLinesVisible(b);
    } 
    
    private void setTickLabelsVisible(boolean b) {
    	xAxis.setTickLabelsVisible(b);
    	yAxis.setTickLabelsVisible(b);
    	setLegende(xAxis,xLegende.getText(),b);
    	setLegende(yAxis,yLegende.getText(),b);
    }
  
    private void setLegende(NumberAxis axe, String s,boolean b) {
    	if(b) {
    		axe.setLabel(s);
    	}
    	else {
    		axe.setLabel("");
    	}
    }
    
    private void setLegende(NumberAxis axe, String s) {
    	setLegende(axe,s,true);
    } 
    
    private void setAbscissesVisible(boolean b) {
 		lineChart.setHorizontalZeroLineVisible(b);
 		xAxis.setTickLabelsVisible(b);
 		setLegende(xAxis,xLegende.getText(),b);
     }
     
     private void setCoordonneesVisible(boolean b) {
     	lineChart.setVerticalZeroLineVisible(b);
     	yAxis.setTickLabelsVisible(b);
     	setLegende(yAxis,yLegende.getText(),b);
     } 
     
     private void setPas(NumberAxis axe, TextField t) {
     	try {
     		axe.setTickUnit(Double.parseDouble(t.getText()));
     		setStyle(t, "-fx-focus-color: #039ED3");
     	}
     	catch(NumberFormatException e) {
     		setStyle(t,"-fx-focus-color: #ff1a1a;");
     	}
     } 
     
     private void setBorneInf(NumberAxis axe, TextField t) {
     	try {
     		axe.setLowerBound(Double.parseDouble(t.getText()));
     		setStyle(t, "-fx-focus-color: #039ED3");
     	}
     	catch(NumberFormatException e) {
     		setStyle(t,"-fx-focus-color: #ff1a1a;");
     	}
     }
    
    private void setBorneSup(NumberAxis axe, TextField t) {
    	try {
    		axe.setUpperBound(Double.parseDouble(t.getText()));
    		setStyle(t, "-fx-focus-color: #039ED3");
    	}
    	catch(NumberFormatException e) {
    		setStyle(t,"-fx-focus-color: #ff1a1a;");
    	}
    }

    private void setContraste(boolean b) {
    	Node chart = lineChart.lookup(".chart");
    	if(chart != null) {
    		Node chartPlotBackground = chart.lookup(".chart-plot-background");
    		Node chartVerticalZeroLines = chart.lookup(".chart-vertical-zero-line");
    		Node chartHorizontalZeroLines = chart.lookup(".chart-horizontal-zero-line");
    		    		
    		if(b) {
    			setStyle(chart,"-fx-background-color: black;");
    			setStyle(chartPlotBackground,"-fx-background-color: black;");
    			setStyle(chartVerticalZeroLines, "-fx-stroke: white;");
    			setStyle(chartHorizontalZeroLines,"-fx-stroke: white;");
    			
    			xAxis.setTickLabelFill(Color.WHITE);
    			yAxis.setTickLabelFill(Color.WHITE);
    			
    		}
    		else {
    			setStyle(chart,"-fx-background-color: white;");
    			setStyle(chartPlotBackground,"-fx-background-color: white;");
    			setStyle(chartVerticalZeroLines, "-fx-stroke: black;");
    			setStyle(chartHorizontalZeroLines,"-fx-stroke: black;");
    			
    			xAxis.setTickLabelFill(Color.BLACK);
    			yAxis.setTickLabelFill(Color.BLACK);
    			
    		}

    	}
    	 
    }
    
    public void setStyle(Node n, String s) {
    	if(n != null) {
    		n.setStyle(s);
    	}
    }
        

}
