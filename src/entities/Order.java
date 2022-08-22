package entities;

public class Order {

	private char item;
	private String serial;
	
	public Order(char item, String serial) {
		this.item = item;
		this.serial = serial;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public char getItem() {
		return item;
	}

	public void setItem(char item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return item + "000:" + serial;
	}
	
	
	
}
