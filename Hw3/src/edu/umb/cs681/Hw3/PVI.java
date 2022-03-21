package edu.umb.cs681.Hw3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PVI {
        public static void main(String args[]) {
                Path path = Paths.get("src/edu/umb/cs681/hw3/PVI.csv");

                try( Stream<String> lines = Files.lines(path) ){
                    List<List<String>> matrix = lines.map(line -> {
                        return Stream.of( line.split(",") )
                        .map(value-> value)
                        .collect( Collectors.toList() ); })
            .collect( Collectors.toList() );


        String MaNoDeath = matrix.stream().filter((i) -> i.get(4).contains("Massachusetts")).map((i) -> 
        i.get(7)).reduce("0", (subtotal, element) -> String.valueOf(Integer.parseInt(subtotal) + 
        Integer.parseInt(element)));
        System.out.println("Total Number of Deaths in MA:" + MaNoDeath);
        String NJNoDeath = matrix.stream().filter((i) -> i.get(4).contains("New Jersey")).map((i) -> 
        i.get(7)).reduce("0", (subtotal, element) -> String.valueOf(Integer.parseInt(subtotal) + 
        Integer.parseInt(element)));
        System.out.println("Total Number of Deaths in NJ:" + NJNoDeath);
        } catch (IOException ex) {
                System.out.println("Bad path");
        }
}
}