package entities;

import java.util.ArrayList;

public class OrderQueue {

	ArrayList<Sale> orderList;
	int listSize;

	public OrderQueue() {
		this.orderList = new ArrayList<>();
		listSize = 0;
	}

	public void add(Sale order) {
		orderList.add(order);
		listSize++;
	}

	public Sale pop() {
		Sale aux = orderList.get(0);
		orderList.remove(0);
		listSize--;
		return aux;
	}

	public int size() {
		return listSize;
	}

}
