package fxml;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class ManagerController {
	
	@FXML
	private Button btadjustmenu;
	
	@FXML
	private Button btsalesvolume;
	
	@FXML
	private Button btnotorderable;
	
	@FXML
	private Button btexit;
	
	@FXML
	private Button popUpdatebutton;
	
	//private Parent root;
	
	
/*	public void adjustmenu() throws Exception {
		
		// TODO Auto-generated method stub
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FirstPage.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btadjustmenu.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}*/
	
	public void salesvolume() throws Exception {
		
		// TODO Auto-generated method stub
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Statistic.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btsalesvolume.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void notorderable() throws Exception {
		
		// TODO Auto-generated method stub
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Notone.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btnotorderable.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void exitmanager() throws Exception {
		
		// TODO Auto-generated method stub
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FirstPage.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btexit.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

//	public void popUpdate(ActionEvent e) throws Exception{
//		
//		FXMLLoader loader = new FXMLLoader(getClass().getResource("Order.fxml"));
//		root = loader.load();	
//		
//		OrderController oc = loader.getController();
//		//oc.PopularHambuger1("test", "t", "t");
		
		
	}



