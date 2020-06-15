package com.example.modernjava.part3.observer;

public interface Subject {
    void registerObserver(Observer o);
    void notifyObserver(String tweet);
}
