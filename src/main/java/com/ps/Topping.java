package com.ps;

public class Topping {
    // The name of the topping
    private String name;
    // The price of the topping
    private double price;

    // Constructor to initalize the name and price f the topping
    public Topping(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getter method to retrieve the name of the topping.
    public String getName() {
        return name;
    }

    // A getter method to retrieve the price of the topping.
    public double getPrice() {
        return price;
    }
}


