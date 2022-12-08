package fxml;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.stage.Stage;

public class PaymentMethodController {

	@FXML
	private Button btnCC; // 신용카드 결제
	@FXML
	private Button btnSPay; // 삼성페이 결제
	
	static int credit = 0, s = 0; // 신용카드, 삼성페이 구분
	
	public void creditCard() {
		++credit;
		try {
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Complete.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btnCC.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sPay() {
		++s;
		try {
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Complete.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btnSPay.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
