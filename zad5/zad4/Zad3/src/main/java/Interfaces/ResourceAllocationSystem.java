package Interfaces;

import Models.Employee;

import java.time.Duration;

public interface ResourceAllocationSystem {
    void allocate(String taskID, Employee employee, Duration duration);
}
