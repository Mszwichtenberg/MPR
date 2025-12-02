package Interfaces;

import Models.Employee;

public interface CommunicationService {
    void sendReminder(Employee employee, String message);
}
