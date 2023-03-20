package com.group6;

import java.sql.*;
import java.util.Scanner;

public class ManagementDepartment {
    Department department;
    Employee employee;

    public ManagementDepartment() {

    }

    public ManagementDepartment(Department dept) {
        this.department = dept;
    }

    public ManagementDepartment(Employee employee) {
        this.employee = employee;
    }

    /**
     * Get department from database
     *
     * @return record from table department
     */

    public ResultSet getDepartment() {
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        ResultSet rs = null;
        try {
            Statement sm = con.createStatement();
            rs = sm.executeQuery("SELECT * FROM department");
            if (rs.next() == false) {
                System.out.println("Data empty!");
            } else {
                System.out.printf("%-10s%-21s%-13s\n", "ID", "Departmentn Name", "Address");
                do {
                    int id = rs.getInt("id");
                    String fullName = rs.getString("department_name");
                    String address = rs.getString("address");


                    System.out.printf("%-10d%-21s%-13s\n", id, fullName, address);
                }
                while (rs.next());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public void addDepartment() {
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();
            PreparedStatement sm = con.prepareStatement("INSERT INTO department (department_name, address) VALUE (?,?)");
            sm.setString(1, department.getDepartmentName());
            sm.setString(2, department.getAddress());
            int count = sm.executeUpdate();
            if (count > 0) {
                System.out.println("Add department successfully!");
            } else {
                System.out.println("Add department failed!");
            }
            sm.close();
            con.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void updateDepartment() {
        Scanner scanner = new Scanner(System.in);
        String sql = "UPDATE department SET department_name = ?, address=? WHERE id = ?";
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();
            PreparedStatement sm = con.prepareStatement(sql);

            sm.setString(1, department.getDepartmentName());
            sm.setString(2, department.getAddress());
            sm.setInt(3, department.getDepartmentId());
            sm.executeUpdate();
            int count = sm.executeUpdate();
            if (count > 0) {
                System.out.println("Update department successfully!");
            } else {
                System.out.println("Update department failed!");
            }
        } catch (SQLException e) {
            e.getMessage();
        }
    }

//    public int getEmployeeByDeptID() {
//        int count = 0;
//        Connection con = null;
//        DBContext db = new DBContext();
//        con = db.getConnection();
//        int value = 0;
//        ResultSet rs;
//        try {
//            String sql = "SELECT count(department_id) AS NoDept\n" +
//                    "FROM employee\n" +
//                    "GROUP BY department_id\n" +
//                    "HAVING department_id =?";
//            PreparedStatement sm = con.prepareStatement(sql);
//            sm.setInt(1, department.getDepartmentId());
//            sm.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return value;
//    }
//
//    public void removeDepartment() {
//        Scanner scanner = new Scanner(System.in);
//        Connection con = null;
//        DBContext db = new DBContext();
//        con = db.getConnection();
//        try {
//            int isCheck = getDepartmentByID();
//            if (isCheck > 0) {
//                System.out.println("Không thể xoá department");
//            } else {
//                String sql = "DELETE from department WHERE id = ?";
//                PreparedStatement sm = con.prepareStatement(sql);
//                System.out.println("Please enter employee to remove:");
//                int deptID = scanner.nextInt();
//                sm.setInt(1, deptID);
//                sm.executeUpdate();
//                int result = sm.executeUpdate();
//                if (result > 0) {
//                    System.out.println("Delete department successfully!");
//                } else {
//                    System.out.println("Delete department failed!");
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public void removeEmployeeByDeptID() {
        Scanner scanner = new Scanner(System.in);
        String sql = "DELETE from employee WHERE id = ?";
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();

            PreparedStatement sm = con.prepareStatement(sql);
            sm.setInt(1, employee.getEmployee_id());
            sm.executeUpdate();
            System.out.println("Delete successfully!");
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
