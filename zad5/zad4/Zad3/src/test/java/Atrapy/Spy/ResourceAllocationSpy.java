package Atrapy.Spy;

import Interfaces.ResourceAllocationSystem;
import Models.Employee;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ResourceAllocationSpy implements ResourceAllocationSystem {
    public static class AllocationRecord{
        public String taskId;
        public Employee employee;
        public Duration duration;

        public AllocationRecord(String taskId,Employee employee,Duration duration){
            this.taskId = taskId;
            this.employee = employee;
            this.duration = duration;
        }
    }
    private List<AllocationRecord> allocationRecords = new ArrayList<>();

    @Override
    public void allocate(String taskID, Employee employee, Duration duration) {
        allocationRecords.add(new AllocationRecord(taskID,employee,duration));
    }
    public List<AllocationRecord> getAllocationRecords(){
        return new ArrayList<>(allocationRecords);
    }
    public boolean wasAllocated(String taskID, Employee employee){
        return allocationRecords.stream()
                .anyMatch(record -> record.taskId.equals(taskID) && record.employee.equals(employee));
    }
    public int getAllocationCount(){
        return allocationRecords.size();
    }
}
