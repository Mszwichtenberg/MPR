package Service;

import Models.Department;
import Models.Employee;
import Models.ProjectTeam;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;

public class ProjectTeamServiceTest {
    static Stream<List<Employee>> validTeams() {
        Employee e1 = new Employee("A", "a@example.com", "TechCorp", Department.programmer, LocalDate.of(2020, 1, 1));
        Employee e2 = new Employee("B", "b@example.com", "TechCorp", Department.manager, LocalDate.of(2020, 1, 1));
        Employee e3 = new Employee("C", "c@example.com", "TechCorp", Department.trainee, LocalDate.of(2020, 1, 1));

        return Stream.of(List.of(e1, e2), List.of(e1, e2, e3)
        );
    }
    private final ProjectTeamService service = new ProjectTeamService();
    @ParameterizedTest
    @MethodSource("validTeams")
    void createTeam_shouldCreateTeamForValidConfigs(List<Employee> members){
        ProjectTeam team=service.createTeam("Team A", members);

        assertThat(team.getMembers()).hasSize(members.size()).containsExactlyInAnyOrderElementsOf(members);
    }
    static Stream<List<Employee>> invalidTeams() {
        Employee e1 = new Employee("A", "a@example.com", "TechCorp", Department.programmer, LocalDate.of(2020, 1, 1));
        Employee e2 = new Employee("B", "b@example.com", "TechCorp", Department.programmer, LocalDate.of(2020, 1, 1));

        return Stream.of(List.of(e1), List.of(e1, e2));
    }

    @ParameterizedTest
    @MethodSource("invalidTeams")
    void createTeam_shouldNotCreateTeamForInvalidConfigs(List<Employee> members){
        assertThatThrownBy(() -> service.createTeam("Team B", members)).isInstanceOf(IllegalArgumentException.class);
    }

}
