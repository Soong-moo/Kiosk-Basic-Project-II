package fxml;

import java.net.URL;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;

import tableView.InsertProduct;
import tableView.Product;

import Database.DBConnect;

public class CompleteController implements Initializable {

	@FXML
	private Button btnComplete; // 완료 버튼
	@FXML
	private Label lblOrderNo; // 주문 번호 출력 라벨
	@FXML
	private Label lblEatingWay; // 식사 방법 출력 라벨
	@FXML
	private Label lblPayment; // 결제 방법 출력 라벨
	@FXML
	private Label lblPrice; // 총 금액 출력 라벨

	// 테이블 뷰
	@FXML
	private TableView<Product> tableView;
	@FXML
	private TableColumn<Product, String> nameColumn;
	@FXML
	private TableColumn<Product, Integer> priceColumn;
	@FXML
	private TableColumn<Product, Integer> countColumn;
	@FXML
	private TableColumn<Product, String> option1Column;
	@FXML
	private TableColumn<Product, String> option2Column;
	@FXML
	private TableColumn<Product, String> option3Column;

	String payment; // 결제 방법
	String eatingWay; // 매장 or 포장

	int price; // 총 금액
	int orderNum = 0; // 주문번호

	ArrayList<Product> arrayProduct = OrderController.arrayProduct;
	ArrayList<InsertProduct> arrayInsertProduct = OrderController.arrayInsertProduct;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		DBConnect.getOrderID();

		// 주문번호 가져오기
		try {
			while (DBConnect.rs.next()) {
				orderNum = DBConnect.rs.getInt("orderID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (FirstPageController.pick == 1) {
			eatingWay = "매장";
		} else if (FirstPageController.take == 1) {
			eatingWay = "포장";
		}

		if (PaymentMethodController.credit == 1) {
			payment = "신용카드";
		} else if (PaymentMethodController.s == 1) {
			payment = "SamsungPay";
		}

		for (int i = 0; i < arrayProduct.size(); i++) {
			price += arrayProduct.get(i).getPrice();
		}

		// DB 값 넣기
		DBConnect.insertOrderData(arrayInsertProduct.get(0).getOrderID(), price, payment, eatingWay);

		for (int i = 0; i < arrayInsertProduct.size(); i++) {
			DBConnect.insertSelectData(arrayInsertProduct.get(i).getOrderID(),
					arrayInsertProduct.get(i).getCategoryID(),
					arrayInsertProduct.get(i).getProductID(),
					arrayInsertProduct.get(i).getOption1ID(),
					arrayInsertProduct.get(i).getOption2ID(),
					arrayInsertProduct.get(i).getOption3ID(),
					arrayInsertProduct.get(i).getNumber());
		}

		ObservableList<Product> products = tableView.getItems();
		products.addAll(arrayProduct);
		tableView.setItems(products);
		tableView.setPlaceholder(new Label());
		nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
		countColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("count"));
		option1Column.setCellValueFactory(new PropertyValueFactory<Product, String>("option1"));
		option2Column.setCellValueFactory(new PropertyValueFactory<Product, String>("option2"));
		option3Column.setCellValueFactory(new PropertyValueFactory<Product, String>("option3"));

		lblPayment.setText(payment);
		lblEatingWay.setText(eatingWay);
		lblPrice.setText(String.format("%d원", price));
		lblOrderNo.setText(String.format("%04d", ++orderNum));

		// 선택 방법 초기화
		FirstPageController.pick = 0;
		FirstPageController.take = 0;

		// 결제 방법 초기화
		PaymentMethodController.credit = 0;
		PaymentMethodController.s = 0;
	}

	public void complete() {

		arrayInsertProduct.clear(); // DB INSERT arrayList 초기화

		try {
			Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FirstPage.fxml"));
			Scene scene = new Scene(View);
			Stage primaryStage = (Stage) btnComplete.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
