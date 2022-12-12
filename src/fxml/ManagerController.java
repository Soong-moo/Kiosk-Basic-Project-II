package fxml;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ManagerController {

	@FXML
	private Button btnSalesVolume; // 통계
	@FXML
	private Button btnSystemClose; // 키오스크 종료
	@FXML
	private Button btnExit; // 나가기
	@FXML
	private AnchorPane informationPane;	//관지라-정보

	public void salesvolume() {
		try {
			Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Statistic.fxml"));
			Scene scene = new Scene(View);
			Stage primaryStage = (Stage) btnSalesVolume.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void exitmanager() {
		try {
			Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FirstPage.fxml"));
			Scene scene = new Scene(View);
			Stage primaryStage = (Stage) btnExit.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	//제작자 정보 출력
	public void informationSelect() {
		informationPane.toFront();
	}
	
	//정보 나가기
	public void informationExitSelect() {
		informationPane.toBack();
	}
	
	
	//키오스크 종료
	public void systemClose() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("키오스크 종료");
		alert.setHeaderText(null);
		alert.setContentText("키오스크를 종료합니다.");
		alert.showAndWait();
		System.exit(0);
	}

}