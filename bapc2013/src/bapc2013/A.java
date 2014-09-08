package bapc2013;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class A {

	public static void main(String[] args) throws Exception {
		new A().solve();
	}

	private BufferedReader bufferedReader;
	private Integer ncars;
	private Integer nevents;
	// ArrayList<Car> cars = new ArrayList<Car>();
	ArrayList<Spy> spies = new ArrayList<Spy>();
	private HashMap<String, Car> carsMap = new HashMap<String, Car>();
	private HashMap<String, Spy> spiesMap = new HashMap<String, Spy>();

	private void solve() throws Exception {
		bufferedReader = new BufferedReader(new FileReader("A.in"));
		String firstLine = bufferedReader.readLine();
		String[] split = firstLine.split(" ");
		ncars = Integer.valueOf(split[0]);
		nevents = Integer.valueOf(split[1]);
		for (int i = 0; i < ncars; i++) {
			String[] vals = bufferedReader.readLine().split(" ");
			Car car = new Car(vals[0], Integer.valueOf(vals[1]),
					Integer.valueOf(vals[2]), Integer.valueOf(vals[3]));
			carsMap.put(vals[0], car);
			// cars.add(car);
		}
		for (int i = 0; i < nevents; i++) {
			String[] vals = bufferedReader.readLine().split(" ");
			Spy spy = spiesMap.get(vals[1]);
			if (spy == null) {
				spy = new Spy(vals[1], null, 0, true);
				spies.add(spy);
				spiesMap.put(vals[1], spy);
			}
			if (spy.consistency == false)
				continue;
			if (vals[2].equals("p")) {
				if (spy.car != null) {
					spy.consistency = false;
					continue;
				} else {
					spy.car = carsMap.get(vals[3]);
					spy.bill += spy.car.pcost;
				}
			} else if (vals[2].equals("a")) {
				if (spy.car == null) {
					spy.consistency = false;
					continue;
				} else
					spy.bill += Math.ceil((Double.valueOf(vals[3]) / 100d)
							* spy.car.price);
			} else if (vals[2].equals("r")) {
				if (spy.car == null) {
					spy.consistency = false;
					continue;
				} else {
					spy.bill += spy.car.cpkm * Integer.valueOf(vals[3]);
					spy.car = null;
				}
			}

		}

		bufferedReader.close();
		Collections.sort(spies, new Comparator<Spy>() {
			@Override
			public int compare(Spy arg0, Spy arg1) {

				return arg0.name.compareTo(arg1.name);
			}
		});

		for (Spy spy : spies) {
			if ((spy.consistency == false) || (spy.car != null))
				System.err.println(spy.name + " INCONSISTENT");
			else
				System.err.println(spy.name + " " + spy.bill);
		}

	}

}

class Car {
	String name;
	int price;
	int pcost;
	int cpkm;

	public Car(String name, int price, int pcost, int cpkm) {
		super();
		this.name = name;
		this.price = price;
		this.pcost = pcost;
		this.cpkm = cpkm;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Car other = (Car) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Car [name=" + name + "]";
	}

}

class Spy {
	@Override
	public String toString() {
		return "Spy [name=" + name + ", car=" + car + ", bill=" + bill
				+ ", consistency=" + consistency + "]";
	}

	String name;
	Car car;
	int bill;
	boolean consistency;

	public Spy(String name, Car car, int bill, boolean consistency) {
		super();
		this.name = name;
		this.car = car;
		this.bill = bill;
		this.consistency = consistency;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Spy other = (Spy) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
