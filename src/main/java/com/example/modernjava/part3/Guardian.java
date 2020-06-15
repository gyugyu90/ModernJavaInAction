package com.example.modernjava.part3;

public class Guardian implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("queen")) {
            System.out.println("Breaking news from London... " + tweet);
        }
    }
}
