package queues;

import java.util.ArrayList;
import entities.Shipment;

public class ShipmentQueue {
	private ArrayList<Shipment> shipmentQueue;
//	private static final int maxQueue = 150;
	
	public ShipmentQueue() {
		shipmentQueue = new ArrayList<>();
	}
	
	public void add(Shipment shipment) {
		shipmentQueue.add(shipment);
	}
	
	public Shipment pop() {
		Shipment aux = shipmentQueue.get(0);
		shipmentQueue.remove(0);
		return aux;
	}
	
	public int size() {
		return shipmentQueue.size();
	}
	
}
