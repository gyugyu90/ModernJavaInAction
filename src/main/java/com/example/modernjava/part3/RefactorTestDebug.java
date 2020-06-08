package com.example.modernjava.part3;

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

    }


}
