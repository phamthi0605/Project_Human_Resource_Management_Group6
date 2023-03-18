package com.group6;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Solution {
    public static void main(String[] args) {
        ManagementEmployee account = new ManagementEmployee(new Admin("thipt1", "1234"));
        int choose = 0;
        menuProgram();
        System.out.println("Chọn chức năng: ");
        choose = Validation.checkInputInt();
        while (choose != 1 && choose != 4) {
            System.out.println("Bạn chưa đăng nhập!");
            System.out.println("Chọn lại chức năng: ");
            choose = Validation.checkInputInt();
        }
        boolean statusLogin = account.login();

        if (choose == 1) {
            if (!statusLogin) {
                System.out.println("Đăng nhập thất bại!");
            } else {
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
                            while (subOption < 1 || subOption > 6) {
                                System.out.println("Chọn lại chức năng: ");
                                subOption = Validation.checkInputInt();
                            }
                            if (subOption == 1) {
                                System.out.println("Thêm nhân viên mới");
                                Employee employee = new Employee();
                                Scanner scanner = new Scanner(System.in);
                                System.out.println("Mã nhân viên: ");
                                int employeeId = Integer.parseInt(scanner.nextLine());
                                employee.setEmployee_id(employeeId);


                                System.out.println("Họ và tên: ");
                                String fullName = scanner.nextLine();
                                employee.setFullName(fullName);

                                System.out.println("Vị trí: ");
                                String position = scanner.nextLine();
                                employee.setPosition(position);

                                System.out.println("Email: ");
                                String email = scanner.nextLine();
                                employee.setEmail(email);

                                System.out.println("Lương: ");
                                float salary = Float.parseFloat(scanner.nextLine());
                                employee.setSalary(salary);

                                // tính thuế cho nhân viên
                                employee.setPerson_Income_Tax(salary);
//                                System.out.println("Thuế: ");
//                                float personIncomeTax = scanner.nextFloat();
                                //   employee.setPerson_Income_Tax();

                                //  System.out.println("Ngày bắt đầu làm: ");
                                Date date = new Date();
                                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
                                String hireDate = formatDate.format(date);
                                employee.setHire_date(hireDate);
                                // nhập phòng ban cho nhân viên
                                System.out.println("Mã phòng ban: (1. HR), (2. Sale), (3. Accounting),(4. Dev):");
                                int deptId = Integer.parseInt(scanner.nextLine());
                                employee.setDepartment_id(deptId);
                                // 1 department có nhiều nhân viên
                                // 1 nhân viên sẽ có 1 quản lý
                                // nhiều nhân viên trong 1 phòng ban chỉ cùng 1 quản lý duy nhất: (departmentId, managerID) unique
                                System.out.println("Quản lý của nhân viên: ");
                                String deptManagerID = scanner.nextLine();
                                String isManager = null;
                                if (deptManagerID.equals("y")) {
                                    isManager = "'1'";
                                }
                                employee.setIs_manager(isManager);

                                ManagementEmployee manageEmployee = new ManagementEmployee(employee);
                                //  System.out.println(employee.toString());
                                manageEmployee.addEmployee();
                            }
                            if (subOption == 2) {
                                System.out.println("Cập nhật nhân viên");

                            }
                            if (subOption == 3) {
                                System.out.println("Xoá nhân viên");
                            }
                            if (subOption == 4) {
                                System.out.println("Tìm kiếm nhân viên");
                            }
                            if (subOption == 5) {
                                System.out.println("Hiển thị danh sách nhân viên");
                            }
                            if (subOption == 6) {
                                System.out.println("Thoát");
                                break;
                            }

                            break;
                        case 3:
                            System.out.println("Danh sách phòng ban");
                            break;
                        case 4:
                            System.out.println("Exit");
                            System.exit(0);
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
