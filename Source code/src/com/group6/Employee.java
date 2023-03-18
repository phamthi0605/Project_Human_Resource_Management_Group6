package com.group6;

import java.util.Date;

public class Employee {
    private int employee_id;
    private String fullName;
    private String position;
    private String email;
    private float salary;
    private float person_Income_Tax;
    private String hire_date;
    private int department_id;
    private String is_manager;

    public Employee() {

    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public float getPerson_Income_Tax() {
        return person_Income_Tax;
    }

    public void setPerson_Income_Tax(float salary) {
        if (salary <= 5000000) {
            salary = (float) (salary * 0.05);
        } else if (salary >= 5000000 && salary <= 10000000) {
            salary = (float) (salary * 0.1);
        } else if (salary >= 10000000 && salary <= 18000000) {
            salary = (float) (salary * 0.15);
        } else {
            salary = (float) (salary * 0.2);
        }
        this.person_Income_Tax = salary;
    }


    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getIs_manager() {
        return is_manager;
    }

    public void setIs_manager(String is_manager) {
        this.is_manager = is_manager;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", fullName='" + fullName + '\'' +
                ", position='" + position + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", person_Income_Tax=" + person_Income_Tax +
                ", hire_date=" + hire_date +
                ", department_id=" + department_id +
                ", is_manager='" + is_manager + '\'' +
                '}';
    }
}
