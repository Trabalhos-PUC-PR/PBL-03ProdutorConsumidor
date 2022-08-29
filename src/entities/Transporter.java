package entities;

import java.util.concurrent.Semaphore;

public class Transporter extends Thread {

	ShipmentQueue shipmentQueue;
	Semaphore transporterGate;
	Semaphore shipmentQueueGate;

	public Transporter(ShipmentQueue shipmentQueue, Semaphore transporterGate, Semaphore shipmentQueueGate) {
		this.shipmentQueue = shipmentQueue;
		this.transporterGate = transporterGate;
		this.shipmentQueueGate = shipmentQueueGate;
	}

	public void run() {
		while (true) {
			try {
				transporterGate.acquire();

				shipmentQueueGate.acquire();
				Shipment aux = shipmentQueue.pop();
				shipmentQueueGate.release();

				System.out.printf("TRANSPORTING %s!\n", aux);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
