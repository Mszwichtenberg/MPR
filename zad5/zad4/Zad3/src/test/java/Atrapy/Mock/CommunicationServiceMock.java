package Atrapy.Mock;

import Interfaces.CommunicationService;
import Models.Employee;

public class CommunicationServiceMock implements CommunicationService {
    private int expectedCount=0;
    private int actualCount=0;
    public  CommunicationServiceMock expectedCount(int expectedCount) {
        this.expectedCount = expectedCount;
        return this;
    }

    @Override
    public void sendReminder(Employee employee, String message) {
        actualCount++;
    }
    public void verify(){
        if(actualCount!=expectedCount){
            throw new AssertionError("Expected "+expectedCount+" but actual "+actualCount);
        }
    }
}
