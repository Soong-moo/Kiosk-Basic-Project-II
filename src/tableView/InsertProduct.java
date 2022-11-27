package tableView;

public class InsertProduct {

	int orderID;
	int categoryID;
	int productID;
	int option1ID;
	int option2ID;
	int option3ID;
	
	public InsertProduct(int orderID, int categoryID, int productID, int option1ID, int option2ID, int option3ID) {
		this.orderID = orderID;
		this.categoryID = categoryID;
		this.productID = productID;
		this.option1ID = option1ID;
		this.option2ID = option2ID;
		this.option3ID = option3ID;
		
	}
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getCategoryID() {
		return categoryID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getOption1ID() {
		return option1ID;
	}
	public void setOption1ID(int option1id) {
		option1ID = option1id;
	}
	public int getOption2ID() {
		return option2ID;
	}
	public void setOption2ID(int option2id) {
		option2ID = option2id;
	}
	public int getOption3ID() {
		return option3ID;
	}
	public void setOption3ID(int option3id) {
		option3ID = option3id;
	}
	
}
