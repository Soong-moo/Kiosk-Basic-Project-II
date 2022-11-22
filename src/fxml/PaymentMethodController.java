package fxml;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class PaymentMethodController {

	@FXML
	private Button btnCC;
	@FXML
	private Button btnSPay;
	
	int credit = 0, s = 0;
	
	public void creditCard() throws Exception {
		credit++; // 결제 수단 파악
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Complete.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btnCC.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void sPay() throws Exception {
		s++; // 결제 수단 파악
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Complete.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btnSPay.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
