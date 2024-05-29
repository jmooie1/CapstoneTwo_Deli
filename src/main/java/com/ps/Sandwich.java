package com.ps;

import java.util.ArrayList;
import java.util.List;

// Sandwich class that inherits from Product class
public class Sandwich extends Product{
    private String size; // Size of the sandwich  (4",8",12")
    private String breadType; // Type of bread(White, Wheat, Rye, Wrap)
    private List<Topping> toppings; // List of toppings added to the sandwich
    private boolean isToasted; // Indicates if the sandwich is toasted.

    // A constructor to initalize sandwich object with name, price, size and bread type.
    public Sandwich(String name, double price, String size, String breadType) {
        super(name, price); // Calls the constructor of the superclass (Product)
        this.size = size; // Initialize the size of the sandwich
        this.breadType = breadType; // Initialize the type of bread
        this.toppings = new ArrayList<>(); // Initalize the list of toppings as an empty list
        this.isToasted = false; // The default value for isToasted is always false
    }

    // A method to add a topping to the sandwich.
    public void addTopping(Topping topping) {
        this.toppings.add(topping); // Adds the topping to the list of toppings.
    }

    // A method to set the sandwich as toasted or not.
    public void setToasted(boolean toasted) {
        isToasted = toasted; // Set the isToasted field based on the parameter value.
    }

    // Getter and setter methods.

    // Getter method for the size of the sandwich
    public String getSize() {
        return size;
    }

    // Getter method for the type of bread
    public String getBreadType() {
        return breadType;
    }

    // Getter method for list of toppings.
    public List<Topping> getToppings() {
        return toppings;
    }

    // Getter method to check if the sandwich is toasted
    public boolean isToasted() {
        return isToasted;
    }
}






