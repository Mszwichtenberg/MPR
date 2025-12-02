package Atrapy.Mock;

import Interfaces.ResourceAllocationSystem;
import Models.Employee;

import java.time.Duration;

public class ResourceAllocationMock implements ResourceAllocationSystem {

    private String expectedTaskId;
    private Employee expectedEmployee;
    private Duration expectedDuration;

    private boolean allocateCalled = false;

    public ResourceAllocationMock expect(String taskId, Employee employee, Duration duration) {
        this.expectedTaskId = taskId;
        this.expectedEmployee = employee;
        this.expectedDuration = duration;
        return this;
    }

    @Override
    public void allocate(String taskId, Employee employee, Duration duration) {
        this.allocateCalled = true;

        if (!taskId.equals(expectedTaskId)) {
            throw new AssertionError("Expected taskId: " + expectedTaskId + " but got: " + taskId);
        }
        if (!employee.equals(expectedEmployee)) {
            throw new AssertionError("Expected employee: " + expectedEmployee + " but got: " + employee);
        }
        if (!duration.equals(expectedDuration)) {
            throw new AssertionError("Expected duration: " + expectedDuration + " but got: " + duration);
        }
    }

    public void verify() {
        if (!allocateCalled) {
            throw new AssertionError("Expected allocate() to be called, but it wasn't");
        }
    }
}
