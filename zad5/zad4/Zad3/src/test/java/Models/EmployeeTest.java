package Models;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    void constructor_shouldSetSalaryFromDepartmentPrice() {
        Department department = Department.programmer;

        Employee employee = new Employee("Jan", "jan@example.com", "TechCorp", department,LocalDate.of(2025,1,1));

        assertEquals(department.getPrice(), employee.getSalary());
    }
    @Test
    void equals_shouldReturnTrueForSameEmail() {

        Employee e1 = new Employee("Jan", "jan@example.com", "TechCorp", Department.programmer,LocalDate.of(2025,1,1));
        Employee e2 = new Employee("Adam", "jan@example.com", "InnaFirma", Department.manager,LocalDate.of(2025,1,1));

        boolean result = e1.equals(e2);

        assertTrue(result);
    }
    @Test
    void equals_shouldReturnFalseForDifferentEmail() {

        Employee e1 = new Employee("Jan", "jan@example.com", "TechCorp", Department.programmer,LocalDate.of(2025,1,1));
        Employee e2 = new Employee("Adam", "Adam@example.com", "InnaFirma", Department.manager,LocalDate.of(2025,1,1));

        boolean result = e1.equals(e2);
        assertFalse(result);

    }

    @Test
    void equals_shouldReturnFalseForNull() {
        Employee e1=new Employee("Jan","jan@example.com", "TechCorp", Department.programmer,LocalDate.of(2025,1,1));

        boolean result = e1.equals(null);
        assertFalse(result);
    }

    @Test
    void equals_shouldReturnFalseForDifferentClass() {
        Employee employee = new Employee("Jan", "jan@example.com", "TechCorp", Department.programmer, LocalDate.of(2025,1,1));
        String otherObject = "not an employee";

        boolean result = employee.equals(otherObject);
        assertFalse(result);
    }


}