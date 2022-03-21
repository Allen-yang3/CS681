package edu.umb.cs681.Hw2;

public class Car {
	private String make, model;
	private int mileage, year, price;

	public Car(String make, String model, int year, int mileage, int price) {
		this.make = make;
		this.model = model;
		this.year = year;
		this.mileage = mileage;
		this.price = price;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getMileage() {
		return mileage;
	}

	public int getYear() {
		return year;
	}

	public int getPrice() {
		return price;
	}
	
	@Override
	public String toString() {
		return this.year + " " + this.make + "  " + this.model + " " + this.mileage + " miles, $" + price;
	}
}
