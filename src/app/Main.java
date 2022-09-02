package app;

import java.util.concurrent.Semaphore;

import entities.Manufacturer;
import entities.Store;
import entities.Transporter;
import queues.OrderQueue;
import queues.ShipmentQueue;

public class Main {

	public static void main(String[] args) {
		OrderQueue orderQueue = new OrderQueue();
		ShipmentQueue shipmentQueue = new ShipmentQueue();

		Semaphore orderQueueGate = new Semaphore(1);
		Semaphore manufactureGate = new Semaphore(0);
		Semaphore shipmentQueueGate = new Semaphore(1);
		Semaphore transporterGate = new Semaphore(0);

		Store store1 = new Store("1.99", orderQueue, orderQueueGate, manufactureGate);
		Store store2 = new Store("Casas_China", orderQueue, orderQueueGate, manufactureGate);
		Store store3 = new Store("PoliShop", orderQueue, orderQueueGate, manufactureGate);
		Store store4 = new Store("Aliexpress", orderQueue, orderQueueGate, manufactureGate);
		Store store5 = new Store("Shopee", orderQueue, orderQueueGate, manufactureGate);
		Store store6 = new Store("Wish.com", orderQueue, orderQueueGate, manufactureGate);
		Store store7 = new Store("Lojas_Franca", orderQueue, orderQueueGate, manufactureGate);
		Store store8 = new Store("Bili_Bili", orderQueue, orderQueueGate, manufactureGate);

		Manufacturer china1 = new Manufacturer("Beijing", orderQueue, shipmentQueue, orderQueueGate, manufactureGate,
				shipmentQueueGate, transporterGate, 4);
		Manufacturer china2 = new Manufacturer("Taiwan", orderQueue, shipmentQueue, orderQueueGate, manufactureGate,
				shipmentQueueGate, transporterGate, 1);
		Manufacturer china3 = new Manufacturer("Hong_Kong", orderQueue, shipmentQueue, orderQueueGate, manufactureGate,
				shipmentQueueGate, transporterGate, 4);
		Manufacturer china4 = new Manufacturer("Jiangxi", orderQueue, shipmentQueue, orderQueueGate, manufactureGate,
				shipmentQueueGate, transporterGate, 4);

		Transporter sedex1 = new Transporter("Amazon_Delivery_System", shipmentQueue, transporterGate,
				shipmentQueueGate, 10);
		Transporter sedex2 = new Transporter("Sedex", shipmentQueue, transporterGate, shipmentQueueGate, 20);

		store1.start();
		store2.start();
		store3.start();
		store4.start();
		store5.start();
		store6.start();
		store7.start();
		store8.start();

		china1.start();
		china2.start();
		china3.start();
		china4.start();

		sedex1.start();
		sedex2.start();
		
		int dayCounter = 1;
		int whatIsConsideredDay = 1000;
		while(true) {
			
			try {
				Thread.sleep(whatIsConsideredDay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.printf("END OF DAY %d!\n", dayCounter);
			System.out.printf("%s manufactured %d items, thats around %.2f per hour!\n", china1, china1.getManufacturedCount(), (double)china1.getManufacturedCount()/(whatIsConsideredDay/1000));
			System.out.printf("%s manufactured %d items, thats around %.2f per hour!\n", china2, china2.getManufacturedCount(), (double)china2.getManufacturedCount()/(whatIsConsideredDay/1000));
			System.out.printf("%s manufactured %d items, thats around %.2f per hour!\n", china3, china3.getManufacturedCount(), (double)china3.getManufacturedCount()/(whatIsConsideredDay/1000));
			System.out.printf("%s manufactured %d items, thats around %.2f per hour!\n", china4, china4.getManufacturedCount(), (double)china4.getManufacturedCount()/(whatIsConsideredDay/1000));
			System.out.printf("\n");
			System.out.printf("%s transported %d items today, thats %.2f per hour!\n", sedex1, sedex1.getTotalShipments(), (double)sedex1.getTotalShipments()/(whatIsConsideredDay/1000));
			System.out.printf("%s transported %d items today, thats %.2f per hour!\n", sedex2, sedex2.getTotalShipments(), (double)sedex2.getTotalShipments()/(whatIsConsideredDay/1000));
			System.out.printf("\n");
			System.out.printf("By the end of the day, %d products are in queue to be manufactured!\n", orderQueue.size());
			System.out.printf("By the end of the day, %d products are in queue to be shipped!\n", shipmentQueue.size());
			System.out.printf("\n\n");
			dayCounter++;
			china1.resetManufacturedCount();
			china2.resetManufacturedCount();
			china3.resetManufacturedCount();
			china4.resetManufacturedCount();
			sedex1.resetTotalShipments();
			sedex2.resetTotalShipments();
		}
		
	}
	
}
