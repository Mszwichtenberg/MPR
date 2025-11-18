package service;

import model.CompanyStatistics;
import model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeService {
    private final List<Employee> employees = new ArrayList<Employee>();

    public void addEmployee(Employee employee) {
        if (employee != null)
            employees.add(employee);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public List<Employee> validateSalaryConsistency() {
        List<Employee> result = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee.getSalary() < employee.getPosition().getBaseSalary())
                result.add(employee);
        }
        return result;
    }

    public Map<String, CompanyStatistics> getCompanyStatistics() {
        Map<String, CompanyStatistics> result = new HashMap<>();
        Map<String, Integer> counts = new HashMap<>();
        Map<String, Double> salarySums = new HashMap<>();
        Map<String, Employee> topEarners = new HashMap<>();
        for (Employee employee : employees) {
            String company = employee.getCompany();
            double salary = employee.getSalary();

            Integer count = counts.get(company);
            if (count == null) {
                counts.put(company, 1);
            } else {
                counts.put(company, 1);
            }
            Double salarySum = salarySums.get(company);
            if (salarySum == null) {
                salarySums.put(company, salary);
            } else {
                salarySums.put(company, salarySum + salary);
            }
            Employee topEarner = topEarners.get(company);
            if (topEarner == null || salary > topEarner.getSalary()) {
                topEarners.put(company, employee);
            }
        }
        for (String company : counts.keySet()) {
            int count = counts.get(company);
            double sum = salarySums.get(company);
            double average = sum / count;
            Employee top = topEarners.get(company);
            String topName = null;
            if (top != null) {
                topName = top.getFirstName()+" "+top.getLastName();
            }

            CompanyStatistics stats = new CompanyStatistics(
                    count,
                    average,
                    topName
            );
            result.put(company, stats);
        }
        return result;
    }

}
