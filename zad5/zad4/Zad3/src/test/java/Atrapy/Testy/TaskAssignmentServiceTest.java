package Atrapy.Testy;

import Atrapy.Fake.InMemorySkillsRepositoryFake;
import Atrapy.Mock.ResourceAllocationMock;
import Atrapy.Spy.ResourceAllocationSpy;
import Atrapy.Stub.AvailabilityCalendarStub;
import Atrapy.fix.AssignmentConfigFixed;
import Interfaces.AssignmentConfig;
import Models.Department;
import Models.Employee;
import Service.TaskAssignmentService;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
public class TaskAssignmentServiceTest {
    @Test
    void assignTask_shouldAssignFirstAvailableEmployeeWithRequiredSkills(){
        Employee employee1=new Employee("Jan","jan@adsasd.dsa","TechCorp", Department.programmer, LocalDate.of(2020,12,2));
        Employee employee2=new Employee("Jan","jan@adsasd.dsa","TechCorp", Department.programmer, LocalDate.of(2021,12,2));
        AvailabilityCalendarStub calendarStub=new AvailabilityCalendarStub().withAvailableEmployees(employee1).withAvailableEmployees(employee2);
        InMemorySkillsRepositoryFake skillsFake=new InMemorySkillsRepositoryFake().withSkills(employee1, Set.of("Java")).withSkills(employee2, Set.of("Java","C++"));
        ResourceAllocationSpy allocationSpy = new ResourceAllocationSpy();
        AssignmentConfigFixed config=new AssignmentConfigFixed(Duration.ofHours(4));
        TaskAssignmentService service = new TaskAssignmentService(
                calendarStub,
                skillsFake,
                allocationSpy,
                config
        );
        Employee assigned=service.assignTask("Task-1",Set.of("Java","C++"),null);
        assertThat(assigned).isEqualTo(employee2);
        assertThat(allocationSpy.getAllocationCount()).isEqualTo(1);
        assertThat(allocationSpy.wasAllocated("Task-1",employee2)).isTrue();
    }
    @Test
    void assignTask_shouldReturnNullWhenNoEmployeeHasRequiredSkills() {
        Employee e1 = new Employee("Jan", "jan@corp.com", "TechCorp", Department.programmer, LocalDate.of(2020, 1, 1));

        AvailabilityCalendarStub calendarStub = new AvailabilityCalendarStub()
                .withAvailableEmployees(e1);

        InMemorySkillsRepositoryFake skillsFake = new InMemorySkillsRepositoryFake()
                .withSkills(e1, Set.of("Java"));

        ResourceAllocationSpy allocationSpy = new ResourceAllocationSpy();
        AssignmentConfigFixed config = new AssignmentConfigFixed(Duration.ofHours(2));

        TaskAssignmentService service = new TaskAssignmentService(
                calendarStub,
                skillsFake,
                allocationSpy,
                config
        );
        Employee assigned = service.assignTask("Task-2", Set.of("Python"), null);
        assertThat(assigned).isNull();
        assertThat(allocationSpy.getAllocationCount()).isEqualTo(0);
}
    @Test
    void assignTask_shouldUseMockToVerifyBehavior() {
        Employee e1 = new Employee("Jan", "jan@corp.com", "TechCorp", Department.programmer, LocalDate.of(2020, 1, 1));

        AvailabilityCalendarStub calendarStub = new AvailabilityCalendarStub()
                .withAvailableEmployees(e1);

        InMemorySkillsRepositoryFake skillsFake = new InMemorySkillsRepositoryFake()
                .withSkills(e1, Set.of("Java"));

        AssignmentConfigFixed config = new AssignmentConfigFixed(Duration.ofHours(3));

        ResourceAllocationMock allocationMock = new ResourceAllocationMock()
                .expect("TASK-3", e1, Duration.ofHours(3));

        TaskAssignmentService service = new TaskAssignmentService(
                calendarStub,
                skillsFake,
                allocationMock,
                config
        );

        Employee assigned = service.assignTask("TASK-3", Set.of("Java"), null);

        assertThat(assigned).isEqualTo(e1);
        allocationMock.verify();
    }
    }
