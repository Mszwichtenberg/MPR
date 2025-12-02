package Service;

import Models.Department;
import Models.Employee;

public class PromotionService {

    public void promote(Employee employee, Department department) {
        Department currentDepartment = employee.getDepartment();
        if(department.getHierarchyLvl()<currentDepartment.getHierarchyLvl()){
            employee.setDepartment(department);
            employee.setSalary(department.getPrice());
        }
        else {
            throw new IllegalArgumentException("Incorrect promotion department");
        }
    }

    public void applyRaise(Employee employee, double percentage) {
        int currentSalary = employee.getSalary();

        double raised=currentSalary*(1+percentage/100);

        int maxSalary = employee.getDepartment().getPrice()*2;
        int newSalary= (int) raised;

        if(newSalary>maxSalary){
            throw new IllegalArgumentException("Incorrect promotion percentage");
        }
        employee.setSalary(newSalary);
    }

}
