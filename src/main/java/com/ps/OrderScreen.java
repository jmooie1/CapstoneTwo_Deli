package com.ps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;


// Main class to handle the order screen for the DELI shop, also I am using the Number format to get the US local currency to try and get a full number.

public class OrderScreen {
    private final Scanner scanner = new Scanner(System.in);
    private final Order currentOrder = new Order(); // The current order being processed.
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    // Main method to display options all the options and to navigate through the menu.
    public void showOptions() {
        while (true) {
            // Wrapping it into a try and catch just in case if I type something wrong on accident.
            try {
                // Displays the menu options
                System.out.println("1) Add Sandwich");
                System.out.println("2) Add Drink");
                System.out.println("3) Add Chips");
                System.out.println("4) Checkout");
                System.out.println("0) Cancel Order");
                int choice = scanner.nextInt();

                // Handle the user choice.
                if (choice == 1) {
                    addSandwich(); // Calls the method to add a sandwich
                } else if (choice == 2) {
                    addDrink(); // Method to add a drink
                } else if (choice == 3) {
                    addChips(); // Method to add chips
                } else if (choice == 4) {
                    checkout(); // Calls method checkout
                    break; // Exits the loop after checkout
                } else if (choice == 0) {
                    System.out.println("Order has been canceled. Now returning to the Home Screen.");
                    break; // Exits the loop and returns to Home Screen.
                } else {
                    System.out.println("Invalid. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid. Enter an actual number.");
                scanner.next(); // Clears the invalid input.
            }
        }
    }

    // Method to add the sandwich to the order.
    private void addSandwich() {
        try {
            System.out.println("Adding a sandwich...");
            System.out.println("What bread type do you want? (Type 0 if you don't want to order a sandwich)");
            String[] breadTypes = {"White", "Wheat", "Rye", "Wrap"}; // Array of bread types
            for (int i = 0; i < breadTypes.length; i++) {
                System.out.println((i + 1) + ") " + breadTypes[i]); // Displays all the bread types.
            }

            int breadChoice = scanner.nextInt(); // This gets the user's bread choice.
            if (breadChoice == 0) {
                return; // Returns if the user does not want a sandwich.
            }
            String breadType = breadTypes[breadChoice - 1]; // Gets the selected bread type.

            System.out.println("Select a sandwich size.");
            String[] sizesOfBreads = {"4\"", "8\"", "12\""}; // Array of sandwich sizes.
            for (int i = 0; i < sizesOfBreads.length; i++) {
                System.out.println((i + 1) + ")" + sizesOfBreads[i]); // Displays all the sandwich sizes.
            }

            int sizeChoice = scanner.nextInt(); // Captures and takes in the user's size choice.
            String size = sizesOfBreads[sizeChoice - 1]; // Gets the selected size.

            double basePrice = 0.0;
            switch (size) { // Determines the base price based on the size.
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

            // Creates a new sandwich object with the selected options.
            Sandwich sandwich = new Sandwich("Custom Sandwich", basePrice, size, breadType);

            // Arrays of the possible toppings.
            System.out.println("Select your toppings (type 'Done' when finished):");
            String[] Meats = {"Steak", "Ham", "Salami", "Roast Beef", "Chicken", "Bacon"};
            String[] Cheeses = {"American", "Provolone", "Cheddar", "Swiss"};
            String[] RegularToppings = {"Lettuce", "Peppers", "Onions", "Tomatoes", "Jalapeño", "Cucumbers", "Pickles", "Guacamole", "Mushrooms"};
            String[] Sauces = {"Mayo", "Mustard", "Ketchup", "Ranch", "Thousand Islands", "Vinaigrette"};

            // Add meats to the sandwich.
            System.out.println("Select meats:");
            addToppings(sandwich, Meats, 1.00,2.00,3.00, 0.50, 1.00, 1.50);

            // Add cheeses to the sandwich.
            System.out.println("Select cheeses:");
            addToppings(sandwich, Cheeses, 0.75,1.50,2.25,0.30,0.60,0.90);

            // Add regular toppings to the sandwich.
            System.out.println("Select regular toppings:");
            addToppings(sandwich, RegularToppings, 0.0,0.0,0.0,0.0,0.0,0.0);

            // Add sauces to the sandwich.
            System.out.println("Select sauces:");
            addToppings(sandwich, Sauces, 0.0,0.0,0.0,0.0,0.0,0.0);

            System.out.println("Would you like the sandwich toasted? (yes/no)");
            String toastedChoice = scanner.next();
            sandwich.setToasted(toastedChoice.equalsIgnoreCase("yes"));

            // Adds sandwich to the current order.
            currentOrder.addSandwich(sandwich);
            System.out.println("Sandwich added to your order.");
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid. Try again.");
        }
    }

    // Method to add toppings to the sandwich.
    private void addToppings(Sandwich sandwich, String[] options, double priceOf4Inch, double priceOf8Inch, double priceOf12Inch, double extraPrice4Inch, double extraPrice8Inch, double extraPrice12Inch) {
        while (true) {
            try {
                for (int i = 0; i < options.length; i++) {
                    double toppingPrice = 0.0;
                    double extraToppingPrice = 0.0;
                    switch(sandwich.getSize()) { // This determines the price based on the sandwich size.
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
                    // This displays the options with the prices.
                    System.out.println((i + 1) + ") " + options[i] + " (" + getFormattedPrice(sandwich.getSize(),priceOf4Inch, priceOf8Inch, priceOf12Inch) + ")" );
                }
                System.out.println("Type 'Done' to finish adding toppings.");
                String choice = scanner.next(); // Captures the choice.

                if (choice.equalsIgnoreCase("Done")) {
                    break; // Exits the loop if the user is done with adding toppings.
                }

                int optionIndex = Integer.parseInt(choice) - 1; // Get the selected topping index.
                String toppingName = options[optionIndex]; // Get the selected topping name
                double toppingPrice = 0.0;
                double extraToppingPrice = 0.0;
                switch (sandwich.getSize()) { // Determines the price based on sandwich size.
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

                if (toppingPrice > 0.0) { // If the topping has a price, add it as a premium topping.
                    sandwich.addTopping(new PremiumTopping(toppingName, toppingPrice));
                    // Asks if the user wants extra topping.
                    System.out.println("Would you like extra " + toppingName + " cheese? (yes/no)");
                    String extraChoice = scanner.next();
                    if(extraChoice.equalsIgnoreCase("yes")) {
                        sandwich.addTopping(new PremiumTopping("Extra " + toppingName, extraToppingPrice ));
                    }
                } else { // If topping is free, then add it as a regular topping.
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

    // Method to add a drink to the order
    private void addDrink() {
        try {
            System.out.println("Adding a drink...");
            System.out.println("Select drink size:");
            String[] sizesOfDrinks = {"Small", "Medium", "Large"}; // Array of the drink sizes.
            double[] prices = {2.00, 2.50, 3.00};
            for (int i = 0; i < sizesOfDrinks.length; i++) { // The for loop followed by the System.out.println() of this displays the drink sizes.
                System.out.println((i + 1) + ") " + sizesOfDrinks[i] + "(" + currencyFormat.format(prices[i]) + ")");
            }

            System.out.println("0) Back to Order Screen (If you decided to not get a drink) ");
            int sizeChoice = scanner.nextInt(); // Captures what the user's choices.
            if(sizeChoice == 0) {
                return;
            }

            String size = sizesOfDrinks[sizeChoice - 1];
            double price = prices[sizeChoice - 1];

            System.out.println("Enter drink flavor:");
            String flavor = scanner.next();

            // Creates the new drink object with the selected options.
            Product drink = new Product(size + " " + flavor + " Drink", price);
            // Adds drink to the current order.
            currentOrder.addOtherItem(drink);
            System.out.println("Drink added to your order.");
        } catch (InputMismatchException | ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid. Try again.");
            scanner.nextLine();
        }
    }

    // Method to add chips to the order.
    private void addChips() {
        try {
            // Chip types for the user to look and choose from.
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
            int chipsChoice = scanner.nextInt(); // Captures the user's input.
            if (chipsChoice == 0) { // Returns back to the Order Screen
                return;
            }

            String chipType = "";
            switch (chipsChoice) { // This calls the choice of the chips.
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

            // Creates chips object with the selected type.
            Product chips = new Product(chipType, 1.50);
            // This adds chips to the current order.
            currentOrder.addOtherItem(chips);
            System.out.println("Chips added to your order.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid. Try again.");
        }
    }

    // Method to check out and confirms the order.
    private void checkout() {
        try {
            System.out.println(currentOrder.getOrderDetailsOfSandwich()); // Displays the order details
            System.out.println("1) Confirm");
            System.out.println("2) Cancel");

            int choice = scanner.nextInt();
            if (choice == 1) {
                // Saves the receipt and returns to home screen
                System.out.println("Order confirmed. Saving receipt...");
                // Generate and save receipt
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

    // Method to save the receipt into a file
    private void saveReceipt() {
        try {
            // Creates a new receipts folder if it doesn't exist
            File folder = new File("receipts");
            if(!folder.exists()) {
                folder.mkdir();
            }

        // Gets the current date and time for the receipt filename
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd-HHmmss");
        String dateTime = dateFormat.format(new Date());

        File receiptFile = new File("receipts" + dateTime+ ".txt");
        FileWriter fw = new FileWriter(receiptFile);
        BufferedWriter bw = new BufferedWriter(fw);
            // This writes the order details to the file.
            bw.write(currentOrder.getOrderDetailsOfSandwich());
            // And this closes it.
            bw.close();
            System.out.println("Order confirmed. The receipt has been saved.");
        } catch(IOException e) {
            System.out.println("There has been an error saving the receipt" + e.getMessage());
        }
    }
    // This helps format prices based on the sandwich size.
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
        return currencyFormat.format(price); // formats and returns the prices.
    }
}


