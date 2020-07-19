package com.example.modernjava.part4;

public interface InterfaceC extends InterfaceA{
    default void hello() {
        System.out.println("Hello from C");
    }
}

