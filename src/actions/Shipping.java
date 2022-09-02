package actions;

import java.util.Random;
import java.util.concurrent.Semaphore;

import entities.Shipment;
import entities.Transporter;

public class Shipping extends Thread {

	private Random random;
	private int sleepTime;
//	private Shipment shipment;
	private Transporter transporter;
	private Semaphore capacityAvailability;

	public Shipping(Transporter transporter, Shipment shipment, Semaphore capacityAvailability) {
		this.transporter = transporter;
//		this.shipment = shipment;
		this.random = new Random();
		this.sleepTime = random.nextInt(30) + 10;
		this.capacityAvailability = capacityAvailability;
	}
	
	public void run() {
//		System.out.printf("%s TRANSPORTING %s!\n", transporter, shipment);
		try {
			Thread.sleep(sleepTime);
			transporter.incrementTotalShipments();
//			System.out.printf("%s HAS FINISHED TRANSPORTING %s!\n", transporter, shipment);
			capacityAvailability.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
