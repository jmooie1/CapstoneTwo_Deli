package com.ps;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;


public class OrderScreen {
    private final Scanner scanner = new Scanner(System.in);
    private final Order currentOrder = new Order();
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    public void showOptions() {
        while (true) {
            try {
                System.out.println("1) Add Sandwich");
                System.out.println("2) Add Drink");
                System.out.println("3) Add Chips");
                System.out.println("4) Checkout");
                System.out.println("0) Cancel Order");
                int choice = scanner.nextInt();

                if (choice == 1) {
                    addSandwich();
                } else if (choice == 2) {
                    addDrink();
                } else if (choice == 3) {
                    addChips();
                } else if (choice == 4) {
                    checkout();
                    break;
                } else if (choice == 0) {
                    System.out.println("Order has been canceled. Now returning to the Home Screen.");
                    break;
                } else {
                    System.out.println("Invalid. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid. Enter an actual number.");
                scanner.next();
            }
        }
    }


    private void addSandwich() {
        try {
            System.out.println("Adding a sandwich...");
            System.out.println("What bread type do you want? (Type 0 if you don't want to order a sandwich)");
            String[] breadTypes = {"White", "Wheat", "Rye", "Wrap"};
            for (int i = 0; i < breadTypes.length; i++) {
                System.out.println((i + 1) + ") " + breadTypes[i]);
            }

            int breadChoice = scanner.nextInt();
            if (breadChoice == 0) {
                return;
            }
            String breadType = breadTypes[breadChoice - 1];

            System.out.println("Select a sandwich size.");
            String[] sizesOfBreads = {"4\"", "8\"", "12\""};
            for (int i = 0; i < sizesOfBreads.length; i++) {
                System.out.println((i + 1) + ")" + sizesOfBreads[i]);
            }

            int sizeChoice = scanner.nextInt();
            String size = sizesOfBreads[sizeChoice - 1];

            double basePrice = 0.0;
            switch (size) {
                case "4\"":
                    basePrice = 5.50;
                    break;
                case "8\"":
                    basePrice = 7.00;
                    break;
                case "12\"":
                    basePrice = 8.50;
                    break;
            }

            Sandwich sandwich = new Sandwich("Custom Sandwich", basePrice, size, breadType);

            System.out.println("Select your toppings (type 'Done' when finished):");
            String[] Meats = {"Steak", "Ham", "Salami", "Roast beef", "Chicken", "Bacon"};
            String[] Cheeses = {"American", "Provolone", "Cheddar", "Swiss"};
            String[] RegularToppings = {"Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeño", "Cucumbers", "Pickles", "Guacamole", "Mushrooms"};
            String[] Sauces = {"Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"};

            // Add meats
            System.out.println("Select meats:");
            addToppings(sandwich, Meats, 1.00,2.00,3.00, 0.50, 1.00, 1.50);

            // Add cheeses
            System.out.println("Select cheeses:");
            addToppings(sandwich, Cheeses, 0.75,1.50,2.25,0.30,0.60,0.90);

            // Add regular toppings
            System.out.println("Select regular toppings:");
            addToppings(sandwich, RegularToppings, 0.0,0.0,0.0,0.0,0.0,0.0);

            // Add sauces
            System.out.println("Select sauces:");
            addToppings(sandwich, Sauces, 0.0,0.0,0.0,0.0,0.0,0.0);

            System.out.println("Would you like the sandwich toasted? (yes/no)");
            String toastedChoice = scanner.next();
            sandwich.setToasted(toastedChoice.equalsIgnoreCase("yes"));

            currentOrder.addSandwich(sandwich);
            System.out.println("Sandwich added to your order.");
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid. Try again.");
        }
    }


    private void addToppings(Sandwich sandwich, String[] options, double priceOf4Inch, double priceOf8Inch, double priceOf12Inch, double extraPrice4Inch, double extraPrice8Inch, double extraPrice12Inch) {
        while (true) {
            try {
                for (int i = 0; i < options.length; i++) {
                    double toppingPrice = 0.0;
                    double extraToppingPrice = 0.0;
                    switch(sandwich.getSize()) {
                        case "4\"":
                            toppingPrice = priceOf4Inch;
                            extraToppingPrice = extraPrice4Inch;
                            break;
                        case "8\"":
                            toppingPrice = priceOf8Inch;
                            extraToppingPrice = extraPrice8Inch;
                            break;
                        case "12\"":
                            toppingPrice = priceOf12Inch;
                            extraToppingPrice = priceOf12Inch;
                            break;
                    }
                    System.out.println((i + 1) + ") " + options[i] + " (" + getFormattedPrice(sandwich.getSize(),priceOf4Inch, priceOf8Inch, priceOf12Inch) + ")" );
                }
                System.out.println("Type 'Done' to finish adding toppings.");
                String choice = scanner.next();

                if (choice.equalsIgnoreCase("Done")) {
                    break;
                }

                int optionIndex = Integer.parseInt(choice) - 1;
                String toppingName = options[optionIndex];
                double toppingPrice = 0.0;
                double extraToppingPrice = 0.0;
                switch (sandwich.getSize()) {
                    case "4\"":
                        toppingPrice = priceOf4Inch;
                        extraToppingPrice = extraPrice4Inch;
                        break;
                    case "8\"":
                        toppingPrice = priceOf8Inch;
                        extraToppingPrice = extraPrice8Inch;
                        break;
                    case "12\"":
                        toppingPrice = priceOf12Inch;
                        extraToppingPrice = extraPrice12Inch;
                        break;
                }

                if (toppingPrice > 0.0) {
                    sandwich.addTopping(new PremiumTopping(toppingName, toppingPrice));
                    System.out.println("Would you like extra " + toppingName + " cheese? (yes/no)");
                    String extraChoice = scanner.next();
                    if(extraChoice.equalsIgnoreCase("yes")) {
                        sandwich.addTopping(new PremiumTopping("Extra " + toppingName, extraToppingPrice ));
                    }
                } else {
                    sandwich.addTopping(new RegularTopping(toppingName));
                }
            } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid. Enter a number or 'Done' to continue the program.");
                scanner.nextLine();
            }
        }
    }

    private void addDrink() {
        try {
            System.out.println("Adding a drink...");
            System.out.println("Select drink size:");
            String[] sizesOfDrinks = {"Small", "Medium", "Large"};
            double[] prices = {2.00, 2.50, 3.00};
            for (int i = 0; i < sizesOfDrinks.length; i++) {
                System.out.println((i + 1) + ") " + sizesOfDrinks[i] + "(" + currencyFormat.format(prices[i]) + ")");
            }

            System.out.println("0) Back to Order Screen (If you decided to not get a drink) ");
            int sizeChoice = scanner.nextInt();
            if(sizeChoice == 0) {
                return;
            }

            String size = sizesOfDrinks[sizeChoice - 1];
            double price = prices[sizeChoice - 1];

            System.out.println("Enter drink flavor:");
            String flavor = scanner.next();

            Product drink = new Product(size + " " + flavor + " Drink", price);
            currentOrder.addOtherItem(drink);
            System.out.println("Drink added to your order.");
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid. Try again.");
            scanner.nextLine();
        }
    }

    private void addChips() {
        try {
            System.out.println("Adding chips...");
            System.out.println("Select chip type (Price: " + currencyFormat.format(1.50) + "):");
            System.out.println("1) Lays Chips Classic");
            System.out.println("2) Lays BBQ Chips");
            System.out.println("3) Ruffles Potato Chips");
            System.out.println("4) Doritos Cool Ranch");
            System.out.println("5) Doritos Nacho Cheese");
            System.out.println("6) Takis Fuego");
            System.out.println("7) Pringles Original");
            System.out.println("8) Pringles Sour Cream and Onion");
            System.out.println("9) SunChips Original");
            System.out.println("0) Back to Order Screen (If you decided to not get chips)");
            int choice = scanner.nextInt();
            if (choice == 0) {
                return;
            }

            String chipType = "";
            switch (choice) {
                case 1:
                    chipType = "Lays Chips Classic";
                    break;
                case 2:
                    chipType = "Lays BBQ Chips";
                    break;
                case 3:
                    chipType = "Ruffles Potato Chips";
                    break;
                case 4:
                    chipType = "Doritos Cool Ranch";
                    break;
                case 5:
                    chipType = "Doritos Nacho Cheese";
                    break;
                case 6:
                    chipType = "Takis Fuego";
                    break;
                case 7:
                    chipType = "Pringles Original";
                    break;
                case 8:
                    chipType = "Pringles Sour Cream and Onion";
                    break;
                case 9:
                    chipType = "SunChips Original";
                    break;
                default:
                    System.out.println("Invalid. Try again.");
                    return;
            }

            Product chips = new Product(chipType, 1.50);
            currentOrder.addOtherItem(chips);
            System.out.println("Chips added to your order.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid. Try again.");
        }
    }

    private void checkout() {
        try {
            System.out.println(currentOrder.getOrderDetailsOfSandwich());
            System.out.println("1) Confirm");
            System.out.println("2) Cancel");

            int choice = scanner.nextInt();
            if (choice == 1) {
                // Save receipt and return to home screen
                System.out.println("Order confirmed. Saving receipt...");
                // Generate and save receipt (simplified)
                System.out.println("Receipt saved.");
            } else if (choice == 2) {
                System.out.println("Order canceled. Returning to home screen.");
            } else {
                System.out.println("Invalid. Try again.");
            }
        } catch(InputMismatchException e){
            System.out.println("Invalid. Try again.");
        }
    }
    public String getFormattedPrice(String size, double priceOf4Inch, double priceOf8Inch, double priceOf12Inch) {
        double price = 0.0;
        switch (size) {
            case "4\"":
                price = priceOf4Inch;
                break;
            case "8\"":
                price = priceOf8Inch;
                break;
            case "12\"":
                price = priceOf12Inch;
                break;
        }
        return currencyFormat.format(price);
    }
}


