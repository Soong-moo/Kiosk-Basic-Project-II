package fxml;

import java.net.URL;

import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import tableView.Product;

public class CheckController implements Initializable {

	@FXML
	private Button btnPay; // 결제
	@FXML
	private Button btnCancel; // 취소
	@FXML
	private Button btnCoupon; //할인 쿠폰
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ArrayList<Product> arrayProduct = OrderController.arrayProduct;
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
	}

	public void pay() {
		try {
			Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/PaymentMethod.fxml"));
			Scene scene = new Scene(View);
			Stage primaryStage = (Stage) btnPay.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void cancel() {
		try {
			Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Order.fxml"));
			Scene scene = new Scene(View);
			Stage primaryStage = (Stage) btnCancel.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//할인 쿠폰
	public void coupon() {
		Dialog<String> dialog = new Dialog<>();
		dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

		TextField couponNumber = new TextField();
		HBox content = new HBox();
		content.getChildren().addAll(new Label("쿠폰 번호 입력:"), couponNumber);
		dialog.getDialogPane().setContent(content);

		String input = null;
		Optional<String> result = dialog.showAndWait();

		if (result.isPresent()) {
			input = couponNumber.getText();
		}

		if (input.equals("1220")) {
			try {
				int i = 0;
				ArrayList<Product> arrayProduct = OrderController.arrayProduct;
				while(i < arrayProduct.size()) {
					int price = arrayProduct.get(i).getPrice();
					price = price / 10 * 9;
					arrayProduct.get(i).setPrice(price);
					i++;
				}
				ObservableList<Product> products = tableView.getItems();
				products.clear();
				products.addAll(arrayProduct);
				tableView.setItems(products);
				tableView.setPlaceholder(new Label());
				nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
				priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
				countColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("count"));
				option1Column.setCellValueFactory(new PropertyValueFactory<Product, String>("option1"));
				option2Column.setCellValueFactory(new PropertyValueFactory<Product, String>("option2"));
				option3Column.setCellValueFactory(new PropertyValueFactory<Product, String>("option3"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
