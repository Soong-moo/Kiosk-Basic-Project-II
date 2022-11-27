package fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Notone implements Initializable {
	@FXML
	private Button btnOrder;

	@FXML
	private Button burger; // 치즈버거
	@FXML
	private Button burger2; // 치킨버거
	@FXML
	private Button burger3; // 불고기버거
	@FXML
	private Button burger4; // 새우버거

	@FXML
	private Button side1; // 감자튀김
	@FXML
	private Button side2; // 치즈 감자튀김
	@FXML
	private Button side3; // 베이컨 치즈 감자튀김
	@FXML
	private Button side4; // 스파이시 치즈 감자튀김
	@FXML
	private Button side5; // 치킨텐더
	@FXML
	private Button side6; // 치즈볼

	@FXML
	private Button beverage1; // 코카콜라
	@FXML
	private Button beverage2; // 제로 코카콜라
	@FXML
	private Button beverage3; // 스프라이트
	@FXML
	private Button beverage4; // 제로 스프라이트
	@FXML
	private Button beverage5; // 환타
	@FXML
	private Button beverage6; // 밀크쉐이크
	@FXML
	private Button beverage7; // 페리에 라임
	@FXML
	private Button beverage8; // 닥터페퍼
	@FXML
	private Button beverage9; // 크림소다
	@FXML
	private Button beverage10; // 하이볼
	@FXML
	private Button beverage11; // 진저하이볼
	@FXML
	private Button next;
	@FXML
	private Button back;
	@FXML
	private Parent loader;
	
	public void setController() throws IOException {
		
	}
	
	
	public void notchease(ActionEvent event){
		try {
			loader = FXMLLoader.load(getClass().getResource("Order.fxml"));
			burger = (Button)loader.lookup("#burger1");
			if(burger.getText().equals("치-즈")) {
			burger.setText("test");
			} else {
				burger.setText("??");
			}
			//Parent view = FXMLLoader.load()

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void b() throws IOException {		
		Parent view = FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
		Scene scene = new Scene(view);
		Stage primaryStage = (Stage) back.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();	
	}


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}

	
}
