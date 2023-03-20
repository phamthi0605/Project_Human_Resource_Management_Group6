package com.group6;

import java.lang.reflect.AccessibleObject;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.SimpleTimeZone;

public class Solution {
    static Scanner scanner = new Scanner(System.in);
    static ManagementEmployee managementEmployee = new ManagementEmployee();


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

        // boolean statusLogin = account.login();

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
                            System.out.println("\tQUẢN LÝ NHÂN VIÊN");
                            menuEmployee();
                            System.out.println("\tChọn chức năng quản lý nhân viên:");
                            int subOption = Validation.checkInputInt();
                            while (subOption < 1 || subOption > 6) {
                                System.out.println("Chọn lại chức năng: ");
                                subOption = Validation.checkInputInt();
                            }
                            if (subOption == 1) {
                                System.out.println("Hiển thị danh sách nhân viên");
                                List<Employee> employeeList = managementEmployee.getListEmployee();
                                System.out.printf("%-15s%-20s%-15s%-10s%-16s%-25s%-20s%-20s%-16s%-10s\n", "EmployeeID", "FullName", "Position", "Age", "Phone", "Email", "Salary", "Tax", "DepartmentID", "IsManager");
                                for (Employee employee : employeeList) {
                                    employee.showData();
                                }
                            }
                            if (subOption == 2) {
                                List<Employee> employeeList = managementEmployee.getListEmployee();
                                while (true) {
                                    Employee employee = new Employee();
                                    System.out.println("Thêm nhân viên mới");
                                    System.out.println("Mã nhân viên: ");
                                    String employeeId = scanner.nextLine();
                                    System.out.println("Họ và tên: ");
                                    String fullName = scanner.nextLine();
                                    if (!Validation.checkEmployeeById(employeeList, employeeId, fullName)) {
                                        System.err.println("Employee id has exist . Pleas re-input.");
                                        continue;
                                    }
                                    System.out.println("Vị trí: ");
                                    String position = scanner.nextLine();
                                    System.out.println("Email: ");
                                    String email = scanner.nextLine();
                                    System.out.println("Lương: ");
                                    float salary = Float.parseFloat(scanner.nextLine());
                                    // tính thuế
                                    employee.setPerson_Income_Tax(salary);
                                    float tax = employee.getPerson_Income_Tax();
                                    // lấy currentDate
                                    Date date = new Date();
                                    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
                                    String hireDate = formatDate.format(date);
                                    // nhập phòng ban cho nhân viên
                                    System.out.println("Mã phòng ban: (1. HR), (2. Sale), (3. Accounting),(4. Dev):");
                                    int deptId = Integer.parseInt(scanner.nextLine());
                                    // nhập quản lý cho nhân viên
                                    System.out.println("Quản lý của nhân viên: ");
                                    String deptManagerID = scanner.nextLine();
                                    String isManager = null;
                                    if (deptManagerID.equals("y")) {
                                        isManager = "'1'";
                                    }
                                    employee = new Employee(employeeId, fullName, position, email, salary, tax, hireDate, deptId, isManager);
                                    if (Validation.checkemployeeExist(employeeList, employeeId, email)) {
                                        ManagementEmployee managementEmployee = new ManagementEmployee(employee);
                                        managementEmployee.addEmployee();
                                        break;
                                    }
                                    System.err.println("Add employee duplicate.");
                                }

                            }
                            if (subOption == 3) {
                                List<Employee> employeeList = managementEmployee.getListEmployee();
                                while (true) {
                                    Employee employeeUpdate = new Employee();
                                    System.out.println("Cập nhật thông tin nhân viên");
                                    System.out.println("Nhập mã nhân viên cần cập nhật: ");
                                    String employeeId = scanner.nextLine();
                                    System.out.println("Họ và tên: ");
                                    String fullName = scanner.nextLine();
                                    if (!Validation.checkEmployeeById(employeeList, employeeId, fullName)) {
                                        System.err.println("Employee id has exist . Pleas re-input.");
                                        continue;
                                    }
                                    System.out.println("Vị trí: ");
                                    String position = scanner.nextLine();
                                    System.out.println("Tuổi: ");
                                    int age = Integer.parseInt(scanner.nextLine());
                                    System.out.println("Phone number: ");
                                    String phoneNumber = scanner.nextLine();
                                    System.out.println("Email: ");
                                    String email = scanner.nextLine();
                                    System.out.println("Lương: ");
                                    float salary = Float.parseFloat(scanner.nextLine());
                                    // tính thuế
                                    employeeUpdate.setPerson_Income_Tax(salary);
                                    float tax = employeeUpdate.getPerson_Income_Tax();
                                    // lấy currentDate
                                    Date date = new Date();
                                    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
                                    String hireDate = formatDate.format(date);
                                    // nhập phòng ban cho nhân viên
                                    System.out.println("Mã phòng ban: (1. HR), (2. Sale), (3. Accounting),(4. Dev):");
                                    int deptId = Integer.parseInt(scanner.nextLine());
                                    // nhập quản lý cho nhân viên
                                    System.out.println("Quản lý của nhân viên: ");
                                    String deptManagerID = scanner.nextLine();
                                    String isManager = null;
                                    if (deptManagerID.equals("y")) {
                                        isManager = "'1'";
                                    }
                                    employeeUpdate = new Employee(employeeId, fullName, position, age, phoneNumber, email, salary, tax, hireDate, deptId, isManager);
                                    if (Validation.checkemployeeExist(employeeList, employeeId, email)) {
                                        ManagementEmployee managementEmployee = new ManagementEmployee(employeeUpdate);
                                        managementEmployee.updateEmployee();
                                        break;
                                    }
                                    System.err.println("Update employee duplicate.");


                                }


                            }
                            if (subOption == 4) {
                                System.out.println("Xoá nhân viên");
                                System.out.println("Please enter employee id to remove:");
                                String employeeId = scanner.nextLine();
                                Employee employee = new Employee();
                                employee.setEmployee_id(employeeId);
                                ManagementEmployee manage = new ManagementEmployee(employee);
                                manage.removeEmployee();
                            }
                            if (subOption == 5) {
                                System.out.println("Tìm kiếm nhân viên theo bất kì mã, tên, số điện thoại hoặc email: ");
                                Employee searchEmp = new Employee();
                                System.out.println("Nhập mã nhân viên muốn tìm kiếm: ");
                                String employeeID = scanner.nextLine();
                                searchEmp.setEmployee_id(employeeID);
                                System.out.println("Nhập tên nhân viên muốn tìm kiếm: ");
                                String fullName = scanner.nextLine();
                                searchEmp.setFullName(fullName);
                                System.out.println("Nhập số điện thoại muốn tìm kiếm: ");
                                String phone = scanner.nextLine();
                                searchEmp.setPhoneNumber(phone);
                                System.out.println("Nhập email muốn tìm kiếm: ");
                                String email = scanner.nextLine();
                                searchEmp.setEmail(email);
                                ManagementEmployee managementEmployee = new ManagementEmployee(searchEmp);
                                managementEmployee.searchEmployee();
                            }
                            if (subOption == 6) {
                                System.out.println("Thoát");
                                break;
                            }
                            break;
                        case 3:
                            //ManagementDepartment mgtd = new ManagementDepartment();
                            System.out.println("\tMANAGEMENT DEPARTMENT");
                            menuDepartment();
                            System.out.println("\tChọn chức năng quản lý phòng ban:");
                            int optionDept = Validation.checkInputInt();
                            while (optionDept < 1 || optionDept > 7) {
                                System.out.println("Chọn lại chức năng: ");
                                optionDept = Validation.checkInputInt();
                            }
                            if (optionDept == 1) {
                                System.out.println("Danh sách phòng ban");
                                ManagementDepartment management = new ManagementDepartment();
                                management.getDepartment();
                            }
                            if (optionDept == 2) {
                                System.out.println("Thêm phòng ban");
                                Department department = new Department();
                                System.out.println("Tên phòng ban: ");
                                String deptName = scanner.nextLine();
                                department.setDepartmentName(deptName);
                                System.out.println("Địa chỉ: ");
                                String address = scanner.nextLine();
                                department.setAddress(address);
                                ManagementDepartment management = new ManagementDepartment(department);
                                management.addDepartment();
                            }
                            if (optionDept == 3) {
                                System.out.println("Cập nhật phòng ban");
                                System.out.println("Nhập mã phòng ban cần cập nhật: ");
                                int deptId = Integer.parseInt(scanner.nextLine());
                                System.out.println("Tên phòng ban:");
                                String deptNameUpdate = scanner.nextLine();
                                System.out.println("Địa chỉ: ");
                                String addressUpdate = scanner.nextLine();
                                Department department = new Department(deptId, deptNameUpdate, addressUpdate);
                                ManagementDepartment management = new ManagementDepartment(department);
                                management.updateDepartment();
                            }
                            if (optionDept == 4) {
//                                System.out.println("Xoá phòng ban");
//                                Department dept = new Department();
//                                System.out.println("Nhập phòng ban để check: ");
//                                int deptID = scanner.nextInt();
//                                dept.setDepartmentId(deptID);
//                                ManagementDepartment managementDepartment = new ManagementDepartment(dept);
//                                int countEmployeeID = managementDepartment.getEmployeeByDeptID();
//                                System.out.println(countEmployeeID);
                            }
                            if (optionDept == 5) {
                                System.out.println("Xoá nhân viên từ phòng ban");
                                System.out.println("Nhập mã nhân viên cần xoá: ");
                                String employeeID = scanner.nextLine();
                                Employee employee = new Employee();
                                employee.setEmployee_id(employeeID);
                                ManagementDepartment manage = new ManagementDepartment(employee);
                                manage.removeEmployeeByDeptID();
                            }
                            if (optionDept == 6) {
                                // Có trong thực hiện update thông tin nhân viên
                                System.out.println("Chuyển phòng ban cho nhân viên");
                            }

                            if (optionDept == 7) {
                                System.out.println("Thoát");
                                break;
                            }
                            break;
                        case 4:
                            System.out.println("Thống kê");
                            menuStatistical();
                            System.out.println("\tChọn chức năng thống kê:");
                            int optionStatistical = Validation.checkInputInt();
                            while (optionStatistical < 1 || optionStatistical > 5) {
                                System.out.println("Chọn lại chức năng: ");
                                optionStatistical = Validation.checkInputInt();
                            }
                            if (optionStatistical == 1) {
                                System.out.println("Số lượng nhân viên mỗi phòng ban");
                                managementEmployee.getEmployeeByDepatID();
                            }
                            if (optionStatistical == 2) {
                                System.out.println("Danh sách nhân viên mới vào tháng này, năm nay");
                            }
                            if (optionStatistical == 3) {
                                System.out.println("Liệt kê số nhân viên có phòng ban trên 20 người");
                            }
                            break;
                        case 5:
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
        System.out.println("\t6.Change department from employee");
        System.out.println("\t7.Exit");
    }

    public static void menuStatistical() {
        System.out.println("\t1.Số lượng nhân viên mỗi phòng ban");
        System.out.println("\t2.Danh sách nhân viên mới vào tháng này, năm nay");
        System.out.println("\t3.Liệt kê số nhân viên có phòng ban trên 20 người");
    }


}
