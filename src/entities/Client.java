package entities;

public class Client {

	private String name;
	private char order;

	public Client(String name, char order) {
		this.name = name;
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public char getOrder() {
		return order;
	}

	public void setOrder(char order) {
		this.order = order;
	}
	
}
