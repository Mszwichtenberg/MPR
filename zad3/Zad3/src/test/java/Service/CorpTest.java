package Service;

import Models.Department;
import Models.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;

public class CorpTest {
    private Corp corp;
    @BeforeEach
    void setUp() {
        corp = new Corp();
        corp.addEmployee(new Employee("Kowalski", "kowal@gmail.com", "Google", Department.programmer));
        corp.addEmployee(new Employee("Nowak", "nowak@gmail.com", "Apple", Department.manager));
        corp.addEmployee(new Employee("Adamski", "adam@gmail.com", "Microsoft", Department.trainee));
        corp.addEmployee(new Employee("Wiśniewski", "wisnia@gmail.com", "Amazon", Department.vicePresident));
        corp.addEmployee(new Employee("Zielinski", "zielinski@gmail.com", "Netflix", Department.manager));
        corp.addEmployee(new Employee("Lewandowski", "lewy@gmail.com", "Spotify", Department.president));
        corp.addEmployee(new Employee("Lewak", "lewak@gmail.com", "Facebook", Department.president));
    }

    @Test
    void getAllEmployeesShouldReturnAllAddedEmployees() {
        List<Employee> employees = corp.getAllEmployees();
        assertEquals(7,employees.size());    }
    @Test
    void addEmployeeShouldAddNewEmployee() {
        Employee employee=new Employee("Polak","polak@polak.com","PolakCorp",Department.manager);

        corp.addEmployee(employee);
        assertTrue(corp.getAllEmployees().contains(employee));
    }

    @Test
    void addEmployeeShouldThrowExceptionWhenEmailAlreadyExists() {
        Employee duplicate = new Employee(
                "Kowal2",
                "kowal@gmail.com",
                "Microsoft",
                Department.manager
        );

        assertThrows(IllegalArgumentException.class, () -> corp.addEmployee(duplicate));
    }

    @Test
    void findByCompanyNameShouldReturnCorrectEmployee() {
        List<Employee> employees = corp.findByCompanyName("Spotify");

        assertEquals(1,employees.size());
        assertEquals("Spotify",employees.get(0).getCompanyName());
        assertEquals("Lewandowski",employees.get(0).getName());

    }

    @Test
    void findByCompanyNameShouldReturnEmptyListForNonExistingCompany() {
        List<Employee> result = corp.findByCompanyName("Firma");

        assertTrue(result.isEmpty(), "Lista powinna byc pusta dla nieistniejacej firmy");
    }

    @Test
    void getAllEmployeesSortedByNameShouldReturnCorrectOrderOfEmployees() {
        List<Employee> employees = corp.getAllEmployeesSortedByName();
        employees.get(0).equals("Adamski");
        employees.get(employees.size()-1).getName().equals("Zielinski");

    }
    @Test
    void findByDepartment_shouldReturnCorrectCountForExistingDepartment() {
        HashMap<Department, Integer> result = corp.findByDepartment(Department.manager);

        assertEquals(1, result.size());
        assertTrue(result.containsKey(Department.manager));
        assertEquals(2, result.get(Department.manager));
    }
    @Test
    void findByDepartmentShouldThrowExceptionWhenNoEmployeesInDepartment() {

        corp = new Corp();

        assertThrows(IllegalArgumentException.class,
                () -> corp.findByDepartment(Department.programmer));
    }

    @Test
    void countEmployeesByDepartment_shouldReturnCorrectCounts() {
        HashMap<Department, Integer> result = corp.countEmployeesByDepartment();

        assertEquals(5, result.size());

        assertEquals(2, result.get(Department.president));
        assertEquals(2, result.get(Department.manager));
        assertEquals(1, result.get(Department.programmer));
        assertEquals(1, result.get(Department.trainee));
        assertEquals(1, result.get(Department.vicePresident));
    }

    @Test
    void averageSalaryShouldReturnCorrectAverageForNonEmptyList() {
        double expected = 103000.0 / 7.0;

        double result = corp.averageSalary();

        assertEquals(expected, result, 0.0001);
    }
    @Test
    void averageSalaryShouldReturnNaNWhenNoEmployees() {
        Corp emptyCorp = new Corp();

        double result = emptyCorp.averageSalary();

        assertTrue(Double.isNaN(result));
    }

    @Test
    void findEmployeeWithTheBiggestSalaryShouldReturnAllEmployeesWithMaxSalary() {
        List<Employee> emplooye = corp.findEmployeeWithTheBiggestSalary();

        assertEquals(2, emplooye.size());

        List<String> names = emplooye.stream().map(Employee::getName).toList();
        assertTrue(names.contains("Lewandowski"));
        assertTrue(names.contains("Lewak"));
    }

    @Test
    void findEmployeeWithTheBiggestSalaryShouldReturnEmptyListWhenNoEmployees() {
        Corp emptyCorp = new Corp();

        List<Employee> employee = emptyCorp.findEmployeeWithTheBiggestSalary();

        assertTrue(employee.isEmpty());
    }





}

