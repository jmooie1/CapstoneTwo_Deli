package com.ps;

public class SandwichPrices {
    // Prices for 4" bread
    private final double meatPrice4Inch = 1.00;
    private final double cheesePrice4Inch = 0.75;
    private final double extraMeatPrice4Inch = 0.50;
    private final double extraCheesePrice4Inch = 0.30;

    // Prices for 8" bread
    private final double meatPrice8Inch = 2.00;
    private final double cheesePrice8Inch = 1.50;
    private final double extraMeatPrice8Inch = 1.00;
    private final double extraCheesePrice8Inch = 0.60;

    // Prices for 12" bread
    private final double meatPrice12Inch = 3.00;
    private final double cheesePrice12Inch = 2.25;
    private final double extraMeatPrice12Inch = 1.50;
    private final double extraCheesePrice12Inch = 0.90;

    public double getMeatPrice(String sizeOfMeat) {
        switch (sizeOfMeat) {
            case "4\"":
                return meatPrice4Inch;
            case "8\"":
                return meatPrice8Inch;
            case "12\"":
                return meatPrice12Inch;
            default:
                return 0.0;
        }
    }

    public double getCheesePrice(String sizeOfCheese) {
        switch (sizeOfCheese) {
            case "4\"":
                return cheesePrice4Inch;
            case "8\"":
                return cheesePrice8Inch;
            case "12\"":
                return cheesePrice12Inch;
            default:
                return 0.0;
        }
    }

    public double getExtraMeatPrice(String sizeOfExtraMeat) {
        switch (sizeOfExtraMeat) {
            case "4\"":
                return extraMeatPrice4Inch;
            case "8\"":
                return extraMeatPrice8Inch;
            case "12\"":
                return extraMeatPrice12Inch;
            default:
                return 0.0;
        }
    }

    public double getExtraCheesePrice(String sizeOfExtraCheese) {
        switch (sizeOfExtraCheese) {
            case "4\"":
                return extraCheesePrice4Inch;
            case "8\"":
                return extraCheesePrice8Inch;
            case "12\"":
                return extraCheesePrice12Inch;
            default:
                return 0.0;
        }
    }
}


