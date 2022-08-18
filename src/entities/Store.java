package entities;

import java.util.ArrayList;

public class Store {

	public static char[] catalogue = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
	private String storeName;
	private ArrayList<Client> orderList;

	public Store(String storeName) {
		this.storeName = storeName;
		orderList = new ArrayList<>();
	}

	public void newOrder(Client fromClient) {
		orderList.add(fromClient);
	}

	public void run() {
		for (Client client : orderList) {

		}
	}

	@Override
	public String toString() {
		return storeName + "";
	}

}
