package application;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Dialog;
import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;

public class CustomDialog {
	
	protected Dialog<?> dialog;
	protected ChampFonction champ;
	private ColorPicker colorPicker;
	private ButtonType valider;
	private ButtonType annuler;
	
	public CustomDialog() {
		dialog = new Dialog<>();
		dialog.setTitle("Changer de couleur");
		colorPicker = new ColorPicker();
		valider = new ButtonType("Appliquer",ButtonData.OK_DONE);
		annuler = new ButtonType("Annuler", ButtonData.CANCEL_CLOSE);
		init();
		appliquer();
		style();
		dialog.show();
	}

	
	public void setChampFonction(ChampFonction c) {
		champ = c;
	}
	
	private void init() {
		
		GridPane grid = new GridPane();
		grid.setHgap(10.0);
		grid.setVgap(10.0);
		grid.setPadding(new Insets(20,150,10,10));
		grid.add(colorPicker,1,0);
		
		
		dialog.getDialogPane().setContent(grid);
		
		dialog.getDialogPane().getButtonTypes().addAll(valider,annuler);
		

		
	}

	private void appliquer() {
		dialog.setResultConverter(dialogButton -> {
			if(dialogButton == valider) {
				try {
					champ.setColor(colorPicker.getValue());
					champ.legendColor.setBackground(
							new Background(new BackgroundFill(colorPicker.getValue(), null,null)));
				}
				catch(NullPointerException e) {
					
				}
				
			}
			
			return null;
			
		});
	}
	
	private void style() {
		String s = "-fx-cursor : hand;";
		colorPicker.setOnMouseEntered((event) -> {
			colorPicker.setStyle(s);
		});
		
	}


}
