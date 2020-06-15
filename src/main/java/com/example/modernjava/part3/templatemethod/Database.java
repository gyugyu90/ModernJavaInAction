package com.example.modernjava.part3.templatemethod;

class Database {
    public static Customer getCustomerWithId(int id) {
        return new Customer();
    }
}
