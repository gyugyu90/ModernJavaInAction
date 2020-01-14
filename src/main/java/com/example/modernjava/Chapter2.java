package com.example.modernjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Chapter2 {

    public static void main(String[] args) {

        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(100, Color.RED));
        inventory.add(new Apple(120, Color.GREEN));
        inventory.add(new Apple(110, Color.GREEN));
        inventory.add(new Apple(150, Color.RED));

        List<Apple> redApples = filterApplesByColor(inventory, Color.RED);

        // predicate impl class
        List<Apple> redApples2 = filter(inventory, new FilterRedApplePredicate());

        // predicate anonymous
        List<Apple> greenApples = filter(inventory, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor().equals(Color.GREEN);
            }
        });

        // lambda
        List<Integer> oddNumbers = filter(Arrays.asList(1, 2, 3, 4, 5), i -> i % 2 == 1);
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();
        for(Apple apple: inventory) {
            if (Color.RED.equals(apple.getColor())) {
                result.add(apple);
            }
        }
        return result;
    }

    // snippets
    public static List<Apple> filter(List<Apple> list, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for(Apple e: list) {
            if(p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for(T e: list) {
            if(p.test(e)) {
                result.add(e);
            }
        }
        return result;
    }

    static class FilterRedApplePredicate implements ApplePredicate{
        @Override
        public boolean test(Apple apple) {
            return Color.RED.equals(apple.getColor());
        }
    }
}
