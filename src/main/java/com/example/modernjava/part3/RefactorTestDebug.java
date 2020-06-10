package com.example.modernjava.part3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class RefactorTestDebug {

    public static void main(String[] args) {
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

        RefactorTestDebug refactorTestDebug = new RefactorTestDebug();

        try {
            String oneLine = refactorTestDebug.processFile((BufferedReader b) -> b.readLine());
            System.out.println(oneLine);
            String twoLines = refactorTestDebug.processFile((BufferedReader b) -> b.readLine() + b.readLine());
            System.out.println(twoLines);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

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
