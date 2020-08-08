package com.example.modernjava.part5;

public class Quote {
    private final String shopName;
    private final double price;
    private final Discount.Code disountCode;

    public Quote(String shopName, double price, Discount.Code disountCode) {
        this.shopName = shopName;
        this.price = price;
        this.disountCode = disountCode;
    }

    public static Quote parse(String s) {
        String[] split = s.split(":");
        String shopName = split[0];
        double price = Double.parseDouble(split[1]);
        Discount.Code discountCode = Discount.Code.valueOf(split[2]);
        return new Quote(shopName, price, discountCode);
    }

    public String getShopName() {
        return shopName;
    }

    public double getPrice() {
        return price;
    }

    public Discount.Code getDisountCode() {
        return disountCode;
    }
}
