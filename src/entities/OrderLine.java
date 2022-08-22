package entities;

import java.util.ArrayList;

public class OrderLine {

	ArrayList<Order> orderList;

	public OrderLine() {
		this.orderList = new ArrayList<>();
	}

	public void add(Order order) {
		orderList.add(order);
	}

	public Order removeFirst() {
		if (orderList.size() > 0) {
			Order aux = orderList.get(0);
			orderList.remove(0);
			return aux;
		}
		return null;
	}

	public int size() {
		return orderList.size();
	}

}
