package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Vue extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Vue.fxml"));
			Parent parent = loader.load(); 
			Scene scene = new Scene(parent);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Calculatrice Graphique");
			try {
				primaryStage.getIcons().add(new Image("Photos/Photo.png"));
			}
			catch(IllegalArgumentException exception) {
				
			}
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}

