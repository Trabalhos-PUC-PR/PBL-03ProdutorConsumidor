package app;

import java.util.concurrent.Semaphore;

import entities.Manufacturer;
import entities.OrderLine;
import entities.Store;

public class Main {

	public static void main(String[] args) {
		OrderLine line = new OrderLine();
		Semaphore storeGate = new Semaphore(1);
		Semaphore manufactureGate = new Semaphore(0);

		Store casasChina = new Store("Casas China", line, storeGate, manufactureGate);
		Manufacturer china = new Manufacturer(line, manufactureGate);
		
		casasChina.start();
		china.start();
	}
}
