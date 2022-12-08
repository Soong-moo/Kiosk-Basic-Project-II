package fxml;

import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;

import javafx.stage.Stage;

public class FirstPageController implements Initializable {

	@FXML
	private Button btnPickUp;
	@FXML
	private Button btnTakeOut;
	@FXML
	private Button btnAdmin;

	static int pick = 0, take = 0; // 포장 매장 구분

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		OrderController.arrayProduct.clear(); // 상품 초기화
	}

	public void pickUp() {
		++pick;
		try {
			Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Order.fxml"));
			Scene scene = new Scene(View);
			Stage primaryStage = (Stage) btnPickUp.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void takeOut() {
		++take;
		try {
			Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Order.fxml"));
			Scene scene = new Scene(View);
			Stage primaryStage = (Stage) btnTakeOut.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void connectAdmin() {
		Dialog<String> dialog = new Dialog<>();
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		PasswordField pwd = new PasswordField();
		HBox content = new HBox();
		content.getChildren().addAll(new Label("password :"), pwd);
		dialog.getDialogPane().setContent(content);

		String input = null;
		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {
			input = pwd.getText();
		}

		if (input.equals("test")) {
			try {
				Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Manager.fxml"));
				Scene scene = new Scene(View);
				Stage primaryStage = (Stage) btnAdmin.getScene().getWindow();
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			//비밀번호 불일치시
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("비밀번호 불일치!");
			alert.setHeaderText("다시 입력해주세요!");
			alert.showAndWait();
			
		}
	}
}
