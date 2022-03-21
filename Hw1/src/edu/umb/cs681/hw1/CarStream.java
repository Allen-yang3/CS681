package edu.umb.cs681.hw1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class CarStream {

	public static void main(String[] args) {

		ArrayList<Car> carList = new ArrayList<Car>(Arrays.asList(new Car("Audi", "Q5", 2015, 80000, 12000),
				new Car("Toyota", "RAV4", 2016, 70000, 10000), new Car("Mercedes", "C-Class", 1980, 8000, 90000),
				new Car("BMW", "M4", 2021, 1500, 80000), new Car("Tesla", "Model3", 2019, 34500, 33000)));

		System.out.print("\nOldest to newest car: \n");
		List<Car> sortByYear = carList.stream()
				.sorted( (Car car1, Car car2) -> car1.getYear() - car2.getYear())
				.collect( Collectors.toList() );
				sortByYear.forEach((car) -> {
					System.out.println((car.getYear()) + ": " + car.toString() );
				});


		System.out.print("\nLowest to highest price: \n");
		List<Car> sortedPrice = carList.stream()
				.sorted( (Car car1, Car car2) -> car1.getPrice() - car2.getPrice())
				.collect( Collectors.toList() );
				sortedPrice.forEach((car) -> {
					System.out.println((car.getPrice()) + ": " + car.toString());
				});


		System.out.print("\nLowest to highest mileage: \n");
		List<Car> sortByMileage = carList.stream()
				.sorted( (Car car1, Car car2) -> car1.getMileage() - car2.getMileage())
				.collect( Collectors.toList() );
				sortByMileage.forEach((car) -> {
					System.out.println((car.getMileage()) + ": " + car.toString() );
				});

		System.out.print("\nPareto Comparison: \n");

        for(Car car: carList){
            car.setDominationCount(carList);
        }

        List<Car> sortByDomination = carList.stream()
        		.sorted(Comparator.comparing((Car car)-> car.getDominationCount()))
        		.collect( Collectors.toList() );
				sortByDomination.forEach((car) -> {
					System.out.println((car.toString() ));
				});
	}

}
