package fxml;

import java.util.Optional;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class FirstPageControllerFake{
	

	@FXML
	private Button btnPickUp;
	@FXML
	private Button btnTakeOut;
	@FXML
	private Button btnAdmin;

	int pick = 0, take = 0;
	
	public void pickUp() throws Exception {
		
			pick++;
			Alert a = new Alert(AlertType.CONFIRMATION);
			if (a.showAndWait().get() == ButtonType.OK) {
				Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Notorder.fxml")); //주문 불가 표시가 되어있는 버전의 order fxml로 이동
				Scene scene = new Scene(View);
				Stage primaryStage = (Stage) btnPickUp.getScene().getWindow();
				primaryStage.setScene(scene);
				primaryStage.show();
			}
	}

	public void takeOut() throws Exception {
			take++;
			Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Notorder.fxml")); //주문 불가 표시가 되어있는 버전의 order fxml로 이동 
			Scene scene = new Scene(View);
			Stage primaryStage = (Stage) btnTakeOut.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		
	}

	public void connectAdmin() throws Exception {
		
		Dialog<String> dialog = new Dialog<>();
		dialog.setContentText("Please enter your name:");
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
			Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/ManagerFake.fxml")); // 주문 불가 상태에서 관리자모드 진입 
			Scene scene = new Scene(View);
			Stage primaryStage = (Stage) btnAdmin.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		}
	}
	
	
	
	
}

/*
 * String input = null;
 * 
 * TextInputDialog dialog = new TextInputDialog();
 * dialog.setContentText("Please enter your name:");
 * 
 * Optional<String> result = dialog.showAndWait();
 * 
 * if(result.isPresent()) { input = dialog.getEditor().getText(); }
 * 
 * if(input.equals("test")) { Parent View =
 * FXMLLoader.load(getClass().getClassLoader().getResource("fxml/test.fxml"));
 * Scene scene = new Scene(View); Stage primaryStage = (Stage)
 * btnAdmin.getScene().getWindow(); primaryStage.setScene(scene);
 * primaryStage.show(); }
 * 
 * }
 */
