package Atrapy.Stub;

import Interfaces.AvailabilityCalendar;
import Models.Employee;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AvailabilityCalendarStub implements AvailabilityCalendar {
    private List<Employee> availableEmployees=new ArrayList<Employee>();

    public AvailabilityCalendarStub withAvailableEmployees(Employee employee) {
        availableEmployees.add(employee);
        return this;
    }

    @Override
    public List<Employee> findAvailableEmployees(Duration duration) {
        return new ArrayList<>(availableEmployees);
    }
}
