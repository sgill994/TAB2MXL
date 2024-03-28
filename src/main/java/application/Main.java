package application;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import TAB2MXL.TabReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	TabReader outputXMLFile;
	String a;

	public static void main(String[] args) {
		
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
		//	FXMLLoader loader = new FXMLLoader(getClass().getResource("/PrimaryStage.fxml"));
			Parent root = FXMLLoader.load(getClass().getResource("PrimaryStage.fxml"));
			primaryStage.setTitle("TAB2XML");
			
			
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}

