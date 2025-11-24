package Models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Employee {
    private String name;
    private String email;
    private String companyName;
    private Department department;
    private int salary;
    private LocalDate hireDate;
    private List<Integer> ratings=new ArrayList<>();


    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public List<Integer> getRatings() {
        return ratings;
    }

    public void setRatings(List<Integer> ratings) {
        this.ratings = ratings;
    }
    public void addRating(int rating){
        this.ratings.add(rating);
    }

    public Employee(String name, String email, String companyName, Department department, LocalDate hireDate) {
        this.name = name;
        this.email = email;
        this.companyName = companyName;
        this.department = department;
        salary = department.getPrice();
        this.hireDate = hireDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int newSalary) {
        this.salary = newSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        companyName = companyName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee Diffemployee = (Employee) o;
        return email.equals(Diffemployee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return ("Name:" + name + " " + "email:" + email + " " + "CompanyName:" + companyName + " " + "department:" + department + " " + "Salary:" + salary);
    }


}
