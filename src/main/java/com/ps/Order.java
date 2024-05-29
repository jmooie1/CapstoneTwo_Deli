package com.ps;

import java.util.ArrayList;
import java.util.List;


public class Order {
    private List<Sandwich> sandwiches;
    private List<Product> otherSideItems; // This will store drinks and chips as generic products

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.otherSideItems = new ArrayList<>();
    }

    public void addSandwich(Sandwich sandwich) {
        sandwiches.add(sandwich);
    }

    public void addOtherItem(Product item) {
        otherSideItems.add(item);
    }

    public double getTotalCost() {
        double total = 0.0;
        for (Sandwich sandwich : sandwiches) {
            total += sandwich.getPrice();
            for (Topping topping : sandwich.getToppings()) {
                total += topping.getPrice();
            }
        }
        for (Product item : otherSideItems) {
            total += item.getPrice();
        }
        return total;
    }

    public String getOrderDetailsOfSandwich() {
        StringBuilder details = new StringBuilder();
        details.append("Order Details:\n");

        for (Sandwich sandwich : sandwiches) {
            details.append("Sandwich: ").append(sandwich.getName()).append("\n");
            details.append("Size: ").append(sandwich.getSize()).append("\n");
            details.append("Bread: ").append(sandwich.getBreadType()).append("\n");
            details.append("Toppings:\n");
            for (Topping topping : sandwich.getToppings()) {
                details.append(" - ").append(topping.getName()).append("\n");
            }
            details.append("Toasted: ").append(sandwich.isToasted() ? "Yes" : "No").append("\n");
        }

        for (Product item : otherSideItems) {
            details.append(item.getName()).append(": $").append(item.getPrice()).append("\n");
        }

        details.append("Total Cost: $").append(getTotalCost()).append("\n");

        return details.toString();
    }
}
