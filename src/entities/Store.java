package entities;

import java.util.Random;
import java.util.concurrent.Semaphore;
import queues.OrderQueue;

public class Store extends Thread {

	public static char[] catalogue = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H' };
	public int salesCount;
	private String storeName;
	private OrderQueue orderLine;
	private Semaphore lineGate;
	private Semaphore manufacturerGate;

	public Store(String storeName, OrderQueue orderLine, Semaphore storeGate, Semaphore manufactureGate) {
		salesCount = 0;
		this.storeName = storeName;
		this.orderLine = orderLine;
		this.lineGate = storeGate;
		this.manufacturerGate = manufactureGate;
	}

	public void newSale(char item) {
		Sale newOrder = new Sale(item, storeName + "-" + salesCount);
		orderLine.add(newOrder);
		System.out.printf("New order #%d: %s\n", salesCount, newOrder);
		salesCount++;
	}

	public void run() {
		Random r = new Random();
		char newChar = ' ';
		while (true) {
			newChar = Character.toUpperCase((char) (r.nextInt(catalogue.length) + 'a'));
			try {
				lineGate.acquire();
				newSale(newChar);
				lineGate.release();
				manufacturerGate.release();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
