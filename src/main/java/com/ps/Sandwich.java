package com.ps;

import java.util.ArrayList;
import java.util.List;

public class Sandwich extends Product{
    private String size;
    private String breadType;
    private List<Topping> toppings;
    private boolean isToasted;

    public Sandwich(String name, double price, String size, String breadType) {
        super(name, price);
        this.size = size;
        this.breadType = breadType;
        this.toppings = new ArrayList<>();
        this.isToasted = false;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void setToasted(boolean toasted) {
        isToasted = toasted;
    }

    public String getSize() {
        return size;
    }

    public String getBreadType() {
        return breadType;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public boolean isToasted() {
        return isToasted;
    }
}






