package com.group6;

import java.lang.reflect.AccessibleObject;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Solution {
    static Scanner scanner = new Scanner(System.in);
    static ManagementEmployee managementEmployee = new ManagementEmployee();
    static ManagementDepartment managementDepartment = new ManagementDepartment();

    public static void main(String[] args) {

        int choose = 0;
        menuProgram();
        System.out.println("Chọn chức năng: ");
        choose = Validation.checkInputInt();
        while (choose != 1 && choose != 4) {
            System.out.println("Bạn chưa đăng nhập!");
            System.out.println("Chọn lại chức năng: ");
            choose = Validation.checkInputInt();
        }

        if (choose == 1) {
            System.out.println("Nhập username: ");
            String userName = scanner.nextLine();
            System.out.println("Nhập password: ");
            String password = scanner.nextLine();
            Admin admin = new Admin(userName, password);
            ManagementEmployee manageAccount = new ManagementEmployee(admin);
            boolean statusLogin = manageAccount.login();
            while (!statusLogin) {
                System.out.println("Đăng nhập lại");
                System.out.println("Nhập username: ");
                userName = scanner.nextLine();
                System.out.println("Nhập password: ");
                password = scanner.nextLine();
                admin = new Admin(userName, password);
                manageAccount = new ManagementEmployee(admin);
                statusLogin = manageAccount.login();
                if (statusLogin) {
                    break;
                }
            }
            if (statusLogin) {
                System.out.println("Đăng nhập thành công!");
                while (true) {
                    menuProgram();
                    System.out.println("Vui lòng nhập từ 2 đến 5 để chọn chức năng muốn sử dụng:");
                    choose = Validation.checkInputInt();
                    switch (choose) {
                        case 2:
                            while (true) {
                                System.out.println("\tQUẢN LÝ NHÂN VIÊN");
                                menuEmployee();
                                System.out.println("\tChọn chức năng quản lý nhân viên:");
                                int optionEmp = Validation.checkInputInt();
                                while (optionEmp < 1 || optionEmp > 6) {
                                    System.out.println("Chọn lại chức năng: ");
                                    optionEmp = Validation.checkInputInt();
                                }
                                if (optionEmp == 1) {
                                    FunctionEmployee.showListEmployee();
                                }
                                if (optionEmp == 2) {
                                    FunctionEmployee.addEmployee();
                                }
                                if (optionEmp == 3) {
                                    FunctionEmployee.updateEmployee();
                                }
                                if (optionEmp == 4) {
                                    FunctionEmployee.deleteEmployee();
                                }
                                if (optionEmp == 5) {
                                    FunctionEmployee.searchEmployee();
                                }
                                if (optionEmp == 6) {
                                    System.out.println("Thoát");
                                    break;
                                }
                            }
                            break;
                        case 3:
                            while (true) {
                                //ManagementDepartment mgtd = new ManagementDepartment();
                                System.out.println("\tMANAGEMENT DEPARTMENT");
                                menuDepartment();
                                System.out.println("\tChọn chức năng quản lý phòng ban:");
                                int optionDept = Validation.checkInputInt();
                                while (optionDept < 1 || optionDept > 8) {
                                    System.out.println("Chọn lại chức năng: ");
                                    optionDept = Validation.checkInputInt();
                                }
                                if (optionDept == 1) {
                                    FunctionDeapartment.getListDepartment();
                                }
                                if (optionDept == 2) {
                                    FunctionDeapartment.addDepartment();
                                }
                                if (optionDept == 3) {
                                    FunctionDeapartment.updateDepartment();

                                }
                                if (optionDept == 4) {
                                    FunctionDeapartment.deleteDepartment();

                                }
                                if (optionDept == 5) {
                                    FunctionDeapartment.deleteEmployeeByDepartID();
                                }
                                if (optionDept == 6) {
                                    FunctionDeapartment.transferDepartmentForEmployee();

                                }
                                if (optionDept == 7) {
                                    FunctionDeapartment.addDepartmentForEmployee();
                                }
                                if (optionDept == 8) {
                                    System.out.println("Thoát");
                                    break;
                                }

                            }
                            break;

                    }
                }
            }

        }
    }

    public static void menuProgram() {
        System.out.println("MANAGEMENT RESOURCE EMPLOYEE");
        System.out.println("1. Login");
        System.out.println("2. Management employee");
        System.out.println("3. Management department");
        System.out.println("4. Statistical ");
        System.out.println("5. Exit");
    }

    public static void menuEmployee() {
        System.out.println("\t1.List employee");
        System.out.println("\t2.Add employee");
        System.out.println("\t3.Update employee");
        System.out.println("\t4.Remove employee");
        System.out.println("\t5.Find employee");
        System.out.println("\t6. Exit");
    }

    public static void menuDepartment() {
        System.out.println("\t1.List department");
        System.out.println("\t2.Add department");
        System.out.println("\t3.Update department");
        System.out.println("\t4.Remove department");//chưa làm được
        System.out.println("\t5.Remove employee from department");
        System.out.println("\t6.Transfer department for employee");
        System.out.println("\t7.Add department for employee");
        System.out.println("\t8.Exit");
    }


}
