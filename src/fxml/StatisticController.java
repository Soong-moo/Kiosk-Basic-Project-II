package fxml;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Database.DBConnect;
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
import tableView.ProductStatistics;

public class StatisticController implements Initializable{

	@FXML
	private Button btnoexit;
	
	@FXML
	private TableView<ProductStatistics> hamburgerTable;
	@FXML
	private TableColumn<ProductStatistics, String> hamburgerName;
	@FXML
	private TableColumn<ProductStatistics, Integer> hamburgerCount;
	@FXML
	private TableView<ProductStatistics> optionsTable;
	@FXML 
	private TableColumn<ProductStatistics, String> optionsName;
	@FXML 
	private TableColumn<ProductStatistics, Integer> optionsCount;
	@FXML
	private TableView<ProductStatistics> option1Table;
	@FXML 
	private TableColumn<ProductStatistics, String> option1Name;
	@FXML 
	private TableColumn<ProductStatistics, Integer> option1Count;
	@FXML
	private TableView<ProductStatistics> option2Table;
	@FXML 
	private TableColumn<ProductStatistics, String> option2Name;
	@FXML 
	private TableColumn<ProductStatistics, Integer> option2Count;
	@FXML
	private TableView<ProductStatistics> option3Table;
	@FXML 
	private TableColumn<ProductStatistics, String> option3Name;
	@FXML 
	private TableColumn<ProductStatistics, Integer> option3Count;
	
	
	
	
	String name; //db 버거 이름 저장
	int count; // db 버거 판매 개수 저장
	String optsName; //db 옵션 통합 이름 저장
	int optsCount; //db 옵션 통합 개수 저장
	String opt1Name;
	int opt1Count;
	String opt2Name;
	int opt2Count;
	String opt3Name;
	int opt3Count;
	
	 ArrayList<ProductStatistics> productStatistics = new ArrayList<>();
	 ArrayList<ProductStatistics> optionsArray = new ArrayList<>();
	 ArrayList<ProductStatistics> option1Array = new ArrayList<>();
	 ArrayList<ProductStatistics> option2Array = new ArrayList<>();
	 ArrayList<ProductStatistics> option3Array = new ArrayList<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
//		try {
//			//버거 통계량
//			DBConnect.burgerStatistics();
//			while(DBConnect.rs.next()) {
//				name = DBConnect.rs.getString("상품이름");
//				count = DBConnect.rs.getInt("판매량");
//				productStatistics.add(new ProductStatistics(name, count));
//			}
//			//옵션통합 통계량
//			DBConnect.optionsStatistics();
//			while(DBConnect.rs.next()) {
//				optsName = DBConnect.rs.getString("옵션이름");
//				optsCount = DBConnect.rs.getInt("판매량");
//				optionsArray.add(new ProductStatistics(optsName, optsCount));
//			}
//			//옵션1 통계량
//			DBConnect.option1Statistics();
//			while(DBConnect.rs.next()) {
//				opt1Name = DBConnect.rs.getString("옵션1이름");
//				opt1Count = DBConnect.rs.getInt("판매량");
//				option1Array.add(new ProductStatistics(opt1Name, opt1Count));
//			}
//			//옵션2 통계량
//			DBConnect.option2Statistics();
//			while(DBConnect.rs.next()) {
//				opt2Name = DBConnect.rs.getString("옵션2이름");
//				opt2Count = DBConnect.rs.getInt("판매량");
//				option2Array.add(new ProductStatistics(opt2Name, opt2Count));
//			}
//			//옵션3 통계량
//			DBConnect.option3Statistics();
//			while(DBConnect.rs.next()) {
//				opt3Name = DBConnect.rs.getString("옵션3이름");
//				opt3Count = DBConnect.rs.getInt("판매량");
//				option3Array.add(new ProductStatistics(opt3Name, opt3Count));
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		test();
		ObservableList<ProductStatistics> products = hamburgerTable.getItems();
		products.addAll(productStatistics);
		hamburgerTable.setItems(products);
		hamburgerTable.setPlaceholder(new Label());
		hamburgerName.setCellValueFactory(new PropertyValueFactory<ProductStatistics, String>("name"));
		hamburgerCount.setCellValueFactory(new PropertyValueFactory<ProductStatistics, Integer>("count"));
		
		ObservableList<ProductStatistics> optproducts = optionsTable.getItems();
		optproducts.addAll(optionsArray);
		optionsTable.setItems(optproducts);
		optionsTable.setPlaceholder(new Label());
		optionsName.setCellValueFactory(new PropertyValueFactory<ProductStatistics, String>("name"));
		optionsCount.setCellValueFactory(new PropertyValueFactory<ProductStatistics, Integer>("count"));
		
		ObservableList<ProductStatistics> opt1products = option1Table.getItems();
		opt1products.addAll(option1Array);
		option1Table.setItems(opt1products);
		option1Table.setPlaceholder(new Label());
		option1Name.setCellValueFactory(new PropertyValueFactory<ProductStatistics, String>("name"));
		option1Count.setCellValueFactory(new PropertyValueFactory<ProductStatistics, Integer>("count"));
		
		ObservableList<ProductStatistics> opt2products = option2Table.getItems();
		opt2products.addAll(option2Array);
		option2Table.setItems(opt2products);
		option2Table.setPlaceholder(new Label());
		option2Name.setCellValueFactory(new PropertyValueFactory<ProductStatistics, String>("name"));
		option2Count.setCellValueFactory(new PropertyValueFactory<ProductStatistics, Integer>("count"));
		
		ObservableList<ProductStatistics> opt3products = option3Table.getItems();
		opt3products.addAll(option3Array);
		option3Table.setItems(opt3products);
		option3Table.setPlaceholder(new Label());
		option3Name.setCellValueFactory(new PropertyValueFactory<ProductStatistics, String>("name"));
		option3Count.setCellValueFactory(new PropertyValueFactory<ProductStatistics, Integer>("count"));
	}
	
	
	
	public void exit() throws Exception {
		
		// TODO Auto-generated method stub
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Manager.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btnoexit.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	
}

	public void test() {
		try {
			//버거 통계량
			DBConnect.burgerStatistics();
			while(DBConnect.rs.next()) {
				name = DBConnect.rs.getString("상품이름");
				count = DBConnect.rs.getInt("판매량");
				productStatistics.add(new ProductStatistics(name, count));
			}
			//옵션통합 통계량
			DBConnect.optionsStatistics();
			while(DBConnect.rs.next()) {
				optsName = DBConnect.rs.getString("옵션이름");
				optsCount = DBConnect.rs.getInt("판매량");
				optionsArray.add(new ProductStatistics(optsName, optsCount));
			}
			//옵션1 통계량
			DBConnect.option1Statistics();
			while(DBConnect.rs.next()) {
				opt1Name = DBConnect.rs.getString("옵션1이름");
				opt1Count = DBConnect.rs.getInt("판매량");
				option1Array.add(new ProductStatistics(opt1Name, opt1Count));
			}
			//옵션2 통계량
			DBConnect.option2Statistics();
			while(DBConnect.rs.next()) {
				opt2Name = DBConnect.rs.getString("옵션2이름");
				opt2Count = DBConnect.rs.getInt("판매량");
				option2Array.add(new ProductStatistics(opt2Name, opt2Count));
			}
			//옵션3 통계량
			DBConnect.option3Statistics();
			while(DBConnect.rs.next()) {
				opt3Name = DBConnect.rs.getString("옵션3이름");
				opt3Count = DBConnect.rs.getInt("판매량");
				option3Array.add(new ProductStatistics(opt3Name, opt3Count));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



}
