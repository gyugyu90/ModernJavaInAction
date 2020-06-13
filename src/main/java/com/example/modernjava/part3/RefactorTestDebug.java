package com.example.modernjava.part3;

import com.example.modernjava.part2.Dish;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RefactorTestDebug {

    public static void main(String[] args) {


        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello");
            }
        };

        Runnable runnable2 = () -> System.out.println("Hello");


        RefactorTestDebug refactorTestDebug = new RefactorTestDebug();
        refactorTestDebug.testScope();


        int a = 10;
        Runnable r1 = () -> {
            // int a = 2;
            System.out.println(a); // prints 10
        };
        r1.run();

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                int a = 2;
                System.out.println(a); // prints 2
            }
        };
        r2.run();

        RefactorTestDebug refactorTest = new RefactorTestDebug();

        try {
            String oneLine = refactorTest.processFile((BufferedReader b) -> b.readLine());
            System.out.println(oneLine);
            String twoLines = refactorTest.processFile((BufferedReader b) -> b.readLine() + b.readLine());
            System.out.println(twoLines);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        doSomething((Task) () -> System.out.println("did something"));


        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );

        List<String> dishNames = new ArrayList<>();
        for (Dish dish: menu) {
            if (dish.getCalories() > 300) {
                dishNames.add(dish.getName());
            }
        }

        menu.parallelStream()
                .filter(dish -> dish.getCalories() > 300)
                .map(Dish::getName)
                .collect(Collectors.toList());

    }

    public static void doSomething(Runnable r) {
        r.run();
        System.out.println("Runnable");
    }

    public static void doSomething(Task a) {
        a.execute();
        System.out.println("Task");
    }

    interface Task {
        public void execute();
    }

    private String str = "Hello";

    private void testScope() {
        Runnable run1 = new Runnable() {
            String str = "World";
            @Override
            public void run() {
                System.out.println(this.str);
            }
        };
        run1.run();

        Runnable run2 = () -> System.out.println(this.str);
        run2.run();
    }

    private String getFileFromResources(String fileName) {

        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return resource.toString();
        }

    }

    public String processFile(BufferedReaderProcessor p) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("data.txt").getFile());
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            return p.process(br); // 인수로 전달된 BufferedReaderProcessor 를 실행
        }
    }

    interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }

}
