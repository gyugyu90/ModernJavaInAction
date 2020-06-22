package com.example.modernjava.part3;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Debugging {
    public static void main(String[] args) {


        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        numbers.stream().map(Debugging::divideByZero).forEach(System.out::println);

        List<String> strings = Arrays.asList("hello", "world");
        strings.stream().map(s -> s.toUpperCase()).forEach(System.out::println);


        List<Point> points = Arrays.asList(new Point(12, 2), null);
        points.stream().map(Point::getX).forEach(System.out::println);
    }

    public static int divideByZero(int n) {
        return n / 0;
    }

}
