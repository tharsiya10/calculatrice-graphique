package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Clavier extends Application implements EventHandler<ActionEvent> {
	private ChampFonction CF;

    public void setCF(ChampFonction c) {
        this.CF=c;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Clavier");

        // Création des boutons du clavier
        Button button1 = new Button("1");
        Button button2 = new Button("2");
        Button button3 = new Button("3");
        Button button4 = new Button("4");
        Button button5 = new Button("5");
        Button button6 = new Button("6");
        Button button7 = new Button("7");
        Button button8 = new Button("8");
        Button button9 = new Button("9");
        Button button0 = new Button("0");

        Button buttonP = new Button("+");
        Button buttonM = new Button("-");
        Button buttonF = new Button("*");
        Button buttonD = new Button("/");
        Button buttonS = new Button("C");
        Button buttonEFFACE = new Button("EFF");
        Button buttonV = new Button(".");
        Button buttonPU = new Button("^");
        Button buttonPO = new Button("(");
        Button buttonPF = new Button(")");
        Button buttonPI=new Button("PI");
        Button buttonX = new Button("x");

        Button buttonEXP = new Button("exp");
        Button buttonLOG = new Button("log");
        Button buttonLN = new Button("ln");
        Button buttonR = new Button("sqrt");
        Button buttonSIN = new Button("sin");
        Button buttonCOS = new Button("cos");
        Button buttonTAN = new Button("tan");
        Button buttonABS = new Button("abs");
        Button buttonASIN = new Button("asin");
        Button buttonACOS = new Button("acos");
        Button buttonATAN = new Button("atan");
        Button buttonCRTB = new Button("cbrt");

        // Ajout du ActionListener pour chaque boutton
        button0.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "0");
        });
        button1.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "1");
        });
        button2.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "2");
        });
        button3.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "3");
        });
        button4.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "4");
        });
        button5.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "5");
        });
        button6.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "6");
        });
        button7.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "7");
        });
        button8.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "8");
        });
        button9.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "9");
        });
        buttonP.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "+");
        });
        buttonM.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "-");
        });
        buttonF.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "*");
        });
        buttonD.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "/");
        });
        buttonS.setOnAction(event -> {
            String s = CF.fonction.getText();
            if (s.length() >0) CF.fonction.setText(s.substring(0, 0));
        });
        buttonEFFACE.setOnAction(event -> {
            String s = CF.fonction.getText();
            if (s.length() >0) CF.fonction.setText(s.substring(0, s.length()-1));
        });
        buttonV.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + ".");
        });
        buttonPU.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "^");
        });
        buttonPO.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "(");
        });
        buttonPF.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + ")");
        });
        buttonEXP.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "exp(");
        });
        buttonLOG.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "log(");
        });
        buttonLN.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "ln(");
        });
        buttonR.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "sqrt(");
        });
        buttonSIN.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "sin(");
        });
        buttonCOS.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "cos(");
        });
        buttonTAN.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "tan(");
        });
        buttonABS.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "abs(");
        });
        buttonASIN.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "asin(");
        });
        buttonATAN.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "atan(");
        });
        buttonACOS.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "acos(");
        });
        buttonCRTB.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "cbrt(");
        });
        buttonPI.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "PI");
        });
        buttonX.setOnAction(event -> {
            CF.fonction.setText(CF.fonction.getText() + "x");
        });

        // Placer les bouttons sur la fenêtre
        GridPane gridPane = new GridPane();

        gridPane.add(button1, 1, 1, 1, 1);
        gridPane.add(button2, 2, 1, 1, 1);
        gridPane.add(button3, 3, 1, 1, 1);
        gridPane.add(button4, 1, 2, 1, 1);
        gridPane.add(button5, 2, 2, 1, 1);
        gridPane.add(button6, 3, 2, 1, 1);
        gridPane.add(button7, 1, 3, 1, 1);
        gridPane.add(button8, 2, 3, 1, 1);
        gridPane.add(button9, 3, 3, 1, 1);
        gridPane.add(button0, 2, 4, 1, 1);

        gridPane.add(buttonP, 6, 1, 1, 1);
        gridPane.add(buttonM, 7, 1, 1, 1);
        gridPane.add(buttonF, 8, 1, 1, 1);
        gridPane.add(buttonD, 9, 1, 1, 1);
        gridPane.add(buttonPO, 6, 2, 1, 1);
        gridPane.add(buttonPF, 7, 2, 1, 1);
        gridPane.add(buttonV, 8, 2, 1, 1);
        gridPane.add(buttonPU, 9, 2, 1, 1);
        gridPane.add(buttonPI, 6, 3, 1, 1);
        gridPane.add(buttonX, 7, 3, 1, 1);
        gridPane.add(buttonS, 8, 3, 1, 1);
        gridPane.add(buttonEFFACE, 9, 3, 1, 1);

        gridPane.add(buttonEXP, 12, 1, 1, 1);
        gridPane.add(buttonLOG, 13, 1, 1, 1);
        gridPane.add(buttonLN, 14, 1, 1, 1);
        gridPane.add(buttonR, 15, 1, 1, 1);
        gridPane.add(buttonSIN, 12, 2, 1, 1);
        gridPane.add(buttonCOS, 13, 2, 1, 1);
        gridPane.add(buttonTAN, 14, 2, 1, 1);
        gridPane.add(buttonABS, 15, 2, 1, 1);
        gridPane.add(buttonASIN, 12, 3, 1, 1);
        gridPane.add(buttonACOS, 13, 3, 1, 1);
        gridPane.add(buttonATAN, 14, 3, 1, 1);
        gridPane.add(buttonCRTB, 15, 3, 1, 1);


        gridPane.setHgap(10);
        gridPane.setVgap(10);
        Scene scene = new Scene(gridPane, 663, 220);
        scene.getStylesheets().add(getClass().getResource("Button.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(ActionEvent actionEvent) {

    }
}
