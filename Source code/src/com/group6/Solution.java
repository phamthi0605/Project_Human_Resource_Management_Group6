package com.group6;

import javax.swing.*;

public class Solution {
    public static void main(String[] args) {
        int choose = 0;
        System.out.println("MANAGEMENT RESOURCE EMPLOYEE");
        System.out.println("1. Login");
        System.out.println("2. Management employee");
        System.out.println("3. Management department");
        System.out.println("4. Exit");
        System.out.println("Please choose option: ");
        choose = Validation.checkInputInt();
        while (choose != 1 && choose != 4) {
            System.out.println("You are not logged in!");
            System.out.println("Please choose option again: ");
            choose = Validation.checkInputInt();
        }
        if (choose == 1) {
            // Ham đăng nhập
            System.out.println("Login successfully!");
            while (choose < 2 || choose > 6) {
                System.out.println("Please choose option from 2 to 6: ");
                choose = Validation.checkInputInt();
            }
            if (choose == 2) {
                System.out.println("\tMANAGEMENT EMPLOYEE");
                System.out.println("\tAdd employee");
                System.out.println("\tUpdate employee");
                System.out.println("\tRemove employee");
                System.out.println("\tFind employee");
                System.out.println("Please choose option to manage employees:");
                int option = Validation.checkInputInt();
                while (option < 1 || choose > 4) {
                    System.out.println("Please choose option from 1 to 4: ");
                    option = Validation.checkInputInt();
                }
                if (option == 1) {
                    System.out.println("Add employee");
                }
                if (option == 2) {
                    System.out.println("Update employee");
                }if (option ==3){
                    System.out.println("Remove employee");
                }if (option ==4){
                    
                }

            }
            if (choose == 3) {
                System.out.println("\tMANAGEMENT DEPARTMENT");
                System.out.println("\tAdd department");
                System.out.println("\tUpdate department");
                System.out.println("\tRemove department");
                System.out.println("\tAdd employee in department");
                System.out.println("\tRemove employee from department");
                System.out.println("\tChange department from employee");
                System.out.println("\tExit");

            }
        }
        if (choose == 4) {
            System.out.println("Exit");
            System.exit(0);
        }

    }


}
