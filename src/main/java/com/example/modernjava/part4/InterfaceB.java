package com.example.modernjava.part4;

public interface InterfaceB extends InterfaceA{
    default void hello() {
        System.out.println("Hello from B");
    }
}
