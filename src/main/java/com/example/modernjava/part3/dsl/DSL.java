package com.example.modernjava.part3.dsl;

import com.example.modernjava.part1.Color;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static com.example.modernjava.part3.dsl.MethodChainingOrderBuilder.forCustomer;
import static com.example.modernjava.part3.dsl.NestedFunctionOrderBuilder.*;
import static java.util.stream.Collectors.groupingBy;


public class DSL {

    public static void main(String[] args) {

        List<Person> persons = Arrays.asList(
                new Person(),
                new Person()
        );
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });

        Collections.sort(persons, (p1, p2) -> p1.getAge() - p2.getAge());


        try {
            List<String> errors = new ArrayList<>();
            int errorCount = 0;
            String fileName = "";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            String line = bufferedReader.readLine();
            while (errorCount < 40 && line != null) {
                if (line.startsWith("ERROR")) {
                    errors.add(line);
                    errorCount++;
                }

                line = bufferedReader.readLine();
            }

            Files.lines(Paths.get(fileName)) // 파일을 열어서 문자열 스트림을 만듦
                    .filter(l -> l.startsWith("ERROR")) // ERROR로 필터링
                    .limit(40) // 40행으로 제한
                    .collect(Collectors.toList()); // 결과를 리스트로 수집

        } catch (Exception ex) {
            ex.printStackTrace();
        }


        List<Car> cars = Arrays.asList(
                new Car()
        );

        Map<String, Map<Color, List<Car>>> carsByBrandAndColor = cars
                .stream()
                .collect(groupingBy(Car::getBrand,
                        groupingBy(Car::getColor)));


        Collector<? super Car, ?, Map<String, Map<Color, List<Car>>>> carGroupingCollector = GroupingBuilder.groupOn(Car::getColor).after(Car::getBrand).get();


        Order order = forCustomer("BigBank")
                .buy(30)
                .stock("IBM")
                .on("NYSE")
                .at(12.00)
                .sell(0)
                .stock("GOOGLE")
                .on("NASDAQ")
                .at(375.00)
                .end();

        Order order1 = order("BigBand",
                            buy(80, stock("IBM", on("NYSE")), at(125.00)),
                            sell(0, stock("GOOGLE", on("NASDAQ")), at(375.00))
                        );

    }

}
