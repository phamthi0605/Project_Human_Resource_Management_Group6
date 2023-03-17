package com.group6;


public class Solution {
    public static void main(String[] args) {
        int choose = 0;
        menuProgram();
        System.out.println("Please choose option: ");
        choose = Validation.checkInputInt();
        while (choose != 1 && choose != 4) {
            System.out.println("You are not logged in!");
            System.out.println("Please choose option again: ");
            choose = Validation.checkInputInt();
        }
        // Hàm xử lý login ở đây
        if (choose == 1) {
            System.out.println("Đăng nhập thành công!");
            while (true) {
                menuProgram();
                System.out.println("Vui lòng nhập từ 2 đến 4 để chọn chức năng muốn sử dụng");
                choose = Validation.checkInputInt();
                switch (choose) {
                    case 2:
                        System.out.println("\tQUẢN LÝ NHÂN VIÊN");
                        menuEmployee();
                        System.out.println("\tChọn chức năng quản lý nhân viên:");
                        int subOption = Validation.checkInputInt();
                        while (subOption < 1 || subOption > 5) {
                            System.out.println("Please choose option again: ");
                            subOption = Validation.checkInputInt();
                        }
                        if (subOption == 1) {
                            System.out.println("Add employee");
                            //   break;
                        }
                        if (subOption == 2) {
                            System.out.println("Update employee");
                        }
                        if (subOption == 3) {
                            System.out.println("Remove");
                        }
                        if (subOption == 4) {
                            System.out.println("Find");
                        }
                        if (subOption == 5) {
                            System.out.println("Exit");
                            break;
                        }

                        break;
                    case 3:
                        System.out.println("Danh sách phòng ban");
                        break;
                    case 4:
                        System.out.println("Exit");
                        break;
                }
            }
        }

    }


    public static void menuProgram() {
        System.out.println("MANAGEMENT RESOURCE EMPLOYEE");
        System.out.println("1. Login");
        System.out.println("2. Management employee");
        System.out.println("3. Management department");
        System.out.println("4. Exit");
    }

    public static void menuEmployee() {
        System.out.println("\t1.Add employee");
        System.out.println("\t2.Update employee");
        System.out.println("\t3.Remove employee");
        System.out.println("\t4.Find employee");
        System.out.println("\t5.Exit");
    }

    public static void menuDepartment() {
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
