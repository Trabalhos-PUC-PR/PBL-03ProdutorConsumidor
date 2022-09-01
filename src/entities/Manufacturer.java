package entities;

import java.util.concurrent.Semaphore;
import actions.Assembling;
import queues.OrderQueue;
import queues.ShipmentQueue;

public class Manufacturer extends Thread {

	private OrderQueue orderQueue;
	private ShipmentQueue shipmentQueue;
	private int manufacturedCount;
	private Semaphore manufactureGate;
	private Semaphore queueGate;
	private Semaphore shipmentQueueGate;
	private Semaphore transporterGate;
	private Semaphore capacityAvailability;
	private String name;
	
	public Manufacturer(
			String name,
			OrderQueue orderLine, 
			ShipmentQueue shipmentQueue, 
			Semaphore queueGate, 
			Semaphore manufactureGate,
			Semaphore shipmentQueueGate,
			Semaphore transporterGate,
			int parallelCapacity
	) {
		this.name = name;
		this.manufacturedCount = 0;
		
		this.orderQueue = orderLine;
		this.shipmentQueue = shipmentQueue;
		
		this.queueGate = queueGate;
		this.manufactureGate = manufactureGate;
		this.shipmentQueueGate = shipmentQueueGate;
		this.transporterGate = transporterGate;
		
		capacityAvailability = new Semaphore(parallelCapacity);
	}
	
	public String getFactoryName() {
		return name;
	}
	
	public void run() {
		while (true) {
			try {
				manufactureGate.acquire();
				capacityAvailability.acquire();
				
				queueGate.acquire();
				Sale aux = orderQueue.pop();
				queueGate.release();

				Assembling assembling = new Assembling(aux, shipmentQueue, transporterGate, shipmentQueueGate, manufacturedCount, capacityAvailability, this);
				assembling.start();
				manufacturedCount++;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
