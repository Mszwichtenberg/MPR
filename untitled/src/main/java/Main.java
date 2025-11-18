import exception.ApiException;
import exception.InvalidDataException;
import model.CompanyStatistics;
import model.ImportSummary;
import model.Employee;
import service.ApiService;
import service.EmployeeService;
import service.ImportService;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        ImportService importService = new ImportService(employeeService);
        ApiService apiService = new ApiService();

        try {
            ImportSummary summary = importService.importFromCsv("employees.csv");
            System.out.println(summary);

            List<Employee> apiEmployees = apiService.fetchEmployeesFromApi();
            for (Employee e : apiEmployees) {
                employeeService.addEmployee(e);
            }



            System.out.println("Inconsistent salaries:");
            List<Employee> inconsistent = employeeService.validateSalaryConsistency();
            for (Employee e : inconsistent) {
                System.out.println(e);
            }

            System.out.println("Company statistics:");
            Map<String, CompanyStatistics> stats = employeeService.getCompanyStatistics();
            for (String company : stats.keySet()) {
                System.out.println(company + " -> " + stats.get(company));
            }

        } catch (InvalidDataException e) {
            System.err.println("CSV error: " + e.getMessage());
        } catch (ApiException e) {
            System.err.println("API error: " + e.getMessage());
        }
    }
}