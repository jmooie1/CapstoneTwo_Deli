package com.ps;

public class Product {
    // Fields that are being used to store the name and the price of the product.
    private String name;
    private double price;

    // Constructor to initalize the Product object with the name and price.
    public Product(String name, double price) {
        this.name = name; // Initializes the name of the product
        this.price = price; // Initializes the price of the product
    }

    // Getter method to retrieve the name of the product
    public String getName() {
        return name; // Returns the name of the product
    }

    // Getter method to retrieve the price of the product
    public double getPrice() {
        return price; // Returns the price of the product
    }
}
