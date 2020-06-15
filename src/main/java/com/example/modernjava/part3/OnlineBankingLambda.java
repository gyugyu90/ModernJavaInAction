package com.example.modernjava.part3;

import java.util.function.Consumer;

public class OnlineBankingLambda {
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }
}
