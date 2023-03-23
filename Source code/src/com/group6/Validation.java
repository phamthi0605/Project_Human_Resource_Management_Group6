package com.group6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Validation {
    static Scanner scanner = new Scanner(System.in);
    static final String PHONE_VALID = "^\\d{9,10}$";
    static final String EMAIL_VALID = "^[0-9A-Za-z+_.%]+@[0-9A-Za-z.-]+\\.[A-Za-z]{2,4}$";

    public static int checkInputInt() {
        while (true) {
            try {
                int result = Integer.parseInt(scanner.nextLine().trim());
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Must be input integer.");
                System.out.print("Enter again: ");
            }
        }
    }

    //check user input yes/ no
    public static boolean checkInputYN() {
        //loop until user input correct
        while (true) {
            System.out.println("Do you want to continue: ");
            String result = scanner.nextLine();
            //return true if user input y/Y
            if (result.equalsIgnoreCase("Y")) {
                return true;
            }
            //return false if user input n/N
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input y/Y or n/N.");
            System.out.print("Enter again: ");
        }
    }

    public static boolean checkInputUD() {
        //loop until user input correct
        while (true) {
            String result = scanner.nextLine();
            //return true if user input u/U
            if (result.equalsIgnoreCase("U")) {
                return true;
            }
            //return false if user input n/N
            if (result.equalsIgnoreCase("N")) {
                return false;
            }
            System.err.println("Please input u/U or n/N.");
            System.out.print("Enter again: ");
        }
    }

    //check update department
    public static String checkInputCourse() {
        //loop until user input correct
        while (true) {
            String result = scanner.nextLine();
            //check input departmentName ='HR', 'Sale','Accounting'
            if (result.equalsIgnoreCase("hr")
                    || result.equalsIgnoreCase("sale")
                    || result.equalsIgnoreCase("accounting")) {
                return result;
            }
            System.out.print("Enter again: ");
        }
    }

    //check employee exist
    public static boolean checkemployeeExist(List<Employee> list, String employeeId,
                                             String email) {
        //  int size = list.size();
        for (Employee employee : list) {
            if (employeeId.equals(employee.getEmployee_id())
                    && email.equalsIgnoreCase(employee.getEmail())) {
                return false;
            }
        }
        return true;
    }

    //check id and exist
    public static boolean checkEmployeeById(List<Employee> list, String employeeID, String fullName) {
        for (Employee employee : list) {
            if (employeeID.equalsIgnoreCase(employee.getEmployee_id())
                    && !fullName.equalsIgnoreCase(employee.getFullName())) {
                return false;
            }
        }
        return true;
    }

    //check employee change or not
    public static boolean checkChangeInfomation(Employee employee, String employeeId,
                                                String fullName, String position, String email,
                                                float salary, float tax,
                                                int deptId, String isManager) {
        if (employeeId.equalsIgnoreCase(employee.getEmployee_id())
                && fullName.equalsIgnoreCase(employee.getFullName())
                && position.equalsIgnoreCase(employee.getPosition())
                && email.equalsIgnoreCase(employee.getEmail())
                && salary == employee.getSalary()
                && tax == employee.getPerson_Income_Tax()
                && deptId == employee.getDepartment_id()
                && isManager.equals(employee.getIs_manager())
        ) {
            return false;
        }

        return true;
    }

    public static boolean checkDepartment(List<Department> list, int deptID, String departName) {
        for (Department department : list) {
            if (deptID == department.getDepartmentId()
                    && !departName.equalsIgnoreCase(department.getDepartmentName())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Kiểm tra dữ liệu nhập vào có bị trùng với giá trị cũ của department ko
     *
     * @param department      : giá trị của 1 bộ phận cũ
     * @param deptId          : mã phòng ban
     * @param deptNameUpdate: thay đổi tên phòng ban
     * @param addressUpdate   : thay đổi địa chỉ phòng ban
     * @return : true nếu giá trị không trùng với department cũ
     */

    public static boolean checkchangeInforDepartment(Department department, int deptId, String deptNameUpdate, String addressUpdate) {
        if (deptId == department.getDepartmentId()
                && deptNameUpdate.equalsIgnoreCase(department.getDepartmentName())
                && addressUpdate.equalsIgnoreCase(department.getAddress())
        ) {
            return false;
        }
        return true;
    }

    public static boolean checkChangeInfomation(Employee employee, String empID, int deptTransfer) {
        if (empID.equalsIgnoreCase(employee.getEmployee_id()) && deptTransfer == employee.getDepartment_id()) {
            return false;
        }
        return true;
    }

    private static int checkIntLimit(int min, int max) {
        while (true) {
            try {
                int n = Integer.parseInt(scanner.nextLine());
                if (n < min || n > max) {
                    throw new NumberFormatException();
                }
                return n;
            } catch (NumberFormatException ex) {
                System.err.println("Re-input");
            }
        }
    }

    public static String checkInputString() {
        //loop until user input true value
        while (true) {
            String result = scanner.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty.");
            } else {
                return result;
            }
        }
    }

    private static String checkIsDepartment() {
        while (true) {
            int n = checkIntLimit(1, 4);
            String result = null;
            switch (n) {
                case 1:
                    result = "HR";
                    break;
                case 2:
                    result = "Dev";
                    break;
                case 3:
                    result = "Accounting";
                    break;
                case 4:
                    result = "Sales";
            }
            return result;
        }
    }

    public static String checkInputPhone() {
        while (true) {
            String result = scanner.nextLine().trim();
            if (result.length() != 0 && result.matches(PHONE_VALID)) {
                return result;
            } else {
                System.err.println("Re-input");
            }
        }
    }

    public static String checkInputEmail() {
        while (true) {
            String result = scanner.nextLine().trim();
            if (result.length() != 0 && result.matches(EMAIL_VALID)) {
                return result;
            } else {
                System.err.println("Re-input");
            }
        }
    }


    public static float checkSalary() {
        //loop until user input correct
        while (true) {
            try {
                float result = Float.parseFloat(scanner.nextLine().trim());
                if (result < 0) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Salary must be greater than 0");
                System.out.print("Enter again: ");
            }
        }
    }
}
