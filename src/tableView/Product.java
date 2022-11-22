package tableView;

public class Product {

	String name;
	int price;
	int count = 1;
	
	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
