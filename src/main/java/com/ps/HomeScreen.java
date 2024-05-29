package com.ps;

import java.util.Scanner;

public class HomeScreen {
    private Scanner scanner = new Scanner(System.in);

    public void showOptions() {
        while (true) {
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            int choice = scanner.nextInt();

            if (choice == 1) {
                OrderScreen orderScreen = new OrderScreen();
                orderScreen.showOptions();
            } else if (choice == 0) {
                System.out.println("Exiting the application.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

