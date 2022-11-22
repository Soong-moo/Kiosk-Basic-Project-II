package fxml;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import Database.DBConnect;
import tableView.Product;

/*
 * button 10개정도? categoryId와 버튼을 눌렀을 때 해당하는 제품명 가격 개수 장바구니
 */

public class OrderController implements Initializable {

	@FXML
	private Button btnOrder;

	@FXML
	private Button popular1;
	@FXML
	private Button popular2;
	@FXML
	private Button popular3;
	@FXML
	private Button popular4;

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
	@FXML
	private Pane pnlBeverageFirst;
	@FXML
	private Pane pnlBeverageSecond;

	@FXML
	private Button plus;
	@FXML
	private Button minus;
	@FXML
	private TableView<Product> tableView;
	@FXML
	private TableColumn<Product, String> nameColumn;
	@FXML
	private TableColumn<Product, Integer> priceColumn;
	@FXML
	private TableColumn<Product, Integer> countColumn;

	public static ArrayList<Product> t = new ArrayList<>();

	int i = 0; // i = plus,minus
	String name;
	int price;
	boolean flag; // page animation

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<Product> products = tableView.getItems();
		products.addAll(t);
		tableView.setItems(products);
		tableView.setPlaceholder(new Label());
		nameColumn.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
		priceColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
		countColumn.setCellValueFactory(new PropertyValueFactory<Product, Integer>("count"));
	}

	// onAction
	public void order() throws Exception {
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Check.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btnOrder.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// 장바구니
	public void testClick(ActionEvent event) throws Exception {

		// if(event.getSource() == burger1) return; //선택 방법 1

		String btn = ((Button) event.getSource()).getId(); // 해당 버튼 id 가져옴

		switch (btn) {
		case "burger1":
			addMenu("치즈버거");
			break;
		case "burger2":
			addMenu("치킨버거");
			break;
		case "burger3":
			addMenu("불고기버거");
			break;
		case "burger4":
			addMenu("새우버거");
			break;

		case "side1":
			addMenu("감자튀김");
			break;
		case "side2":
			addMenu("치즈 감자튀김");
			break;
		case "side3":
			addMenu("베이컨 치즈 감자튀김");
			break;
		case "side4":
			addMenu("스파이시 치플레 감자튀김");
			break;
		case "side5":
			addMenu("치킨텐더");
			break;
		case "side6":
			addMenu("치즈볼");
			break;
		case "beverage1":
			addMenu("코카콜라");
			break;
		case "beverage2":
			addMenu("제로 코카콜라");
			break;
		case "beverage3":
			addMenu("스프라이트");
			break;
		case "beverage4":
			addMenu("제로 스프라이트");
			break;
		case "beverage5":
			addMenu("환타");
			break;
		case "beverage6":
			addMenu("밀크쉐이크");
			break;
		case "beverage7":
			addMenu("페리에 라임");
			break;
		case "beverage8":
			addMenu("닥터페퍼");
			break;
		case "beverage9":
			addMenu("크림소다");
			break;
		case "beverage10":
			addMenu("하이볼");
			break;
		case "beverage11":
			addMenu("진저하이볼");
			break;
		}
	}

	public void changeScene(ActionEvent event) {
		if (event.getSource() == next) {
			pnlBeverageSecond.toFront();
		} else if (event.getSource() == back) {
			pnlBeverageFirst.toFront();
		}
	}

	// 상품 수 추가
	public void plus() {
		try {
			Product p = tableView.getSelectionModel().getSelectedItem();
			ObservableList<Product> products = tableView.getItems();
			Iterator<Product> itr = t.iterator();

			while (itr.hasNext()) {
				Product exist = itr.next();
				if (exist.getName().equals(p.getName())) {
					int cnt = exist.getCount();
					exist.setCount(++cnt);
					tableView.getItems().clear();
					products.addAll(t);
					break;
				}
			}
			tableView.setItems(products);
		} catch (NullPointerException e) {
			return;
		}
	}

	// 상품 수 감소
	public void minus() {
		try {
			Product p = tableView.getSelectionModel().getSelectedItem();
			ObservableList<Product> products = tableView.getItems();
			Iterator<Product> itr = t.iterator();

			while (itr.hasNext()) {
				Product exist = itr.next();
				if (exist.getName().equals(p.getName())) {
					int cnt = exist.getCount();
					exist.setCount(--cnt);
					if (exist.getCount() <= 0) {
						t.remove(i);
					}
					tableView.getItems().clear();
					products.addAll(t);
					break;
				}
				i++;
			}
			tableView.setItems(products);
			i = 0;
		} catch (NullPointerException e) {
			return;
		}
	}

	// 중복값 알림
	public void warning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("상품 추가 오류!");
		alert.setHeaderText("장바구니에 이미 담겨져 있습니다!");
		alert.showAndWait();
	}

	// TableView 값 저장 Method
	public void addMenu(String n) {

		Iterator<Product> itr = t.iterator();
		DBConnect.test();

		while (itr.hasNext()) {
			Product product = itr.next();
			if (product.getName().equals(n)) {
				warning();
				return;
			}
		}

		try {
			while (DBConnect.rs.next()) {
				name = DBConnect.rs.getString("name");
				price = DBConnect.rs.getInt("price");
				if (name.equals(n)) {
					Product product = new Product(name, price);
					t.add(product); // ArrayList 추가
					ObservableList<Product> products = tableView.getItems();
					products.add(product);
					tableView.setItems(products);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

/*
 * public void testClick() throws Exception { DBConnect.test(); while
 * (DBConnect.rs.next()) { // product = DBConnect.rs.getInt("productId"); //
 * category = DBConnect.rs.getInt("categoryId"); name =
 * DBConnect.rs.getString("name"); price = DBConnect.rs.getInt("price"); if
 * (name.equals("감자튀김")) { t.add(new Product(name, price)); label.setText(name +
 * "\t\t" + price); break; } } }
 */

/*
 * public void test2Click() throws Exception {
 * 
 * DBConnect.test();
 * 
 * Iterator<Product> itr = t.iterator();
 * 
 * while (itr.hasNext()) { Product product = itr.next(); if
 * (product.getName().equals("치즈 감자튀김")) { warning(); return; } }
 * 
 * while (DBConnect.rs.next()) { name = DBConnect.rs.getString("name"); price =
 * DBConnect.rs.getInt("price"); if (name.equals("치즈 감자튀김")) { Product product =
 * new Product(name, price); t.add(product); // ArrayList 추가
 * ObservableList<Product> products = tableView.getItems();
 * products.add(product); } tableView.setItems(products); } }
 */