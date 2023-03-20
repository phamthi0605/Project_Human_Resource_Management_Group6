package com.group6;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ManagementEmployee {
    private Employee employee;
    private Admin admin;
    Scanner scanner = new Scanner(System.in);

    public ManagementEmployee() {

    }

    public ManagementEmployee(Admin admin) {
        this.admin = admin;
    }

    public ManagementEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     * Login in to the program
     *
     * @return true: login successfully and return false login failed
     */
    public boolean login() {
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        boolean status = false;
        try {
            PreparedStatement ptm = con.prepareStatement("select username,password from admin where username = ? and password = ? ");
            ptm.setString(1, admin.getUserName());
            ptm.setString(2, admin.getPassword());

            ResultSet rs = ptm.executeQuery();
            status = rs.next();


        } catch (SQLException e) {
            e.getMessage();
        }
        return status;

    }

    /**
     * Add new employees to the database
     */
    public void addEmployee() {
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();
            String sql = "INSERT INTO employee (employee_id, full_name, position, email, salary, person_Income_Tax, hire_date, " +
                    "department_id, is_manager) " +
                    "VALUES ('" + employee.getEmployee_id() + "', '" + employee.getFullName() + "', '" + employee.getPosition() + "'," +
                    "'" + employee.getEmail() + "', '" + employee.getSalary() + "', '" + employee.getPerson_Income_Tax() + "', '" + employee.getHire_date() + "'," +
                    " '" + employee.getDepartment_id() + "', " + employee.getIs_manager() + ")";

            PreparedStatement sm = con.prepareStatement(sql);
            int count = sm.executeUpdate();
            if (count > 0) {
                System.out.println("Add employee successfully!");
            } else {
                System.out.println("Add failed!");
            }
            sm.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Add employees to the database
     */
    public void updateEmployee() {
        String sql = "UPDATE employee SET full_name = ?, position=?, age=?, phone =?, email = ?, " +
                "salary =?, person_Income_Tax=?, hire_date=?, department_id=?, is_manager=?" +
                " WHERE employee_id = ?";
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();

            PreparedStatement psmt = con.prepareStatement(sql);

            // set value for parameter of sql statement
            psmt.setString(1, employee.getFullName());
            psmt.setString(2, employee.getPosition());
            psmt.setInt(3, employee.getAge());
            psmt.setString(4, employee.getPhoneNumber());
            psmt.setString(5, employee.getEmail());
            psmt.setFloat(6, employee.getSalary());
            psmt.setFloat(7, employee.getPerson_Income_Tax());
            psmt.setString(8, employee.getHire_date());
            psmt.setInt(9, employee.getDepartment_id());
            psmt.setString(10, employee.getIs_manager());

            psmt.setString(11, employee.getEmployee_id());

            psmt.executeUpdate();
            int count = psmt.executeUpdate();
            if (count > 0) {
                employee.showData();
            } else {
                System.out.println("Update failed!");
            }
            psmt.close();
            con.close();

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    /**
     * Remove employee by employee id
     */
    public void removeEmployee() {
        Scanner scanner = new Scanner(System.in);
        String sql = "DELETE from employee WHERE employee_id = ?";
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();

            PreparedStatement sm = con.prepareStatement(sql);
            sm.setString(1, employee.getEmployee_id());
            sm.executeUpdate();
            System.out.println("Delete successfully!");
        } catch (SQLException e) {
            e.getMessage();
        }
    }


    public ResultSet searchEmployee() {
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        ResultSet result = null;
        try {
            PreparedStatement ptm = con.prepareStatement("select * from employee where employee_id = ? or full_name =? or phone=? or email=? ");
            ptm.setString(1, employee.getEmployee_id());
            ptm.setString(2, employee.getFullName());
            ptm.setString(3, employee.getPhoneNumber());
            ptm.setString(4, employee.getEmail());
            result = ptm.executeQuery();
            if (result.next() == false) {
                System.out.println("Data empty!");
            } else {
                System.out.printf("%-8s%-15s%-20s%-15s%-10s%-16s%-25s%-20s%-20s%-10s\n", "ID", "EmployeeID", "FullName", "Position", "Age", "Phone", "Email", "Salary", "Tax", "DepartmentID");
                do {
                    int id = result.getInt("id");
                    String employeeId = result.getString("employee_id");
                    String fullName = result.getString("full_name");
                    String position = result.getString("position");
                    int age = result.getInt("age");
                    String phoneNumber = result.getString("phone");
                    String email = result.getString("email");
                    float salary = result.getFloat("salary");
                    float tax = result.getFloat("person_Income_Tax");
//                    Date hireDate = result.getDate("hire_date");
//                    Date endDate = result.getDate("end_date");
                    int deptID = result.getInt("department_id");
                    System.out.printf("%-8s%-15s%-20s%-15s%-10d%-16s%-25s%-20f%-20f%-10d\n", id, employeeId, fullName, position, age, phoneNumber, email, salary, tax, deptID);

                }
                while (result.next());
            }

        } catch (SQLException e) {
            e.getMessage();
        }
        return result;

    }

    public List<Employee> getListEmployee() {
        List<Employee> list = new ArrayList<>();
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        Statement sm = null;
        try {
            sm = con.createStatement();
            ResultSet rs = sm.executeQuery("select * from employee");
            if (!rs.next()) {
                System.out.println("Data empty!");
            } else {
                do {
                    Employee employee = new Employee(
                            rs.getString("employee_id"),
                            rs.getString("full_name"),
                            rs.getString("position"),
                            rs.getInt("age"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getFloat("salary"),
                            rs.getFloat("person_Income_Tax"),
                            rs.getString("hire_date"),
                            rs.getString("end_date"),
                            rs.getInt("department_id"),
                            rs.getString("is_manager")
                    );
                    list.add(employee);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Employee> getListEmployeeByDeptId() {
        List<Employee> list = new ArrayList<>();
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        ResultSet rs = null;
        try {
            PreparedStatement ptm = con.prepareStatement("SELECT * FROM employee\n" +
                    "WHERE department_id =?");
            // set value for parameter of sql statement
            ptm.setInt(1, employee.getDepartment_id());
            rs = ptm.executeQuery();
            if (rs.next() == false) {
                System.out.println("Bộ phận này không có nhân viên!");
            } else {
                do {
                    Employee employee = new Employee(
                            rs.getString("employee_id"),
                            rs.getString("full_name"),
                            rs.getString("position"),
                            rs.getInt("age"),
                            rs.getString("phone"),
                            rs.getString("email"),
                            rs.getFloat("salary"),
                            rs.getFloat("person_Income_Tax"),
                            rs.getString("hire_date"),
                            rs.getString("end_date"),
                            rs.getInt("department_id"),
                            rs.getString("is_manager"));
                    list.add(employee);
                } while (rs.next());
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    //Get list employee find by id
    public List<Employee> getListEmployeeById(List<Employee> ls, String employeeID) {
        List<Employee> getList = new ArrayList<>();
        for (Employee employee : ls) {
            if (employeeID.equalsIgnoreCase(employee.getEmployee_id())) {
                getList.add(employee);
            }
        }
        return getList;
    }


    public void getEmployeeByDepatID() {
    }
}
