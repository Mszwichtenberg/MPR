package Service;

import Models.Department;
import Models.Employee;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PromotionServiceTest {

    private final PromotionService promotionService = new PromotionService();

    @ParameterizedTest
    @CsvSource({"trainee, programmer, 8000", "programmer, manager, 12000", "manager, vicePresident, 18000"})
    void promote_shouldChangeDepartmentAndSalaryForValidPromotion(Department from, Department to, int expectedSalary) {

        Employee employee = new Employee("Jan", "jan@example.com", "TechCorp", from,LocalDate.of(2025,1,1));

        promotionService.promote(employee, to);

        assertThat(employee.getDepartment()).isEqualTo(to);
        assertThat(employee.getSalary()).isEqualTo(expectedSalary);

    }
    @ParameterizedTest
    @CsvSource({
            "manager, programmer",
            "vicePresident, manager",
            "president, president"
    })
    void promote_shouldThrowExceptionForInvalidPromotion(Department from, Department to) {
        Employee employee = new Employee("Jan", "jan@example.com", "TechCorp", from,LocalDate.of(2025,1,1));


      Assertions.assertThatThrownBy(() -> promotionService.promote(employee, to)).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Incorrect promotion");
    }

    @ParameterizedTest
    @CsvSource({"0,8000","10,8800","25,10000"})
    void applyRaise_shouldIncreaseSalaryWithinLimit(double percentage, int expectedSalary) {
        Employee employee = new Employee("John","john@email.com", "TechCorp", Department.programmer, LocalDate.of(2025,1,1));
        promotionService.applyRaise(employee,percentage);
        assertThat(employee.getSalary()).isEqualTo(expectedSalary);

        assertThat(employee.getSalary(),allOf(greaterThanOrEqualTo(Department.programmer.getPrice()),lessThanOrEqualTo(Department.programmer.getPrice()*2)));

    }
    @ParameterizedTest
    @CsvSource({"150","250"})
    void applyRaise_shouldThrowExceptionWhenExceedsMax(double percentage) {
        Employee employee =new Employee("Janek","snieg@dsada.com", "TechCorp", Department.programmer,LocalDate.of(2025,1,1));
        assertThatThrownBy(() -> promotionService.applyRaise(employee,percentage)).isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Incorrect promotion percentage");
    }

    @ParameterizedTest
    @EnumSource(Department.class)
    void allDepartments_shouldHavePositiveBaseSalary(Department department) {
        assertThat(department.getPrice(),greaterThan(0));
    }



}
