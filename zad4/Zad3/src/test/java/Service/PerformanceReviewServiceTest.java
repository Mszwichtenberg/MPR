package Service;

import Models.Department;
import Models.Employee;
import org.hamcrest.Matchers;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasItem;

public class PerformanceReviewServiceTest {
    private final PerformanceReviewService service = new PerformanceReviewService();

    public static Stream<Arguments> ratingSequences() {
        return Stream.of(Arguments.of(List.of(5), 5.0), Arguments.of(List.of(4, 4, 4), 4.0, Arguments.of(List.of(3, 3, 3), 3.0)));
    }

    @ParameterizedTest
    @MethodSource("ratingSequences")
    void getAverageRating_shouldReturnCorrectValues(List<Integer> ratings, double averageRating) {
        Employee employee = new Employee("Jan", "janek@dassa.com", "TechCorp", Department.programmer, LocalDate.of(2025, 4, 11));
        ratings.forEach(rating -> service.addRating(employee, rating));
        double average = service.getAverageRating(employee);
        assertThat(average).isEqualTo(averageRating);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5})
    void addRating_shouldAcceptValidRatings(int rating) {
        Employee employee = new Employee("Jan", "janek@dassa.com", "TechCorp", Department.programmer, LocalDate.of(2025, 4, 11));
        service.addRating(employee, rating);
        assertThat(service.getRatings(employee).contains(rating));

        assertThat(service.getRatings(employee), hasItem(rating));

    }

    @ParameterizedTest
    @ValueSource(ints = {0, 6, -1})
    void addRating_shouldNotAcceptInvalidRatings(int rating) {
        Employee employee = new Employee("Jan", "janek@dassa.com", "TechCorp", Department.programmer, LocalDate.of(2025, 4, 11));
        assertThatThrownBy(() -> service.addRating(employee, rating)).isInstanceOf(IllegalArgumentException.class);
    }


}
