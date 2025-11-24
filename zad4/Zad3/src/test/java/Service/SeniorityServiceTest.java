package Service;
import Models.Department;
import Models.Employee;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
public class SeniorityServiceTest {
    private final SeniorityService service = new SeniorityService();

    @ParameterizedTest
    @CsvSource({"2014-01-01,11","2021-01-01,4","2024-01-01,1"})
    void calculateYearsOfService_shouldHandleVariousDates(String hireDateStr,int expectedYears){
        LocalDate hireDate=LocalDate.parse(hireDateStr);
        Employee employee=new Employee("Jan","jan@dsadas.das","TechCorp", Department.programmer,hireDate);
        int years=service.calculateYearsOfService(employee,LocalDate.of(2025,1,1));
        assertThat(years,is(expectedYears));

        assertThat(years,greaterThanOrEqualTo(0));
    }

    @ParameterizedTest
    @CsvSource({"5,15","10,20"})
    void filterByYearsOfServiceRange_shouldReturnEmloyeesWithinRange(int min,int max){
        Employee e1 = new Employee("A", "a@example.com", "TechCorp", Department.programmer, LocalDate.of(2015, 1, 1)); // 10 lat
        Employee e2 = new Employee("B", "b@example.com", "TechCorp", Department.manager, LocalDate.of(2020, 1, 1));   // 5 lat
        Employee e3 = new Employee("C", "c@example.com", "TechCorp", Department.trainee, LocalDate.of(2024, 1, 1));  // 1 rok
        List<Employee> result=service.filterByYearsOfServiceRange(List.of(e1,e2,e3),min,max,LocalDate.of(2025,1,1));

        assertThat(result).isNotEmpty();

        assertThat(result,hasSize(greaterThanOrEqualTo(1)));
    }

}
