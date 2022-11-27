package fxml;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NotoneFake {
	@FXML
	private Button btnOrder;


	@FXML
	private Button burger1; // 치즈버거
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
	
	
	public void notchease(ActionEvent event) throws Exception {
		// TODO Auto-generated method stub
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FirstPage.fxml")); // 주문 불가 상품을 다시 클릭했으니 맨 처음의 상태로 돌아감 
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) burger1.getScene().getWindow();
	
		primaryStage.setScene(scene);
		primaryStage.show();	
		
	}

	
}
