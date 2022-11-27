package fxml;

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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
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

public class NotOrderController implements Initializable {

	@FXML
	private Button btnOrder; // 주문버튼
	@FXML
	private Button bthome;
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
	private Button next; // 음료 앞 넘김
	@FXML
	private Button back; // 음료 뒤 넘김
	@FXML
	private Pane pnlBeverageFirst;
	@FXML
	private Pane pnlBeverageSecond;

	@FXML
	private Label oLabel1, oLabel2, oLabel3, opInfo1;
	@FXML
	private ComboBox<String> ocombobox1, ocombobox2, ocombobox3;
	@FXML
	private Button obutton1, obutton2;
	@FXML
	private ObservableList<String> olist1 = FXCollections.observableArrayList("선택 안함", "비프", "통새우 4개", "통새우 8개");

	private ObservableList<String> olist2 = FXCollections.observableArrayList("선택 안함", "치즈 1장", "치즈 2장", "치즈 3장");

	private ObservableList<String> olist3 = FXCollections.observableArrayList("선택 안함", "아보카도", "파인애플", "피클", "할라피뇨", "기본 야채 추가");

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
	
	//인기1위제품
	@FXML
	private Label FemptyH; //인기햄버거 출력문
	@FXML
	private Label FemptyO1;
	@FXML
	private Label FemptyO2;
	@FXML
	private Label FemptyO3;
	//인기2위제품
	@FXML
	private Label FemptyH1; //인기햄버거 출력문
	@FXML
	private Label FemptyO11;
	@FXML
	private Label FemptyO21;
	@FXML
	private Label FemptyO31;
	//인기3위제품
	@FXML
	private Label FemptyH2; //인기햄버거 출력문
	@FXML
	private Label FemptyO12;
	@FXML
	private Label FemptyO22;
	@FXML
	private Label FemptyO32;
	@FXML
	private Button updatefamous;

	public static ArrayList<Product> arrayProduct = new ArrayList<>();

	int i = 0; // i = plus,minus
	String name;
	int price;
	int categoryID;

	String p_name;
	int p_price;

	String productButtonInfo;
	String optionPageStr1;
	String optionPageStr2;
	String optionPageStr3;
	
	int optionPage1=99;
	int optionPage2=99;
	int optionPage3=99;
	
	int optionID;
	String  optionName;
	int optionPrice=0;
	
	//인기상품 예시 -> db에서 통계량 뽑아서 넣어야함//
	public String h = "cheese";
	public String o1 = "meat";
	public String o2 = "tomato";
	public String o3 = "njtl";
	
	public String h1 = "meat1";
	public String o11 = "meat2";
	public String o21 = "mea3t";
	public String o31 = "meat4";
	
	public String h2 = "me3at";
	public String o12 = "me2at";
	public String o22 = "m5eat";
	public String o32 = "me7at";
	
	
	
	
	
	public void opInfoLabel() {
		opInfo1.setText(productButtonInfo);
	}
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
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
		
        ocombobox1.setItems(olist1);    
        ocombobox2.setItems(olist2);  
        ocombobox3.setItems(olist3); 
	}
/*
    //옵션 change
    public void comboChange(ActionEvent event) {
        oLabel1.setText(ocombobox1.getValue());
        oLabel2.setText(ocombobox2.getValue());
        oLabel3.setText(ocombobox3.getValue());
    }
    */

	// onAction
	public void order() throws Exception {
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Check.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btnOrder.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void home() throws Exception {
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FirstPageFake.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) bthome.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	//인기상품 라벨에 메뉴넣는 메소드
		public void PopularHambuger1(ActionEvent e) {
			FemptyH.setText(h);
			FemptyO1.setText(o1);
			FemptyO2.setText(o2);
			FemptyO3.setText(o3);
			
			FemptyH1.setText(h1);
			FemptyO11.setText(o11);
			FemptyO21.setText(o21);
			FemptyO31.setText(o31);
			
			FemptyH2.setText(h2);
			FemptyO12.setText(o12);
			FemptyO22.setText(o22);
			FemptyO32.setText(o32);
		}
	

	// 장바구니
	public void testClick(ActionEvent event) throws Exception {
		
		
		

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
	
    	
		
    	if(categoryID != 2) {
    		addMenu(productButtonInfo, optionPrice);
    	}
    	else {
    		opInfoLabel();
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
			Iterator<Product> itr = arrayProduct.iterator();

			while (itr.hasNext()) {
				Product exist = itr.next();
				if (exist.getName().equals(p.getName())) {
					int cnt = exist.getCount();
					exist.setCount(++cnt);
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

			while (itr.hasNext()) {
				Product exist = itr.next();
				if (exist.getName().equals(p.getName())) {
					int cnt = exist.getCount();
					exist.setCount(--cnt);
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

	// 중복값 알림
	public void warning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("상품 추가 오류!");
		alert.setHeaderText("장바구니에 이미 담겨져 있습니다!");
		alert.showAndWait();
	}


	// 중복값 알림
	public void optionNotChoiceWarning() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("옵션이 비어있음!");
		alert.setHeaderText("옵션을 선택해주세요!");
		alert.showAndWait();
	}
	
	
	//옵션 페이지 모두 선택 안함 액션
	public void optionOrder1() throws Exception {
		optionPage1 = 0;
		optionPage2 = 0;
		optionPage3 = 0;
		optionPageStr1 = "X";
		optionPageStr2 = "X";
		optionPageStr3 = "X";
		
		optionPrice = 0;
		
		addMenu(productButtonInfo, optionPrice);
	}
    
    //옵션 페이지 선택 완료 액션
    public void optionOrder2() throws IOException, SQLException {
    	DBConnect.optionPrice();
    	
    	
    	
    	
    	
    	
		if (olist1.get(0).equals(ocombobox1.getValue())) {
			optionPage1 = 0;
			optionPageStr1 = "X";
		}
		else if (olist1.get(1).equals(ocombobox1.getValue())) {
			optionPage1 = 1;
			optionPageStr1 = "비프";
		}
		else if (olist1.get(2).equals(ocombobox1.getValue())) {
			optionPage1 = 2;	
			optionPageStr1 = "통새우 4개";
		}
		else if (olist1.get(3).equals(ocombobox1.getValue())) {
			optionPage1 = 3;
			optionPageStr1 = "통새우 8개";
		}
		
			
		if (olist2.get(0).equals(ocombobox2.getValue())) {
			optionPage2 = 0;
			optionPageStr2 = "X";
		}
		else if (olist2.get(1).equals(ocombobox2.getValue())) {
			optionPage2 = 4;
			optionPageStr2 = "치즈 1장";
		}
		else if (olist2.get(2).equals(ocombobox2.getValue())) {
			optionPage2 = 5;
			optionPageStr2 = "치즈 2장";
		}
		else if (olist2.get(3).equals(ocombobox2.getValue())) {
			optionPage2 = 6;
			optionPageStr2 = "치즈 3장";
		}
		
		
			
		if (olist3.get(0).equals(ocombobox3.getValue())) {
			optionPage3 = 0;
			optionPageStr3 = "X";
		}
		else if (olist3.get(1).equals(ocombobox3.getValue())) {
			optionPage3 = 7;
			optionPageStr3 = "아보카도";
		}
		else if (olist3.get(2).equals(ocombobox3.getValue())) {
			optionPage3 = 8;
			optionPageStr3 = "파인애플";
		}
		else if (olist3.get(3).equals(ocombobox3.getValue())) {
			optionPage3 = 9;
			optionPageStr3 = "피클";
		}
		else if (olist3.get(4).equals(ocombobox3.getValue())) {
			optionPage3 = 10;
			optionPageStr3 = "할라피뇨";
		}
		else if (olist3.get(5).equals(ocombobox3.getValue())) {
			optionPage3 = 11;
			optionPageStr3 = "기본 야채 추가";
		}
		
		if(optionPage1 == 99 || optionPage2 == 99 || optionPage3 ==99) {
			optionNotChoiceWarning();
		}
		else {
			int tmp;
			optionPrice=0;
		
			while (DBConnect.rsp.next()) {
				optionID = DBConnect.rsp.getInt("optionId");
				optionName = DBConnect.rsp.getString("optionName");
				tmp = DBConnect.rsp.getInt("optionPrice");
				if (optionID == optionPage1) {
					optionPrice = optionPrice + tmp;
				}
				if (optionID == optionPage2) {
					optionPrice = optionPrice + tmp;
				}
				if (optionID == optionPage3) {
					optionPrice = optionPrice + tmp;
				}
			}
			
			addMenu(productButtonInfo, optionPrice);
			
			ocombobox1.getSelectionModel().selectFirst();
			ocombobox2.getSelectionModel().selectFirst();
			ocombobox3.getSelectionModel().selectFirst();
		}
		
    }
	
	
	
	

	// TableView 값 저장 Method
	public void addMenu(String n, int oPrice) throws SQLException {

		Iterator<Product> itr = arrayProduct.iterator();
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
				categoryID = DBConnect.rs.getInt("categoryId");
				
				if (name.equals(n))
					break;
			}
			
			
			if (name.equals(n) && categoryID ==2) {
				Product product = new Product(name, price+oPrice, optionPageStr1, optionPageStr2, optionPageStr3);
				arrayProduct.add(product); // ArrayList 추가
				ObservableList<Product> products = tableView.getItems();
				products.add(product);
				tableView.setItems(products);
			}
			else if (name.equals(n) && categoryID != 2) {
				Product product = new Product(name, price, "-", "-", "-");
				arrayProduct.add(product); // ArrayList 추가
				ObservableList<Product> products = tableView.getItems();
				products.add(product);
				tableView.setItems(products);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}