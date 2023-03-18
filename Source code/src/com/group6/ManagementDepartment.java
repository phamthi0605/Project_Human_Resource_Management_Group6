package com.group6;

import java.sql.*;
import java.util.Scanner;

public class ManagementDepartment {
    Department department;

    public ManagementDepartment() {

    }

    public ManagementDepartment(Department dept) {
        this.department = dept;
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

    public void removeDepartment() {

    }
}
