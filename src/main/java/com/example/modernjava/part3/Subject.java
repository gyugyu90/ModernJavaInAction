package com.example.modernjava.part3;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObserver(String tweet);
}
