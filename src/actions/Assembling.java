package actions;

import java.util.Random;
import java.util.concurrent.Semaphore;

import entities.Manufacturer;
import entities.Sale;
import entities.Shipment;
import queues.ShipmentQueue;

public class Assembling extends Thread {

	private Sale product;
	private Random random;
	private int sleepTime;
	private Semaphore shipmentQueueGate;
	private Semaphore transporterGate;
	private ShipmentQueue shipmentQueue;
	private int itemNumber;
	private Semaphore capacityAvailability;
	private Manufacturer factory;

	public Assembling(Sale product, ShipmentQueue shipmentQueue, Semaphore transporterGate, Semaphore shipmentQueueGate,
			int itemNumber, Semaphore capacityAvailability, Manufacturer factory) {
		this.product = product;
		this.random = new Random();
		this.sleepTime = random.nextInt(30) + 10;
		this.itemNumber = itemNumber;

		this.shipmentQueue = shipmentQueue;
		this.shipmentQueueGate = shipmentQueueGate;
		this.transporterGate = transporterGate;
		this.capacityAvailability = capacityAvailability;
		this.factory = factory;
	}

	public void run() {
		try {
			System.out.printf("Manufacturing #%d: %s (%d@%s)\n", itemNumber, product, sleepTime,
					factory.getFactoryName());
			Thread.sleep(sleepTime);

			Shipment shipment = new Shipment(product.toString());
			shipmentQueueGate.acquire();
			shipmentQueue.add(shipment);
			capacityAvailability.release();
			shipmentQueueGate.release();
			transporterGate.release();
			System.out.printf("Wrapping up #%d: %s (%d@%s)\n", itemNumber, product, sleepTime,
					factory.getFactoryName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
