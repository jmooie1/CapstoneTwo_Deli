package com.ps;

import java.util.Scanner;

public class OrderScreen {
    private Scanner scanner = new Scanner(System.in);

    public void showOptions() {
        while (true) {
            System.out.println("1) Add Sandwich");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Chips");
            System.out.println("4) Checkout");
            System.out.println("0) Cancel Order");
            int choice = scanner.nextInt();
        }
    }


}
