package com.example.modernjava.part3.dsl;

import java.util.function.Consumer;

public class TradeBuilder {

    private final MethodChainingOrderBuilder builder;
    private final Trade trade = new Trade();

    public TradeBuilder(MethodChainingOrderBuilder builder, Trade.Type type, int quantity) {
        this.builder = builder;
        trade.setType(type);
        trade.setQuantity(quantity);
    }

    public StockBuilder stock(String symbol) {
        return new StockBuilder(builder, trade, symbol);
    }


}
