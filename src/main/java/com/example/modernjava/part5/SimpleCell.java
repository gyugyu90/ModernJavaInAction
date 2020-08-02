package com.example.modernjava.part5;

import java.util.ArrayList;
import java.util.List;

public class SimpleCell implements Publisher<Integer>, Subscriber<Integer> {

    private int value = 0;
    private String name;
    private List<Subscriber> subscribers = new ArrayList<>();

    public SimpleCell(String name) {
        this.name = name;
    }

    @Override
    public void subscribe(Subscriber<? super Integer> subscriber) {
        subscribers.add(subscriber);
    }

    private void notifyAllSubscribers() {
        subscribers.forEach(subscriber -> subscriber.onNext(this.value));
    }

    @Override
    public void onNext(Integer item) {
        this.value = item;
        System.out.println(this.name + ":" + this.value);
        notifyAllSubscribers();
    }

}

interface Subscriber<T> {
    void onNext(T t);
}

interface Publisher<T> {
    void subscribe(Subscriber<? super T> subscriber);
}