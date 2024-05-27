package com.ps;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while(!exit) {
            displayHomeScreen();
        }
    }
    private static void displayHomeScreen() {
        System.out.println(" Home Screen ");
        System.out.println("1) New Order");
        System.out.println("2) Exit");
    }


}



