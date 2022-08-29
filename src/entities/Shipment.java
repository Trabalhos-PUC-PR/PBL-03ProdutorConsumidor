package entities;

public class Shipment {

	private int itemNumber;
	private char productName;
	private String storeName;
	
	public Shipment(String itemSerial, int itemNumber) {
		String[] itemAttribs = itemSerial.split("-");
 		this.productName = itemAttribs[2].charAt(0);
 		this.storeName = itemAttribs[0];
		this.itemNumber = itemNumber;
	}
	
	public int getItemNumber() {
		return itemNumber;
	}

	public char getProductName() {
		return productName;
	}
	
	@Override
	public String toString() {
		return "@"+storeName + "#" + itemNumber + "-" + productName;
	}
	
}
