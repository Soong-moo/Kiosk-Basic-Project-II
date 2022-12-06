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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import Database.DBConnect;
import tableView.InsertProduct;
import tableView.Product;
/*
 * button 10개정도? categoryId와 버튼을 눌렀을 때 해당하는 제품명 가격 개수 장바구니
 */

public class OrderController implements Initializable {

	DBConnect dbc = new DBConnect();
	StatisticController s = new StatisticController();
	
	@FXML
	private Button btnOrder; // 주문버튼
	@FXML
	private Button bthome;
	@FXML
	public Button popular1;
	@FXML
	private Button popular2;
	@FXML
	private Button popular3;

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
	
	@FXML
	private ImageView danger1;	//광고 + 설명서 이미지
	
	@FXML
	private Tab	bestTab; // 인기 탭
	@FXML
	private Tab burgerTab; // 버거 탭
	@FXML
	private Tab sideTab; // 사이드 탭
	@FXML
	private Tab drankTab; // 음료 탭
	
	
	
	

	public static ArrayList<Product> arrayProduct = new ArrayList<>(); // tableView ArrayList
	public static ArrayList<InsertProduct> arrayInsertProduct = new ArrayList<>(); // INSERT DB ArrayList  
	

	int i = 0; // i = plus,minus
	int cnt; // 개수
	String name;
	int price;
	int categoryID;
	int productID;

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
	
	int imageCnt=0;		//설명서 았다리갔다리

	
	//탭 클릭 시 이미지 전환
	public void tabImageChange() {
		if (bestTab.isSelected() == false) {
			danger1.setImage(new Image("/img/고양이설명서.jpeg"));
		}
	}
	
	
	
	//광고 이미지 숨기기
	public void imageChange() {
		if(imageCnt == 0) {
			danger1.toBack();
			imageCnt =1;
		}
		else {
			danger1.toFront();
			imageCnt = 0;
		}
	}
	

	// 버거 메뉴 확인
	public void opInfoLabel() {
		opInfo1.setText(productButtonInfo);
	}
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		t();
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
        
        //인기 1위
        FemptyH.setText(s.productStatistics.get(0).getName());
        FemptyO1.setText(s.option1Array.get(0).getName());
        FemptyO2.setText(s.option2Array.get(0).getName());
        FemptyO3.setText(s.option3Array.get(0).getName());
        
        //인기 2위
        FemptyH1.setText(s.productStatistics.get(1).getName());
        FemptyO11.setText(s.option1Array.get(1).getName());
        FemptyO21.setText(s.option2Array.get(1).getName());
        FemptyO31.setText(s.option3Array.get(1).getName());
        
        //인기 3위
        FemptyH2.setText(s.productStatistics.get(2).getName());
        FemptyO12.setText(s.option1Array.get(2).getName());
        FemptyO22.setText(s.option2Array.get(2).getName());
        FemptyO32.setText(s.option3Array.get(2).getName());

	}

	// onAction
	public void order() throws Exception {
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Check.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btnOrder.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void home() throws Exception {
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/FirstPage.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) bthome.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	// 장바구니
	public void testClick(ActionEvent event) throws Exception {

		// if(event.getSource() == burger1) return; //선택 방법 1

		String btn = ((Button) event.getSource()).getId(); // 해당 버튼 id 가져옴

		switch (btn) {
		case "popular1" :
			break;
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
	
		imageChange();		//설명서 숨기기
    	
		
    	if(categoryID != 2) {
    		addMenu(productButtonInfo, optionPrice);
    	} else if(btn == "popular1" || btn == "popular2" || btn == "popular3") {
    		return;
    	}
    	else {
    		opInfoLabel();
    	}
		
    	
		
	}

	//음료 화면 전환
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
				if (exist.getName().equals(p.getName()) && exist.getOption1().equals(p.getOption1()) && exist.getOption2().equals(p.getOption2()) && exist.getOption3().equals(p.getOption3())) {
					cnt = exist.getCount();
					exist.setCount(++cnt);
					insertExist.setNumber(cnt);
					exist.setPrice(exist.getPrice() + (exist.getPrice()/(cnt-1)));
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
				if (exist.getName().equals(p.getName()) && exist.getOption1().equals(p.getOption1()) && exist.getOption2().equals(p.getOption2()) && exist.getOption3().equals(p.getOption3())) {
					cnt = exist.getCount();
					exist.setCount(--cnt);
					insertExist.setNumber(cnt);
					exist.setPrice(exist.getPrice() - (exist.getPrice()/(cnt+1)));
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

	// 옵션 선택 안함 경고
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
					optionPrice += tmp;
				}
				if (optionID == optionPage2) {
					optionPrice += tmp;
				}
				if (optionID == optionPage3) {
					optionPrice += tmp;
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
		
		int orderID = 0;
		
		Iterator<Product> itr = arrayProduct.iterator();
		DBConnect.test();
		
		while (itr.hasNext()) {
			Product product = itr.next();
			if (product.getName().equals(n) && product.getOption1().equals(optionPageStr1) && product.getOption2().equals(optionPageStr2) && product.getOption3().equals(optionPageStr3)) {
				warning();
				return;
			}
			else if (product.getName().equals(n) && categoryID != 2) {
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
			
			dbc.getOrderID();
			
			while(dbc.rs.next()) {
				orderID = dbc.rs.getInt("orderID");
			}
			
			if (name.equals(n) && categoryID ==2) {
				Product product = new Product(name, price+oPrice, optionPageStr1, optionPageStr2, optionPageStr3);
				InsertProduct insertProduct = new InsertProduct(orderID + 1, categoryID, productID, optionPage1, optionPage2, optionPage3, 1);
				arrayProduct.add(product); // ArrayList 추가
				arrayInsertProduct.add(insertProduct); // insert ArrayList
				ObservableList<Product> products = tableView.getItems();
				products.add(product);
				tableView.setItems(products);
			}
			else if (name.equals(n) && categoryID != 2) {
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
		imageChange();		//설명서 나오기
	}
	
	public void t() {
		s.test();
	}

}