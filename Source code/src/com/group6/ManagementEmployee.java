package com.group6;

import java.sql.*;

public class ManagementEmployee {
    private Employee employee;
    private Admin admin;

    public ManagementEmployee(Admin admin) {
        this.admin = admin;
    }

    public ManagementEmployee(Employee employee) {
        this.employee = employee;
    }

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

}
