package Service;

import Models.Employee;

import java.util.Comparator;

public class EmployeeNameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        if (e1 == null && e2 == null) return 0;
        if (e1 == null) return 1;
        if (e2 == null) return -1;

        if (e1.getName() == null && e2.getName() == null) return 0;
        if (e1.getName() == null) return 1;
        if (e2.getName() == null) return -1;
        return e1.getName().compareToIgnoreCase(e2.getName());
    }
}
