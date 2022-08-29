package entities;

import java.util.concurrent.Semaphore;

public class Manufacturer extends Thread {

	private OrderQueue orderQueue;
	private ShipmentQueue shipmentQueue;
	private int manufacturedCount;
	private Semaphore manufactureGate;
	private Semaphore queueGate;
	private Semaphore shipmentQueueGate;
	private Semaphore transporterGate;

	public Manufacturer(
			OrderQueue orderLine, 
			ShipmentQueue shipmentQueue, 
			Semaphore queueGate, 
			Semaphore manufactureGate,
			Semaphore shipmentQueueGate,
			Semaphore transporterGate
	) {
		this.manufacturedCount = 0;
		
		this.orderQueue = orderLine;
		this.shipmentQueue = shipmentQueue;
		
		this.queueGate = queueGate;
		this.manufactureGate = manufactureGate;
		this.shipmentQueueGate = shipmentQueueGate;
		this.transporterGate = transporterGate;
	}

	public void run() {
		while (true) {
			try {
				manufactureGate.acquire();
				
				queueGate.acquire();
				Sale aux = orderQueue.pop();
				queueGate.release();
				
				System.out.printf("Manufacturing #%d: %s\n", manufacturedCount, aux);
				manufacturedCount++;

				Shipment shipment = new Shipment(aux.toString(), manufacturedCount-1);
				shipmentQueueGate.acquire();
				shipmentQueue.add(shipment);
				shipmentQueueGate.release();
				transporterGate.release();
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
