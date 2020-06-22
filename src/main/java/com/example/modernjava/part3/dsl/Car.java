package com.example.modernjava.part3.dsl;

import com.example.modernjava.part1.Color;

public class Car {
    private Color color;
    private String brand;


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
