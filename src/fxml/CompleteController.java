package fxml;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CompleteController {

	@FXML
	private Button btnComplete;
	
	public void complete() throws Exception {
		OrderController.arrayProduct.clear(); // 상품 초기화
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FirstPage.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btnComplete.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
