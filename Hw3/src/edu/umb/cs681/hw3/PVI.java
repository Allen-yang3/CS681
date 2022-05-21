package edu.umb.cs681.hw3;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class PVI {
    public static void main(String[] args) throws Exception{

        var path = Paths.get("src/edu/umb/cs681/hw3/PVI.csv");

        var counties = Files.readAllLines(path)
                .stream()
                .filter(line -> line.contains("New Jersey, Morris")
                        || line.contains("New Jersey, Sussex")
                        || line.contains("New Jersey, Warren")
                        || line.contains("New Jersey, Hudson")
                        || line.contains("New Jersey, Essex"))
                .map(line -> line.split(","))
                .collect(Collectors.toList());

        var otherPVI = counties.stream()
                .map(line -> Double.parseDouble(line[0].replace("\"", "")))
                .reduce(new double[2], (result, pvi) -> {
                            var x = (result[1] * result[0] + pvi) / ++result[0];
                            return new double[]{result[0]++, x};
                        },
                        (calcresult, storedResult) -> calcresult)[1];
        System.out.print("Pvi selected north jersey counties is: ");
        System.out.println(otherPVI);
		
		}
}