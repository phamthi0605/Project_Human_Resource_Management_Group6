package com.group6;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

    public List<Department> getListDepartment() {
        List<Department> list = new ArrayList<>();
        Connection con = null;
        DBContext db = new DBContext();
        con = db.getConnection();
        Statement sm = null;
        try {
            sm = con.createStatement();
            ResultSet rs = sm.executeQuery("SELECT * FROM department");
            if (!rs.next()) {
                System.out.println("Data empty!");
            } else {
                do {
                    Department department = new Department(
                            rs.getInt("id"),
                            rs.getString("department_name"),
                            rs.getString("address")
                    );
                    list.add(department);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
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

    public void removeEmployeeByDeptID() {
        String sql = "DELETE from department WHERE id = ?";
        try {
            Connection con = null;
            DBContext db = new DBContext();
            con = db.getConnection();

            PreparedStatement sm = con.prepareStatement(sql);
            sm.setInt(1, department.getDepartmentId());
            sm.executeUpdate();
            System.out.println("Delete department successfully!");

        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public List<Department> getListDepartmentById(List<Department> list, int deptID) {
        List<Department> getListDept = new ArrayList<>();
        for (Department department : list) {
            if (deptID == department.getDepartmentId()) {
                getListDept.add(department);
            }
        }
        return getListDept;
    }


}
