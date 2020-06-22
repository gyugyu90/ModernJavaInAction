package com.example.modernjava.part3.dsl;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private String customer;
    private List<Trade> trades = new ArrayList<>();

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
    }

    public double getValue() {
        return trades.stream().mapToDouble(Trade::getPrice).sum();
    }
}
