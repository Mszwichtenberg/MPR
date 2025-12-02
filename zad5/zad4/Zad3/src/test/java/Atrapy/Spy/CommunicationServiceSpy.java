package Atrapy.Spy;

import Interfaces.CommunicationService;
import Models.Employee;

import java.util.ArrayList;
import java.util.List;

public class CommunicationServiceSpy implements CommunicationService {
    public static class SentReminder {
        public Employee employee;
        public String message;

        public SentReminder(Employee employee, String message) {
            this.employee = employee;
            this.message = message;
        }
    }
    private List<SentReminder> sentReminders=new ArrayList<>();
    @Override
    public void sendReminder(Employee employee, String message) {
        sentReminders.add(new SentReminder(employee,message));
    }
    public List<SentReminder> getSentReminders() {
        return new ArrayList<>(sentReminders);
    }
    public int getSentRemindersSize() {
        return sentReminders.size();
    }
}
