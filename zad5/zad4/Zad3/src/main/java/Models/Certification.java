package Models;

import java.time.LocalDate;

public class Certification {
    private Employee employee;
    private String type;
    private LocalDate expiryDate;

    public Certification(Employee employee, String type, LocalDate expiryDate) {
        this.employee = employee;
        this.type = type;
        this.expiryDate = expiryDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public String getType() {
        return type;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}
