package Interfaces;

import Models.Employee;

import java.time.Duration;
import java.util.List;

public interface AvailabilityCalendar {
    List<Employee> findAvailableEmployees(Duration duration);
}
