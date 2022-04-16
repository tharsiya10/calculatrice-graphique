package application;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Random;

import javafx.scene.Node;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ChampFonction {
	
	private Controleur ctrl;
	
	protected ArrayList<XYChart.Series<Double,Double>> listSeries;
	
	protected Label legendColor;
	protected Color color;
	protected TextField fonction;
	protected TextField rangeMin, rangeMax;
	protected Label l1, l2;
	protected CheckBox trace;
	protected VBox champ; 
	protected Button close;
	protected RadioButton[]intervalle = new RadioButton[4];
	
	public ChampFonction() {
		init();
		assemblage();
		
		color = randomColor();
		setLegendColor(color);
		
		legendColor.setPrefWidth(20);
		legendColor.setPrefHeight(23);
		fonction.setPrefWidth(132);
		rangeMin.setPrefWidth(38);
		rangeMax.setPrefWidth(38);
		l1.setPrefWidth(27);
		trace.setPrefWidth(148);
	}

	
	public void setCtrleur(Controleur ctrl) {
		this.ctrl = ctrl;
	}
	
	public void setColor(Color color) {
		this.color = color;
		ctrl.afficheGraphique.colorLine(listSeries, color);
	}
	
	private void init() {
		listSeries = new ArrayList<XYChart.Series<Double,Double>>();
		listSeries.add(new XYChart.Series<Double,Double>());
		
		legendColor = new Label("");
		legendColor.setStyle("-fx-cursor: hand");
		fonction = new TextField();
		close = new Button("x");
		close.setStyle("-fx-text-fill: #555; -fx-background-color : #DCDCDC	;");
		rangeMin = new TextField();
		rangeMax = new TextField();
		
		l1 = new Label("De");
		l2 = new Label("à");
		
		intervalle[0] = new RadioButton("[]");
		intervalle[1] = new RadioButton("[[");
		intervalle[2] = new RadioButton("]]");
		intervalle[3] = new RadioButton("][");
		
		ToggleGroup group = new ToggleGroup();
		for(RadioButton r : intervalle) {
			r.setToggleGroup(group);
			r.setOnMouseEntered((event) -> {
				r.setStyle("-fx-cursor : hand;");
			});
			
		}
		
		trace = new CheckBox("Tracer");
		
		champ = new VBox(); 
		
	}
	
	private void assemblage() {
		HBox h  = new HBox();
		HBox h1 = new HBox();
		HBox h2 = new HBox();
		HBox h3 = new HBox();
		HBox h4 = new HBox();
		VBox v1 = new VBox();
		h4.getChildren().addAll(intervalle[2],intervalle[3]);
		h3.getChildren().addAll(intervalle[0],intervalle[1]);
		v1.getChildren().addAll(h3,h4);
		h2.getChildren().addAll(trace);
		h1.getChildren().addAll(l1,rangeMin,l2,rangeMax,v1);
		h.getChildren().addAll(legendColor,fonction,close);
		champ.getChildren().addAll(h,h1,h2);
		
	}
	
    private Color randomColor() {
    	Random random = new Random();
    	return new Color(random.nextDouble(),random.nextDouble(),random.nextDouble(),1.0);
    }
    
    private void setLegendColor(Color color) {
    	legendColor.setBackground(new Background(new BackgroundFill(color, null,null)));
    }
    
    public void handleLegendColor() {
    	legendColor.setOnMouseClicked((event) -> {
    		CustomDialog c = new CustomDialog();
    		c.setChampFonction(this);
    	});
    }
    
    public void removeChamp() {
    	ctrl.removeChampInLeftControl(this); //on enlève le champ de la partie gauche de l'écran
    	ctrl.afficheGraphique.clear(listSeries);
		ctrl.removeChampInList(this);  //on enlève le champ qui était dans la liste de ChampFonction
		
    }
	private void handleCloseBtn() {
		close.setOnAction((event) -> {
			removeChamp();
		});

		close.setOnMouseEntered((event) -> {
			close.setStyle("-fx-cursor: hand; -fx-text-fill: #555; -fx-background-color : #E8E8E8;");
		});
		
		close.setOnMouseExited((event) -> {
			close.setStyle("-fx-text-fill: #555; -fx-background-color : #DCDCDC;");
		});
	}

	//si un des intervalles a été sélectionné ou non
	private boolean intervalleChecked() {
		for(int i = 0; i<intervalle.length; i++) {
			if(intervalle[i].isSelected()) {
				return true;
			}
		}
		return false;
	}
	
	public void handleParamCourbe() {
		rangeMin.setOnKeyReleased((event) -> {
			rangeMinErreur();
			modifCourbe();
		});
		rangeMax.setOnKeyReleased((event) -> {
			rangeMaxErreur();
			modifCourbe();
		});
		fonction.setOnKeyReleased((event) -> {
			modifCourbe();
		});
		for(RadioButton r : intervalle) {
			r.setOnAction((event) -> {
				modifCourbe();
			});
		}
	}
	
	
	private void handleFonction(boolean b) {
		try {
		boolean intervalleChoisi = intervalleChecked();
		
			if(b) {
				try {
				
					double min = Double.parseDouble(rangeMin.getText());
					double max = Double.parseDouble(rangeMax.getText());
						if(!intervalleChoisi || intervalle[0].isSelected()) {
						
							ctrl.afficheGraphique.setXBorneInf(min);
							ctrl.afficheGraphique.setXBorneSup(max);
							ctrl.afficheGraphique.plotLine(fonction.getText(), listSeries, color);
						}
						else {
							
								if(intervalle[1].isSelected()) {
									ctrl.afficheGraphique.setXBorneSup(max-0.01);
								}
								else if(intervalle[2].isSelected()) {
									ctrl.afficheGraphique.setXBorneInf(min+0.01);
								}
								else if(intervalle[3].isSelected()) {
									ctrl.afficheGraphique.setXBorneInf(min+0.01);
									ctrl.afficheGraphique.setXBorneSup(max-0.01);
								}
	
								ctrl.afficheGraphique.plotLine(fonction.getText(), listSeries
								, color);
						}
				}
				catch(NumberFormatException e) {
					try {
						double min = Double.parseDouble(ctrl.xBorneInf.getText());
						double max = Double.parseDouble(ctrl.xBorneSup.getText());
						ctrl.afficheGraphique.setXBorneInf(min);
						ctrl.afficheGraphique.setXBorneSup(max);
						ctrl.afficheGraphique.plotLine(fonction.getText(), listSeries
								, color);
					}
					catch(NumberFormatException exception) {
						
					}
				}

			}
			else {	
				
				ctrl.afficheGraphique.clear(listSeries);;
			
			}
			setStyle(fonction, "-fx-focus-color: #039ED3");
		}
		
		catch(EmptyStackException e) {
			//couleur du textfield rouge pour indiquer une erreur
			setStyle(fonction,"-fx-focus-color: #ff1a1a;");  
		}

	}
	
	private void handleCheckBox() {
		trace.setOnAction((event) -> {
			handleFonction(trace.isSelected());
		});
		trace.setOnMouseEntered((event) -> {
			trace.setStyle("-fx-cursor : hand;");
		});
	}
	
	//effectuer les modifications en appelant les methodes correspondantes
	public void modifCourbe() {
		ctrl.afficheGraphique.clear(listSeries);
		listSeries = new ArrayList<XYChart.Series<Double,Double>>();
		listSeries.add(new XYChart.Series<Double,Double>());
		handleFonction(trace.isSelected());	
	}
	
    public void handleChampFonction() {
    	handleParamCourbe();
    	handleCheckBox();
    	handleCloseBtn();
    }
    
    private void rangeMinErreur() {
    	if(!rangeMin.getText().isEmpty()) {
    	try {
    		Double.parseDouble(rangeMin.getText());
    		setStyle(rangeMin, "-fx-focus-color: #039ED3");
    	}
    	catch(NumberFormatException e) {
    		setStyle(rangeMin,"-fx-focus-color: #ff1a1a;");
    	}
    	}
    }
    
    private void rangeMaxErreur() {
    	if(!rangeMax.getText().isEmpty()) {
    	try {
    		Double.parseDouble(rangeMax.getText());
    		setStyle(rangeMax, "-fx-focus-color: #039ED3");
    	}
    	catch(NumberFormatException e) {
    		setStyle(rangeMax,"-fx-focus-color: #ff1a1a;");
    	}
    	}
    }
    
    private void setStyle(Node n, String s) {
    	if(n != null) {
    		n.setStyle(s);
    	}
    }


}
