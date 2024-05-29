package com.ps;

import java.util.Scanner;

public class HomeScreen {
    private Scanner scanner = new Scanner(System.in); // Scanner object for user input.

    // Method to display the options on the home screen and to handle user input.
    public void showOptions() {
        while (true) { // An infinite loop to keep displaying options until the user chooses to exit.
            System.out.println("1) New Order"); // Option to start a new order.
            System.out.println("0) Exit"); // Option to exit the application.
            int choice = scanner.nextInt(); // Reads the user's choice.

            if (choice == 1) { // If the user chooses to start a new order.
                OrderScreen orderScreen = new OrderScreen(); // It then creates a new instance of OrderScreen.
                orderScreen.showOptions(); // Show the options for the order screen
            } else if (choice == 0) { // If the user chooses to exit.
                System.out.println("Exiting the application."); // Displays the exit message
                break; // Exits the loop and ends the application.
            } else { // If user enters an invalid choice
                System.out.println("Invalid choice. Please try again."); // Displays the error message.
            }
        }
    }
}

