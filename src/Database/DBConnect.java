package Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBConnect {

	public static Connection con = null;
	public static Statement st = null;
	public static Statement stp = null;
	public static ResultSet rs = null;
	public static ResultSet rsp = null;
	public PreparedStatement pst = null;

	public void connect() throws SQLException {
		// Connection 객체를 자동완성으로 import할 때는 com.mysql.connection이 아닌
		// java 표준인 java.sql.Connection 클래스를 import해야 한다.

		try {
			// 1. 드라이버 로딩
			// 드라이버 인터페이스를 구현한 클래스를 로딩
			// mysql, oracle 등 각 벤더사 마다 클래스 이름이 다르다.
			// mysql은 "com.mysql.jdbc.Driver"이며, 이는 외우는 것이 아니라 구글링하면 된다.
			// 참고로 이전에 연동했던 jar 파일을 보면 com.mysql.jdbc 패키지에 Driver 라는 클래스가 있다.
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 연결하기
			// 드라이버 매니저에게 Connection 객체를 달라고 요청한다.
			// Connection을 얻기 위해 필요한 url 역시, 벤더사마다 다르다.
			// mysql은 "jdbc:mysql://localhost/사용할db이름" 이다.
			String url = "jdbc:mysql://localhost/kioskDB?useUnicode=true&serverTimezone=UTC";

			// @param getConnection(url, userName, password);
			// @return Connection
			con = DriverManager.getConnection(url, "test", "test");
			st = con.createStatement();
			stp = con.createStatement();
			//stp = conp.createStatement();
			System.out.println("연결 성공");
			
//			if(con != null) con.close();
//			if(st != null) st.close();
//			if(rs != null) rs.close();
//			if(pst != null) pst.close();
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		}
	}
	
	
	
	
	public static void test() {
		String sql = "SELECT productId, categoryId, name, price FROM productdata";
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void optionPrice() {
		String sqlp = "SELECT optionId, optionName, optionPrice FROM optionData";
	
		try {
			rsp = stp.executeQuery(sqlp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void getOrderID() {
		String sql = "SELECT orderID FROM selectData";
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean insertSelectData(int orderID, int categoryID, int productID, int option1ID, int option2ID, int option3ID, int number) {
		String sql = "INSERT INTO selectData VALUES ('" + orderID + "' , '" + categoryID + "' , '" + productID + "' , '" + option1ID + "' , '" + option2ID + "' , '" + option3ID +"' , '" + number + "')";
		try {
			st.executeUpdate(sql);	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean insertOrderData(int orderID, int orderPrice, String orderWay, String eatingWay) {
		String sql = "INSERT INTO orderData VALUES ('" + orderID + "', now() , '" + orderPrice + "', '" + orderWay + "', '" + eatingWay + "')";
		try {
			st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	//햄버거 통계량
	public static void burgerStatistics() {
		String sql = "SELECT S.productID AS 상품ID, P.NAME AS 상품이름, SUM(S.number) AS 판매량 FROM selectData S JOIN productData P ON S.productID = P.productID WHERE S.categoryID = 2 GROUP BY S.productID ORDER BY SUM(S.number) DESC";
		try {
			rs = st.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//option통계량
	public static void optionsStatistics() {
		String sql = "SELECT O.optionNAME AS 옵션이름, SUM(S.number) AS 판매량 FROM selectData S JOIN optionData O ON O.optionName NOT LIKE '선택안함' AND S.option1ID = O.optionID OR S.option2ID = O.optionID OR S.option3ID = O.optionID WHERE S.categoryID = 2 GROUP BY O.optionNAME ORDER BY SUM(S.number) DESC";
		try {
			rs = st.executeQuery(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//option1통계량
	public static void option1Statistics() {
		String sql = "SELECT O.optionNAME AS 옵션1이름, IFNULL(b.CNT, 0) AS 판매량 FROM optionData O LEFT OUTER JOIN (SELECT option1ID, SUM(number) AS CNT FROM selectData GROUP BY option1ID) AS b ON O.optionID = b.option1ID WHERE O.optionName NOT LIKE '선택안함' AND O.optionID = 1 OR O.optionID = 2 OR O.optionID = 3 ORDER BY b.cnt DESC";
		try {
			rs = st.executeQuery(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//option2통계량
	public static void option2Statistics() {
		String sql = "SELECT O.optionNAME AS 옵션2이름, IFNULL(b.CNT, 0) AS 판매량 FROM optionData O LEFT OUTER JOIN (SELECT option2ID, SUM(number) AS CNT FROM selectData GROUP BY option2ID) AS b ON O.optionID = b.option2ID WHERE O.optionName NOT LIKE '선택안함' AND O.optionID = 4 OR O.optionID = 5 OR O.optionID = 6 ORDER BY b.cnt DESC";
		try {
			rs = st.executeQuery(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	//option3통계량
	public static void option3Statistics() {
		String sql ="SELECT O.optionNAME AS 옵션3이름, IFNULL(b.CNT, 0) AS 판매량 FROM optionData O LEFT OUTER JOIN (SELECT option3ID, SUM(number) AS CNT FROM selectData GROUP BY option3ID) AS b ON O.optionID = b.option3ID WHERE O.optionName NOT LIKE '선택안함' AND O.optionID = 7 OR O.optionID = 8 OR O.optionID = 9 OR O.optionID = 10 OR O.optionID = 11 ORDER BY b.cnt DESC";
		try {
			rs = st.executeQuery(sql);
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	
	
	
	public static void main(String[] args) {
	//	new DBConnect().connect();
	}
	
	

}
	