package com.example.modernjava.part3.observer;

public class TweeterApplication {
    public static void main(String[] args) {
        Feed feed = new Feed();
        feed.registerObserver(new NYTimes());
        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());

        feed.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("book")) {
                System.out.println("something about book! " + tweet);
            }
        });

        feed.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("Java")) {
                System.out.println("something about java! " + tweet);
            }
        });

        feed.notifyObserver("The queen said her favourite book is Modern Java in Action!");
    }
}
