package entities;

import java.util.concurrent.Semaphore;

import actions.Shipping;
import queues.ShipmentQueue;

public class Transporter extends Thread {

	private String name;
	private int totalShipments;
	private ShipmentQueue shipmentQueue;
	private Semaphore transporterGate;
	private Semaphore shipmentQueueGate;
	private Semaphore capacityAvailability;

	public Transporter(String name, ShipmentQueue shipmentQueue, Semaphore transporterGate, Semaphore shipmentQueueGate,
			int parallelCapacity) {
		this.name = name;
		this.totalShipments = 0;
		this.shipmentQueue = shipmentQueue;
		this.transporterGate = transporterGate;
		this.shipmentQueueGate = shipmentQueueGate;
		this.capacityAvailability = new Semaphore(parallelCapacity);
	}

	public int getTotalShipments() {
		return totalShipments;
	}
	
	public void resetTotalShipments() {
		totalShipments = 0;
	}
	
	public void incrementTotalShipments() {
		totalShipments++;
	}
	
	public void run() {
		while (true) {
			try {
				transporterGate.acquire();
				capacityAvailability.acquire();
				
				shipmentQueueGate.acquire();
				Shipment aux = shipmentQueue.pop();
				shipmentQueueGate.release();

				Shipping shipping = new Shipping(this, aux, capacityAvailability);
				shipping.start();

			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		return name;
	}

}
