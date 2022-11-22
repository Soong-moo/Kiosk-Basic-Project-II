package fxml;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CheckController implements Initializable{ 
	
	@FXML
	private Button btnPay;
	@FXML
	private Button btnCancel;
	@FXML
	private Label menu1;
	@FXML
	private Label menu2;
	@FXML
	private Label menu3;
	@FXML
	private Label price1;
	@FXML
	private Label price2;
	@FXML
	private Label price3;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//menu1.setText(OrderController.t.get(0).getName());
		//menu2.setText(OrderController.t.get(1).getName());
		//price1.setText(String.valueOf(OrderController.t.get(0).getPrice()));
		//price2.setText(String.valueOf(OrderController.t.get(1).getPrice()));
	}
	
	
	public void pay() throws Exception {
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/PaymentMethod.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btnPay.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void cancel() throws Exception {
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Order.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btnCancel.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
