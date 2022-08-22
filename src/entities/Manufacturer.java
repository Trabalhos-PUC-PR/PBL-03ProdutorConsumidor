package entities;

import java.util.concurrent.Semaphore;

public class Manufacturer extends Thread {

	private OrderLine orderLine;
	private int manufacturedCount;
	Semaphore manufactureGate;

	public Manufacturer(OrderLine orderLine, Semaphore manufactureGate) {
		this.orderLine = orderLine;
		this.manufactureGate = manufactureGate;
		this.manufacturedCount = 0;
	}

	public void run() {
		while (true) {
			try {
				manufactureGate.acquire();
				Order aux = orderLine.removeFirst();
				if (aux != null) {
					manufacturedCount++;
					System.out.printf("removing #%d: %s\n", manufacturedCount, aux);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
