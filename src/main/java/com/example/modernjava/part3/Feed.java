package com.example.modernjava.part3;

import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject {

    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void registerObserver(Observer o) {
        this.observers.add(o);
    }

    @Override
    public void notifyObserver(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }
}
