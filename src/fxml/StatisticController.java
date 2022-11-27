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

public class StatisticController implements Initializable {

	@FXML
	private Button btnoexit;
	
	@FXML
	private TableView<ProductStatistics> tableView;
	@FXML
	private TableColumn<ProductStatistics, String> nameColumn; // 버거 통계 컬럼
	@FXML
	private TableColumn<ProductStatistics, Integer> countColumn; // 버거 판매 개수 통계 컬럼
	
	
	String name; //DB 버거 이름 저장
	int count; // DB 버거 판매 개수 저장
	
	ArrayList<ProductStatistics> productStatistics = new ArrayList<>();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		
		try {
			DBConnect.burgerStatistics();
			while(DBConnect.rs.next()) {
				name = DBConnect.rs.getString("상품이름");
				count = DBConnect.rs.getInt("판매량");
				productStatistics.add(new ProductStatistics(name, count));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ObservableList<ProductStatistics> products = tableView.getItems();
		products.addAll(productStatistics);
		tableView.setItems(products);
		tableView.setPlaceholder(new Label());
		nameColumn.setCellValueFactory(new PropertyValueFactory<ProductStatistics, String>("name"));
		countColumn.setCellValueFactory(new PropertyValueFactory<ProductStatistics, Integer>("count"));
	}
	
	public void exit() throws Exception {
		// TODO Auto-generated method stub
		Parent View = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Manager.fxml"));
		Scene scene = new Scene(View);
		Stage primaryStage = (Stage) btnoexit.getScene().getWindow();
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
