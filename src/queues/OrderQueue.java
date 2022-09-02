package queues;

import java.util.ArrayList;

import entities.Sale;

public class OrderQueue {

	ArrayList<Sale> orderList;

	public OrderQueue() {
		this.orderList = new ArrayList<>();
	}

	public void add(Sale order) {
		orderList.add(order);
	}

	public Sale pop() {
		Sale aux = orderList.get(0);
		orderList.remove(0);
		return aux;
	}

	public int size() {
		return orderList.size();
	}

}
