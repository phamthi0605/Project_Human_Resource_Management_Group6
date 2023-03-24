package com.group6;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class FunctionEmployee {
    static Scanner scanner = new Scanner(System.in);
    static ManagementEmployee managementEmployee = new ManagementEmployee();

    public static void showListEmployee() {
        System.out.println("Hiển thị danh sách nhân viên");
        List<Employee> employeeList = managementEmployee.getListEmployee();
        System.out.printf("%-15s%-20s%-15s%-10s%-16s%-25s%-20s%-20s%-16s%-10s\n", "EmployeeID", "FullName", "Position", "Age", "Phone", "Email", "Salary", "Tax", "DepartmentID", "IsManager");
        for (Employee employee : employeeList) {
            employee.showData();
        }
    }

    public static void addEmployee() {
        List<Employee> employeeList = managementEmployee.getListEmployee();
        while (true) {
            Employee employee = new Employee();
            System.out.println("Thêm nhân viên mới");
            System.out.println("Mã nhân viên: ");
            String employeeId = Validation.checkInputString();
            System.out.println("Họ và tên: ");
            String fullName = Validation.checkInputString();
            if (!Validation.checkEmployeeById(employeeList, employeeId, fullName)) {
                System.err.println("Nhân viên " + employeeId + " đã tồn tại. Nhập lại mã nhân viên: ");
                continue;
            }
            System.out.println("Vị trí: ");
            String position = Validation.checkInputString();
            System.out.println("Email: ");
            String email = Validation.checkInputString();
            System.out.println("Lương: ");
            float salary = Validation.checkSalary();
            // tính thuế
            employee.setPerson_Income_Tax(salary);
            float tax = employee.getPerson_Income_Tax();
            // lấy currentDate
            Date date = new Date();
            SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String hireDate = formatDate.format(date);
            // nhập phòng ban cho nhân viên
            System.out.println("Mã phòng ban:");
            int deptId = managementEmployee.checkInputDepartment(Integer.parseInt(scanner.nextLine()));
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
            System.err.println("Thêm nhân viên thành công.");
        }

    }

    public static void updateEmployee() {
        List<Employee> employeeList = managementEmployee.getListEmployee();
        if (employeeList.isEmpty()) {
            System.err.println("Không có dữ liệu.");
            return;
        }
        System.out.println("Cập nhật thông tin nhân viên");
        for (Employee employee : employeeList) {
            employee.showData();
        }
        while (true) {
            Employee employeeUpdate = new Employee();
            System.out.println("Nhập mã nhân viên cần cập nhật: ");
            String employeeId = scanner.nextLine();
            List<Employee> listFindEmployee = managementEmployee.getListEmployeeById(employeeList, employeeId);
            if (listFindEmployee.isEmpty()) {
                System.err.println("Không tìm thấy nhân viên");
                continue;
            } else {
                System.out.println("Kết quả tìm kiếm: ");
                System.out.printf("%-15s%-20s%-15s%-10s%-16s%-25s%-20s%-20s%-16s%-10s\n", "EmployeeID", "FullName", "Position", "Age", "Phone", "Email", "Salary", "Tax", "DepartmentID", "IsManager");
                for (Employee employee : listFindEmployee) {
                    employee.showData();
                }
                System.out.println("Họ và tên: ");
                String fullName = Validation.checkInputString();
                System.out.println("Vị trí: ");
                String position = Validation.checkInputString();
                System.out.println("Tuổi: ");
                int age = Validation.checkInputAge();
                System.out.println("Số điện thoại: ");
                String phoneNumber = Validation.checkInputString();
                List<String> listCheckPhone = new ArrayList<>();
                for (int i = 0; i < employeeList.size(); i++) {
                    listCheckPhone.add(employeeList.get(i).getPhoneNumber());
                }
                while (listCheckPhone.contains(phoneNumber)) {
                    System.out.println("Số điện thoại " + phoneNumber + " đã tồn tại. Nhập lại: ");
                    phoneNumber = Validation.checkInputString();
                }
                System.out.println("Email: ");
                String email = Validation.checkInputString();
                List<String> listCheckEmail = new ArrayList<>();
                for (int i = 0; i < employeeList.size(); i++) {
                    listCheckEmail.add(employeeList.get(i).getEmail());
                }
                while (listCheckEmail.contains(email)) {
                    System.out.println("Email " + email + " đã tồn tại. Nhập lại: ");
                    email = Validation.checkInputString();
                }
                //String email = Validation.checkInputString();
                System.out.println("Lương: ");
                float salary = Validation.checkSalary();
                // tính thuế
                employeeUpdate.setPerson_Income_Tax(salary);
                float tax = employeeUpdate.getPerson_Income_Tax();
                // lấy currentDate
                Date date = new Date();
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                // SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
                String hireDate = formatDate.format(date);
                // nhập phòng ban cho nhân viên
                System.out.println("Mã phòng ban:");
                int deptId = managementEmployee.checkInputDepartment(Integer.parseInt(scanner.nextLine()));
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

            }
            break;
        }
    }

    public static void deleteEmployee() {
        System.out.println("Xoá nhân viên");
        System.out.println("Nhập mã nhân viên cần xoá: ");
        String employeeId = scanner.nextLine();
        Employee employee = new Employee();
        employee.setEmployee_id(employeeId);
        ManagementEmployee manage = new ManagementEmployee(employee);
        manage.removeEmployee();
    }

    public static void searchEmployee() {
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
}
