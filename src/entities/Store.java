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
		Random random = new Random();
		char newChar = ' ';
		int refreshSaleTimer = random.nextInt(140) + 10;
		while (true) {
			newChar = Character.toUpperCase((char) (random.nextInt(catalogue.length) + 'a'));
			try {
				lineGate.acquire();
				newSale(newChar);
				lineGate.release();
				manufacturerGate.release();
				Thread.sleep(refreshSaleTimer);
				refreshSaleTimer = random.nextInt(140) + 10;
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

}
