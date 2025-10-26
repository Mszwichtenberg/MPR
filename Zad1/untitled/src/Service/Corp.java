package Service;

import Models.Department;
import Models.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Corp {
    private List<Employee> employees = new ArrayList<Employee>();

    public List<Employee> getAllEmployees() {
        return employees;
    }

    public List<Employee> getAllEmployeesSortedByName() {
        List<Employee> sorted = new ArrayList<>(employees);
        Collections.sort(sorted, new EmployeeNameComparator());
        return sorted;
    }

    public void addEmployee(Employee newEmployee) {

        try {
            if (newEmployee == null) {
                throw new NullPointerException("Employee cannot be null");
            }
            for (Employee employee : employees) {
                if (employee.getName().equals(newEmployee.getName())) {
                    throw new IllegalArgumentException("Employee with that email already exists");
                }
            }
            employees.add(newEmployee);


        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());

        }
    }

    public List<Employee> findByCompanyName(String companyName) {
        List<Employee> CompanyEmployees = new ArrayList<>();
        try {
            for (Employee employee : employees) {
                if (employee.getCompanyName().equals(companyName)) {
                    CompanyEmployees.add(employee);
                }
            }
            return CompanyEmployees;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Employee with that company name doesn't exist");
        }
    }

    public HashMap<Department, Integer> findByDepartment(Department department) {
        HashMap<Department, Integer> map = new HashMap<>();
        int peopleCount = 0;
        for (Employee employee : employees) {
            if (employee.getDepartment().equals(department)) {
                peopleCount++;
            }
        }
        Integer people = peopleCount;

        if (people == 0) {
            throw new IllegalArgumentException("There is no employee with that department or that department doesn't exist");
        }
        map.put(department, people);
        return map;
    }

    public HashMap<Department, Integer> countEmployeesByDepartment() {
        HashMap<Department, Integer> map = new HashMap<>();
        for (Employee employee : employees) {
            if (map.containsKey(employee.getDepartment())) {
                map.put(employee.getDepartment(), map.get(employee.getDepartment()) + 1);
            } else {
                map.put(employee.getDepartment(), 1);
            }
        }
        return map;
    }

    public double averageSalary() {
        double salarySum = 0;
        double emp = employees.size();
        for (Employee employee : employees) {
            salarySum += employee.getSalary();
        }
        return salarySum / emp;
    }

    public List<Employee> findEmployeeWithTheBiggestSalary() {
        int biggestSalary = 0;
        List<Employee> biggestEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSalary() > biggestSalary) {
                biggestSalary = employee.getSalary();
            }
        }
        for (Employee employee : employees) {
            if (employee.getSalary() == biggestSalary) {
                biggestEmployees.add(employee);
            }
        }
        return biggestEmployees;
    }


}
