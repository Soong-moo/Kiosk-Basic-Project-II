package fxml;

import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import Database.DBConnect;
import tableView.InsertProduct;
import tableView.Product;

public class OrderController implements Initializable {

	StatisticController s = new StatisticController();

	@FXML
	private Button btnOrder; // 주문버튼
	@FXML
	private Button btnHome; // 돌아가기 버튼
	@FXML
	public Button popular1; // 인기상품 1위
	@FXML
	private Button popular2; // 인기상품 2위
	@FXML
	private Button popular3; // 인기상품 3위

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
	private Button next; // 음료 앞 넘김
	@FXML
	private Button back; // 음료 뒤 넘김
	@FXML
	private Pane pnlBeverageFirst; // 음료 앞 페이지
	@FXML
	private Pane pnlBeverageSecond; // 음료 뒷 페이지

	@FXML
	private Label burgerOption; // 옵션 추가할 버거 이름
	@FXML
	private ComboBox<String> ocombobox1, ocombobox2, ocombobox3;
	@FXML
	private Button notOption, optionChoice;
	@FXML
	private ObservableList<String> optionList1 = FXCollections.observableArrayList("선택 안함", "비프", "통새우 4개", "통새우 8개");

	private ObservableList<String> optionList2 = FXCollections.observableArrayList("선택 안함", "치즈 1장", "치즈 2장", "치즈 3장");

	private ObservableList<String> optionList3 = FXCollections.observableArrayList("선택 안함", "아보카도", "파인애플", "피클",
			"할라피뇨", "기본 야채 추가");

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
	@FXML
	private TableColumn<Product, String> option1Column;
	@FXML
	private TableColumn<Product, String> option2Column;
	@FXML
	private TableColumn<Product, String> option3Column;

	// 인기1위제품
	@FXML
	private Label burgerRank1;
	@FXML
	private Label option1Rank1;
	@FXML
	private Label option2Rank1;
	@FXML
	private Label option3Rank1;

	// 인기2위제품
	@FXML
	private Label burgerRank2;
	@FXML
	private Label option1Rank2;
	@FXML
	private Label option2Rank2;
	@FXML
	private Label option3Rank2;
	// 인기3위제품
	@FXML
	private Label burgerRank3;
	@FXML
	private Label option1Rank3;
	@FXML
	private Label option2Rank3;
	@FXML
	private Label option3Rank3;

	@FXML
	private Button updatefamous;
	@FXML
	private ImageView explanation; // 광고 + 설명서 이미지
	@FXML
	private Tab bestTab;
	@FXML
	private Tab burgerTab;
	@FXML
	private Tab sideTab;
	@FXML
	private Tab drankTab;

	public static ArrayList<Product> arrayProduct = new ArrayList<>(); // tableView ArrayList
	public static ArrayList<InsertProduct> arrayInsertProduct = new ArrayList<>(); // INSERT DB ArrayList

	int i = 0; // i = plus,minus
	int cnt; // 개수
	String name;
	int price;
	int categoryID;
	int productID;

	String productButtonInfo;
	String optionPageStr1;
	String optionPageStr2;
	String optionPageStr3;

	int optionPage1 = 99;
	int optionPage2 = 99;
	int optionPage3 = 99;

	// 클래스 내 옵션 정보
	int optionID;
	String optionName;
	int optionPrice = 0;

	int imageCnt = 0; // 설명서 반전

	int popularInfo = 0; // 인기 상품 정보

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		print(); // 통계 출력

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
		ocombobox1.setItems(optionList1);
		ocombobox2.setItems(optionList2);
		ocombobox3.setItems(optionList3);

		// 인기 1위
		burgerRank1.setText(s.productStatistics.get(0).getName());
		option1Rank1.setText(s.option1Array.get(0).getName());
		option2Rank1.setText(s.option2Array.get(0).getName());
		option3Rank1.setText(s.option3Array.get(0).getName());

		// 인기 2위
		burgerRank2.setText(s.productStatistics.get(1).getName());
		option1Rank2.setText(s.option1Array.get(1).getName());
		option2Rank2.setText(s.option2Array.get(1).getName());
		option3Rank2.setText(s.option3Array.get(1).getName());

		// 인기 3위
		burgerRank3.setText(s.productStatistics.get(2).getName());
		option1Rank3.setText(s.option1Array.get(2).getName());
		option2Rank3.setText(s.option2Array.get(2).getName());
		option3Rank3.setText(s.option3Array.get(2).getName());

	}

	// 통계 출력
	public void print() {
		s.print();
	}

	// 주문하기
	public void order() {
		if (arrayProduct.size() == 0) {
			notChoiceWarning();
			return;
		}

		try {
			Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Check.fxml"));
			Scene scene = new Scene(View);
			Stage primaryStage = (Stage) btnOrder.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 처음 화면 복귀
	public void home() {
		try {
			Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FirstPage.fxml"));
			Scene scene = new Scene(View);
			Stage primaryStage = (Stage) btnHome.getScene().getWindow();
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 장바구니
	public void menuClick(ActionEvent event) {

		// if(event.getSource() == burger1) return; //선택 방법 1

		String btn = ((Button) event.getSource()).getId(); // 해당 버튼 id 가져옴

		switch (btn) {
		case "burger1":
			productButtonInfo = "치즈버거";
			categoryID = 2;
			break;
		case "burger2":
			productButtonInfo = "치킨버거";
			categoryID = 2;
			break;
		case "burger3":
			productButtonInfo = "불고기버거";
			categoryID = 2;
			break;
		case "burger4":
			productButtonInfo = "새우버거";
			categoryID = 2;
			break;

		case "side1":
			productButtonInfo = "감자튀김";
			categoryID = 3;
			break;
		case "side2":
			productButtonInfo = "치즈 감자튀김";
			categoryID = 3;
			break;
		case "side3":
			productButtonInfo = "베이컨 치즈 감자튀김";
			categoryID = 3;
			break;
		case "side4":
			productButtonInfo = "스파이시 치플레 감자튀김";
			categoryID = 3;
			break;
		case "side5":
			productButtonInfo = "치킨텐더";
			categoryID = 3;
			break;
		case "side6":
			productButtonInfo = "치즈볼";
			categoryID = 3;
			break;
		case "beverage1":
			productButtonInfo = "코카콜라";
			categoryID = 4;
			break;
		case "beverage2":
			productButtonInfo = "제로 코카콜라";
			categoryID = 4;
			break;
		case "beverage3":
			productButtonInfo = "스프라이트";
			categoryID = 4;
			break;
		case "beverage4":
			productButtonInfo = "제로 스프라이트";
			categoryID = 4;
			break;
		case "beverage5":
			productButtonInfo = "환타";
			categoryID = 4;
			break;
		case "beverage6":
			productButtonInfo = "밀크쉐이크";
			categoryID = 4;
			break;
		case "beverage7":
			productButtonInfo = "페리에 라임";
			categoryID = 4;
			break;
		case "beverage8":
			productButtonInfo = "닥터페퍼";
			categoryID = 4;
			break;
		case "beverage9":
			productButtonInfo = "크림소다";
			categoryID = 4;
			break;
		case "beverage10":
			productButtonInfo = "하이볼";
			categoryID = 4;
			break;
		case "beverage11":
			productButtonInfo = "진저하이볼";
			categoryID = 4;
			break;
		}

		imageChange(); // 설명서 숨기기

		if (categoryID != 2) {
			addMenu(productButtonInfo, optionPrice);
		} else {
			burgerOptionLabel(); // 옵션 추가 전 메뉴 라벨
		}

	}

	// 음료 페이지 넘김
	public void changeScene(ActionEvent event) {
		if (event.getSource() == next) {
			pnlBeverageFirst.toBack();
		} else if (event.getSource() == back) {
			pnlBeverageFirst.toFront();
		}
	}

	// 상품 수 추가
	public void plus() {
		try {
			Product p = tableView.getSelectionModel().getSelectedItem();
			ObservableList<Product> products = tableView.getItems();
			Iterator<Product> itr = arrayProduct.iterator();
			Iterator<InsertProduct> insertItr = arrayInsertProduct.iterator();

			while (itr.hasNext()) {
				Product exist = itr.next();
				InsertProduct insertExist = insertItr.next();
				if (exist.getName().equals(p.getName()) && exist.getOption1().equals(p.getOption1())
						&& exist.getOption2().equals(p.getOption2()) && exist.getOption3().equals(p.getOption3())) {
					cnt = exist.getCount();
					exist.setCount(++cnt);
					insertExist.setNumber(cnt);
					exist.setPrice(exist.getPrice() + (exist.getPrice() / (cnt - 1)));
					tableView.getItems().clear();
					products.addAll(arrayProduct);
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
			Iterator<Product> itr = arrayProduct.iterator();
			Iterator<InsertProduct> insertItr = arrayInsertProduct.iterator();

			while (itr.hasNext()) {
				Product exist = itr.next();
				InsertProduct insertExist = insertItr.next();
				if (exist.getName().equals(p.getName()) && exist.getOption1().equals(p.getOption1())
						&& exist.getOption2().equals(p.getOption2()) && exist.getOption3().equals(p.getOption3())) {
					cnt = exist.getCount();
					exist.setCount(--cnt);
					insertExist.setNumber(cnt);
					exist.setPrice(exist.getPrice() - (exist.getPrice() / (cnt + 1)));
					if (exist.getCount() <= 0) {
						arrayProduct.remove(i);
					}
					tableView.getItems().clear();
					products.addAll(arrayProduct);
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

	// 옵션 페이지 모두 선택 안함 액션
	public void optionOrder1() {

		optionPage1 = 0;
		optionPage2 = 0;
		optionPage3 = 0;

		optionPageStr1 = "X";
		optionPageStr2 = "X";
		optionPageStr3 = "X";

		optionPrice = 0;

		addMenu(productButtonInfo, optionPrice);
	}

	// 옵션 페이지 선택 완료 액션
	public void optionOrder2() {

		DBConnect.optionPrice();

		if (optionList1.get(0).equals(ocombobox1.getValue())) {
			optionPage1 = 0;
			optionPageStr1 = "X";
		} else if (optionList1.get(1).equals(ocombobox1.getValue())) {
			optionPage1 = 1;
			optionPageStr1 = "비프";
		} else if (optionList1.get(2).equals(ocombobox1.getValue())) {
			optionPage1 = 2;
			optionPageStr1 = "통새우 4개";
		} else if (optionList1.get(3).equals(ocombobox1.getValue())) {
			optionPage1 = 3;
			optionPageStr1 = "통새우 8개";
		}

		if (optionList2.get(0).equals(ocombobox2.getValue())) {
			optionPage2 = 0;
			optionPageStr2 = "X";
		} else if (optionList2.get(1).equals(ocombobox2.getValue())) {
			optionPage2 = 4;
			optionPageStr2 = "치즈 1장";
		} else if (optionList2.get(2).equals(ocombobox2.getValue())) {
			optionPage2 = 5;
			optionPageStr2 = "치즈 2장";
		} else if (optionList2.get(3).equals(ocombobox2.getValue())) {
			optionPage2 = 6;
			optionPageStr2 = "치즈 3장";
		}

		if (optionList3.get(0).equals(ocombobox3.getValue())) {
			optionPage3 = 0;
			optionPageStr3 = "X";
		} else if (optionList3.get(1).equals(ocombobox3.getValue())) {
			optionPage3 = 7;
			optionPageStr3 = "아보카도";
		} else if (optionList3.get(2).equals(ocombobox3.getValue())) {
			optionPage3 = 8;
			optionPageStr3 = "파인애플";
		} else if (optionList3.get(3).equals(ocombobox3.getValue())) {
			optionPage3 = 9;
			optionPageStr3 = "피클";
		} else if (optionList3.get(4).equals(ocombobox3.getValue())) {
			optionPage3 = 10;
			optionPageStr3 = "할라피뇨";
		} else if (optionList3.get(5).equals(ocombobox3.getValue())) {
			optionPage3 = 11;
			optionPageStr3 = "기본 야채 추가";
		}

		if (optionPage1 == 99 || optionPage2 == 99 || optionPage3 == 99) {
			optionNotChoiceWarning();
		} else {
			int extra; // 추가 요금
			optionPrice = 0;

			try {
				while (DBConnect.rs.next()) {
					optionID = DBConnect.rs.getInt("optionId");
					optionName = DBConnect.rs.getString("optionName");
					extra = DBConnect.rs.getInt("optionPrice");

					if (optionID == optionPage1) {
						optionPrice += extra;
					}
					if (optionID == optionPage2) {
						optionPrice += extra;
					}
					if (optionID == optionPage3) {
						optionPrice += extra;
					}
				}

				addMenu(productButtonInfo, optionPrice);

				ocombobox1.getSelectionModel().selectFirst();
				ocombobox2.getSelectionModel().selectFirst();
				ocombobox3.getSelectionModel().selectFirst();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	// 인기 1,2,3 확인
	public void checkPopular1() {
		popularInfo = 0;
		addPopular(popularInfo);
	}

	public void checkPopular2() {
		popularInfo = 1;
		addPopular(popularInfo);
	}

	public void checkPopular3() {
		popularInfo = 2;
		addPopular(popularInfo);
	}

	// 인기 상품 TableView 값 저장
	public void addPopular(int n) {
		int orderID = 0;
		int p_price = 0;
		int o1_price = 0;
		int o2_price = 0;
		int o3_price = 0;

		Iterator<Product> itr = arrayProduct.iterator();

		DBConnect.getProductData();

		// 중복 검사
		while (itr.hasNext()) {
			Product product = itr.next();
			if (product.getName().equals(s.productStatistics.get(n).getName())
					&& product.getOption1().equals(s.option1Array.get(n).getName())
					&& product.getOption2().equals(s.option2Array.get(n).getName())
					&& product.getOption3().equals(s.option3Array.get(n).getName())) {
				warning();
				return;
			}
		}

		// 상품 ID 검색
		if (s.productStatistics.get(n).getName().equals("치즈버거")) {
			productID = 1;
			p_price = 4000;
		} else if (s.productStatistics.get(n).getName().equals("치킨버거")) {
			productID = 2;
			p_price = 4500;
		} else if (s.productStatistics.get(n).getName().equals("불고기버거")) {
			productID = 3;
			p_price = 5000;
		} else if (s.productStatistics.get(n).getName().equals("새우버거")) {
			productID = 4;
			p_price = 5000;
		}

		// 옵션 ID 검색
		if (s.option1Array.get(n).getName().equals("선택 안함")) {
			optionPage1 = 0;
			o1_price = 0;
		} else if (s.option1Array.get(n).getName().equals("비프")) {
			optionPage1 = 1;
			o1_price = 1500;
		} else if (s.option1Array.get(n).getName().equals("통새우 4개")) {
			optionPage1 = 2;
			o1_price = 1000;
		} else if (s.option1Array.get(n).getName().equals("통새우 8개")) {
			optionPage1 = 3;
			o1_price = 2000;
		}

		if (s.option2Array.get(n).getName().equals("선택 안함")) {
			optionPage2 = 0;
			o2_price = 0;
		} else if (s.option2Array.get(n).getName().equals("치즈 1장")) {
			optionPage2 = 4;
			o2_price = 500;
		} else if (s.option2Array.get(n).getName().equals("치즈 2장")) {
			optionPage2 = 5;
			o2_price = 1000;
		} else if (s.option2Array.get(n).getName().equals("치즈 3장")) {
			optionPage2 = 6;
			o2_price = 1500;
		}

		if (s.option3Array.get(n).getName().equals("선택 안함")) {
			optionPage3 = 0;
			o3_price = 0;
		} else if (s.option3Array.get(n).getName().equals("아보카도")) {
			optionPage3 = 7;
			o3_price = 1500;
		} else if (s.option3Array.get(n).getName().equals("파인애플")) {
			optionPage3 = 8;
			o3_price = 1000;
		} else if (s.option3Array.get(n).getName().equals("피클")) {
			optionPage3 = 9;
			o3_price = 500;
		} else if (s.option3Array.get(n).getName().equals("할라피뇨")) {
			optionPage3 = 10;
			o3_price = 500;
		} else if (s.option3Array.get(n).getName().equals("기본 야채 추가")) {
			optionPage3 = 11;
			o3_price = 1000;
		}

		try {

			while (DBConnect.rs.next()) {
				name = DBConnect.rs.getString("name");
				price = DBConnect.rs.getInt("price");
				categoryID = DBConnect.rs.getInt("categoryId");
				productID = DBConnect.rs.getInt("productId");
				if (name.equals(s.productStatistics.get(n).getName()))
					break;
			}

			DBConnect.getOrderID(); // 주문번호 가져옴

			while (DBConnect.rs.next()) {
				orderID = DBConnect.rs.getInt("orderID");
			}

			if (name.equals(s.productStatistics.get(n).getName())) {
				Product product = new Product(s.productStatistics.get(n).getName(),
						p_price + o1_price + o2_price + o3_price, s.option1Array.get(n).getName(),
						s.option2Array.get(n).getName(), s.option3Array.get(n).getName());
				InsertProduct insertProduct = new InsertProduct(orderID + 1, 2, productID, optionPage1, optionPage2,
						optionPage3, 1);
				arrayProduct.add(product); // ArrayList 추가
				arrayInsertProduct.add(insertProduct); // insert ArrayList
				ObservableList<Product> products = tableView.getItems();
				products.add(product);
				tableView.setItems(products);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// TableView 값 저장 Method
	public void addMenu(String n, int oPrice) {

		int orderID = 0;

		Iterator<Product> itr = arrayProduct.iterator();
		DBConnect.getProductData();

		while (itr.hasNext()) {
			Product product = itr.next();
			if (product.getName().equals(n) && product.getOption1().equals(optionPageStr1)
					&& product.getOption2().equals(optionPageStr2) && product.getOption3().equals(optionPageStr3)) {
				warning();
				return;
			} else if (product.getName().equals(n) && categoryID != 2) {
				warning();
				return;
			}
		}

		try {
			while (DBConnect.rs.next()) {
				name = DBConnect.rs.getString("name");
				price = DBConnect.rs.getInt("price");
				categoryID = DBConnect.rs.getInt("categoryId");
				productID = DBConnect.rs.getInt("productId");
				if (name.equals(n))
					break;
			}

			DBConnect.getOrderID();

			while (DBConnect.rs.next()) {
				orderID = DBConnect.rs.getInt("orderID");
			}

			if (name.equals(n) && categoryID == 2) {
				Product product = new Product(name, price + oPrice, optionPageStr1, optionPageStr2, optionPageStr3);
				InsertProduct insertProduct = new InsertProduct(orderID + 1, categoryID, productID, optionPage1,
						optionPage2, optionPage3, 1);
				arrayProduct.add(product); // ArrayList 추가
				arrayInsertProduct.add(insertProduct); // insert ArrayList
				ObservableList<Product> products = tableView.getItems();
				products.add(product);
				tableView.setItems(products);
			} else if (name.equals(n) && categoryID != 2) {
				Product product = new Product(name, price, "-", "-", "-");
				InsertProduct insertProduct = new InsertProduct(orderID + 1, categoryID, productID, 0, 0, 0, 1);
				arrayProduct.add(product); // ArrayList 추가
				arrayInsertProduct.add(insertProduct); // insert ArrayList
				ObservableList<Product> products = tableView.getItems();
				products.add(product);
				tableView.setItems(products);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		imageChange(); // 설명서 나오기
	}

	// 미선택 주문시
	public void notChoiceWarning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("상품이 선택 되지 않음!");
		alert.setHeaderText("상품 선택 후 주문해주세요!");
		alert.showAndWait();
	}

	// 중복값 알림
	public void warning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("상품 추가 오류!");
		alert.setHeaderText("장바구니에 이미 담겨져 있습니다!");
		alert.showAndWait();
	}

	// 옵션 선택 안함 알림
	public void optionNotChoiceWarning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("옵션이 비어있음!");
		alert.setHeaderText("옵션을 선택해주세요!");
		alert.showAndWait();
	}

	// 탭 클릭 시 이미지 전환
	public void tabImageChange() {
		if (bestTab.isSelected() == false) {
			explanation.setImage(new Image("/img/상품 페이지 설명서1.png"));
		}
	}

	// 광고 이미지 숨기기
	public void imageChange() {
		if (imageCnt == 0) {
			explanation.toBack();
			imageCnt = 1;
		} else {
			explanation.toFront();
			imageCnt = 0;
		}
	}

	// 옵션 선택 버거 이름 출력
	public void burgerOptionLabel() {
		burgerOption.setText(productButtonInfo);
	}
}