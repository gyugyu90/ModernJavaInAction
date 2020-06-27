package com.example.modernjava.part4;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class OptionalInsteadOfNull {


    public static void main(String[] args) {

        Optional<Car> optCar = Optional.empty();

        Car car = new Car();
        Optional<Car> optCarWithInstance = Optional.of(car);
        // Optional<Car> optWithNull = Optional.of(null); // throws NPE

        Optional<Car> optNullable = Optional.ofNullable(car);
        Optional<Car> optNullable2 = Optional.ofNullable(null); // returns Optional.empty();

        Insurance insurance = new Insurance("ABCInsurance");
        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);
        Optional<String> name = optInsurance.map(Insurance::getName);


    }

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public Set<String> getCarInsuranceNames(List<Person> persons) {
        return persons.stream()
                .map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optIns -> optIns.map(Insurance::getName))
                .flatMap(Optional::stream)
                .collect(Collectors.toSet());
    }

    public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    public Insurance findCheapestInsurance(Person person, Car car) {
        return new Insurance("ABC");
    }

    public String getCarInsuranceName(Optional<Person> person, int minAge) {
        return person.filter(p -> p.getAge() >= minAge)
                .flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException ex) {
            return Optional.empty();
        }
    }

}
