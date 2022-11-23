package tableView;

public class Product {

	String name;
	int price;
	String option1;
	String option2;
	String option3;
	
	int count = 1;
	
	
	public Product(String name, int price, String option1, String option2, String option3) {
		this.name = name;
		this.price = price;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
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
	
	
	
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
}
