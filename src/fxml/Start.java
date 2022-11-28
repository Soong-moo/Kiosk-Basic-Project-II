package fxml;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;

import Database.DBConnect;

public class Start extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
			Scene scene = new Scene(root,800,700);
			
			primaryStage.getIcons().add(new Image("img/ㅇㅅㄲ.jpg"));
			primaryStage.setTitle("MakeYourBurger");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		new DBConnect().connect();
		launch(args);
	}
	
}


