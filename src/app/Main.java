package app;

import java.util.concurrent.Semaphore;

import entities.Manufacturer;
import entities.OrderQueue;
import entities.ShipmentQueue;
import entities.Store;
import entities.Transporter;

public class Main {

	public static void main(String[] args) {
		OrderQueue orderQueue = new OrderQueue();
		ShipmentQueue shipmentQueue = new ShipmentQueue();

		Semaphore orderQueueGate = new Semaphore(1);
		Semaphore manufactureGate = new Semaphore(0);
		Semaphore shipmentQueueGate = new Semaphore(1);
		Semaphore transporterGate = new Semaphore(0);

		Store casasChina1 = new Store("Casas_China1", orderQueue, orderQueueGate, manufactureGate);
		Store casasChina2 = new Store("Casas_China2", orderQueue, orderQueueGate, manufactureGate);
		Store casasChina3 = new Store("Casas_China3", orderQueue, orderQueueGate, manufactureGate);
		Store casasChina4 = new Store("Casas_China4", orderQueue, orderQueueGate, manufactureGate);
		Store casasChina5 = new Store("Casas_China5", orderQueue, orderQueueGate, manufactureGate);
		Store casasChina6 = new Store("Casas_China6", orderQueue, orderQueueGate, manufactureGate);
		Store casasChina7 = new Store("Casas_China7", orderQueue, orderQueueGate, manufactureGate);
		Store casasChina8 = new Store("Casas_China8", orderQueue, orderQueueGate, manufactureGate);
		
		Manufacturer china1 = new Manufacturer(orderQueue, shipmentQueue, orderQueueGate, manufactureGate,
				shipmentQueueGate, transporterGate);
		Manufacturer china2 = new Manufacturer(orderQueue, shipmentQueue, orderQueueGate, manufactureGate,
				shipmentQueueGate, transporterGate);
		Manufacturer china3 = new Manufacturer(orderQueue, shipmentQueue, orderQueueGate, manufactureGate,
				shipmentQueueGate, transporterGate);
		Manufacturer china4 = new Manufacturer(orderQueue, shipmentQueue, orderQueueGate, manufactureGate,
				shipmentQueueGate, transporterGate);
		
		Transporter sedex1 = new Transporter(shipmentQueue, transporterGate, shipmentQueueGate);
		Transporter sedex2 = new Transporter(shipmentQueue, transporterGate, shipmentQueueGate);

		casasChina1.start();
		casasChina2.start();
		casasChina3.start();
		casasChina4.start();
		casasChina5.start();
		casasChina6.start();
		casasChina7.start();
		casasChina8.start();
		
		china1.start();
		china2.start();
		china3.start();
		china4.start();
		
		sedex1.start();
		sedex2.start();
	}
}
