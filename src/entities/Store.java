package entities;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Store extends Thread {

	public static char[] catalogue = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
	public int salesCount;
	private String storeName;
	private OrderLine orderLine;
	Semaphore storeGate;
	Semaphore manufactureGate;

	public Store(String storeName, OrderLine orderLine, Semaphore storeGate, Semaphore manufactureGate) {
		salesCount = 0;
		this.storeName = storeName;
		this.orderLine = orderLine;
		this.storeGate = storeGate;
		this.manufactureGate = manufactureGate;
	}

	public void newSale(char item) {
		Order newOrder = new Order(item, storeName);
		System.out.printf("New order #%d: %s\n", salesCount, newOrder);
		orderLine.add(newOrder);
		salesCount++;
	}

	public void run() {
		try {
			Random r = new Random();
			char newChar = ' ';
			while (true) {
				storeGate.acquire();
				newChar = Character.toUpperCase((char) (r.nextInt(catalogue.length) + 'a'));
				newSale(newChar);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				storeGate.release();
				manufactureGate.release();
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

}
