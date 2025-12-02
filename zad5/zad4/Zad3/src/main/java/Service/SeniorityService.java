package Service;

import Models.Employee;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SeniorityService {
    public int calculateYearsOfService(Employee employee, LocalDate date) {
        if (employee.getHireDate() == null) {
            return 0;
        }
        return Period.between(employee.getHireDate(), date).getYears();
    }

    public List<Employee> filterByYearsOfServiceRange(List<Employee> employees, int minYears, int maxYears, LocalDate asOfDate) {
        return employees.stream().filter(employee -> {
            int years = calculateYearsOfService(employee, asOfDate);
            return years >= minYears && years <= maxYears;
        }).collect(Collectors.toList());
    }

    public List<Employee> findJubileeEmployees(List<Employee> employees, LocalDate asOfDate) {
        return employees.stream().filter(employee -> {
            int years = calculateYearsOfService(employee, asOfDate);
            return years > 0 && years % 5 == 0;
        }).collect(Collectors.toList());

    }
}
