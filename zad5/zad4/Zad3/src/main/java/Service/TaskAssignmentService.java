package Service;

import Interfaces.AssignmentConfig;
import Interfaces.AvailabilityCalendar;
import Interfaces.ResourceAllocationSystem;
import Interfaces.SkillsRepository;
import Models.Employee;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class TaskAssignmentService {
    private AvailabilityCalendar availabilityCalendar;
    private SkillsRepository skillsRepository;
    private ResourceAllocationSystem resourceAllocationSystem;
    private AssignmentConfig assignmentConfig;

    public TaskAssignmentService(AvailabilityCalendar availabilityCalendar, SkillsRepository skillsRepository, ResourceAllocationSystem resourceAllocationSystem, AssignmentConfig assignmentConfig) {
        this.availabilityCalendar = availabilityCalendar;
        this.skillsRepository = skillsRepository;
        this.resourceAllocationSystem = resourceAllocationSystem;
        this.assignmentConfig = assignmentConfig;
    }

    public Employee assignTask(String taskID, Set<String> requiredSkills, Duration duration ){
       Duration actualDuration;
       if(duration!=null){
           actualDuration = duration;
       }else {
           actualDuration=assignmentConfig.getDefaultDuration();
       }

        List<Employee> availableEmployees = availabilityCalendar.findAvailableEmployees(actualDuration);

       for (Employee employee : availableEmployees) {
           Set<String> skills =skillsRepository.getSkillsFor(employee);
           if (skills.containsAll(requiredSkills)) {
               resourceAllocationSystem.allocate(taskID,employee,actualDuration);
               return employee;
           }
       }
       return null;
    }

}
