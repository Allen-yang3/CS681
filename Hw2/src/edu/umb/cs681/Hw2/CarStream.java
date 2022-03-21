package edu.umb.cs681.Hw2;

import java.util.ArrayList;
import java.util.Arrays;

public class CarStream {

	public static void main(String[] args) {
		ArrayList<Car> carList = new ArrayList<Car>(Arrays.asList(new Car("Audi", "Q5", 2015, 80000, 12000),
				new Car("Toyota", "RAV4", 2016, 70000, 10000), new Car("Mercedes", "C-Class", 1980, 8000, 90000),
				new Car("BMW", "M4", 2021, 1500, 80000), new Car("Tesla", "Model3", 2019, 34500, 33000)));

		// Lowest price

		Integer minPrice = carList.stream().map((Car car) -> car.getPrice()).reduce(0, (result, carPrice) -> {
			if (result == 0)
				return carPrice;
			else if (carPrice < result)
				return carPrice;
			else
				return result;
		});
		System.out.println("Our cheapest car in the list has a price of $" + minPrice);


		// Highest price

		Integer maxPrice = carList.stream().map((Car car) -> car.getPrice()).reduce(0,
				(result, carPrice) -> Math.max(result, carPrice));
		System.out.print("\nThe most expensive car costs $" + maxPrice + "\n");

		//Average

		int avgPrice = carList.stream().map( car->car.getPrice()).reduce(new int[2],
				(result,price)->
					{result[1]=(result[1]*result[0]+price)/(result[0]+1);
					result[0]++;
					return result;},
				(finalResult, intermediateResult)->finalResult)[1];
		System.out.printf("The average price of cars is $" + avgPrice);


	}

}
