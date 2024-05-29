package com.ps;

import java.util.ArrayList;
import java.util.List;


public class Order {
    private List<Sandwich> sandwiches; // List to store sandwich items.
    private List<Product> otherSideItems; // This will store drinks and chips as generic products

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.otherSideItems = new ArrayList<>();
    }

    // A method that adds a sandwich to the order.
    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    // A method to add other side items which are drinks and chips to the order.
    public void addOtherItem(Product item) {
        otherSideItems.add(item);
    }

    // A method to calculate the total cost of the order.
    public double getTotalCost() {
        double total = 0.0; // Initializes the total cost.
        // This calculates the total cost for each sandwich
        for (Sandwich sandwich : sandwiches) {
            total += sandwich.getPrice(); // This adds the base price of the sandwich.
            // Adds the price of each topping on the sandwich.
            for (Topping topping : sandwich.getToppings()) {
                total += topping.getPrice();
            }
        }
        // Adds the price of each other side items which are drinks and chips.
        for (Product item : otherSideItems) {
            total += item.getPrice();
        }
        return total; // Returns the total cost.
    }

    // This method generates the order details for sandwiches and other side items.
    public String getOrderDetailsOfSandwich() {
        StringBuilder detailsOfSandwich = new StringBuilder(); // StringBuilder to store order details
        detailsOfSandwich.append("Order Details:"); //

        // Appends the details for each sandwich in the order.
        for (Sandwich sandwich : sandwiches) {
            detailsOfSandwich.append("Sandwich: ").append(sandwich.getName()).append("\n"); // Sandwich name
            detailsOfSandwich.append("Size: ").append(sandwich.getSize()).append("\n"); //Sandwich size
            detailsOfSandwich.append("Bread: ").append(sandwich.getBreadType()).append("\n"); // Type of bread
            detailsOfSandwich.append("Toppings:\n"); // Header for all the toppings
            // Append each of the toppings on the sandwich.
            for (Topping topping : sandwich.getToppings()) {
                detailsOfSandwich.append(" - ").append(topping.getName()).append("\n");
            }
            detailsOfSandwich.append("Toasted: ").append(sandwich.isToasted() ? "Yes" : "No").append("\n"); // Toasted or not toasted.
        }

        // Append the details for each side item such as drinks and chips.
        for (Product item : otherSideItems) {
            detailsOfSandwich.append(item.getName()).append(": $").append(item.getPrice()).append("\n"); // Total cost of the order.
        }

        detailsOfSandwich.append("Total Cost: $").append(getTotalCost()).append("\n"); // Total cost of the entire order

        return detailsOfSandwich.toString(); // Returns the order details as a string.
    }
}
