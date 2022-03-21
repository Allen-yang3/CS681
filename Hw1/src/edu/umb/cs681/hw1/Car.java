package edu.umb.cs681.hw1;

import java.util.ArrayList;

public class Car extends Object {

	private String make, model;
	private int mileage, year, price;
	private int dominationCount;

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
	
	public void setDominationCount(ArrayList<Car>cars) {
		this.dominationCount = 0;
		for(Car car: cars ) {
			if(
				(
					car.getPrice() >= this.getPrice() && 
					car.getMileage() >= this.getMileage() && 
					car.getYear() <= this.getYear()
				)
				&&
				(
					car.getPrice() > this.getPrice() || 
					car.getMileage() > this.getMileage() || 
					car.getYear() < this.getYear()
				)
			) {
				this.dominationCount++;
			}
		}
	}
	
	public int getDominationCount() {
		return this.dominationCount;
	}
}
