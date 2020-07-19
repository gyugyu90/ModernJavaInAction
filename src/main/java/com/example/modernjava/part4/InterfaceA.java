package com.example.modernjava.part4;

public interface InterfaceA {
    default void hello() {
        System.out.println("Hello from A");
    }
}
