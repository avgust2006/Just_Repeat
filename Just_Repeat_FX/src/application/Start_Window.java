package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Start_Window extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
		 
		 	Parent root = FXMLLoader.load(getClass().getResource("Main_Scene.fxml"));
		 	 
		 	Scene scene = new Scene(root);
		  	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		 	primaryStage.setScene(scene);
		// 	primaryStage.setTitle("JustRepeat.Dictionary");
		  	primaryStage.getIcons().add(new Image ("https://www.nicepng.com/png/full/417-4170762_streamvpg-world-vector-logo-png.png"));
		    primaryStage.initStyle(StageStyle.TRANSPARENT);
		  	primaryStage.setResizable(false);
			primaryStage.show();
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
