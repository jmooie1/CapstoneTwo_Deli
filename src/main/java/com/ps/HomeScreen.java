package com.ps;

import java.util.Scanner;


public class HomeScreen {
    Scanner scanner = new Scanner(System.in);


    public void showOptions() {
        while (true) {
            System.out.println("1) New Order");
            System.out.println("0) Exit");
            int userChoice = scanner.nextInt();

            if(userChoice == 1) {
                OrderScreen orderSCreen = new OrderScreen();
                orderSCreen.showOptions();
            } else if (userChoice == 0) {
                System.out.println("Exits the application.");
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }
}

