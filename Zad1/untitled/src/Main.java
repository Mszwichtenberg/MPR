import Models.Department;
import Models.Employee;
import Service.Corp;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Corp corp = new Corp();
        corp.addEmployee(new Employee("Kowalski", "kowal@gmail.com", "Google", Department.programmer));
        corp.addEmployee(new Employee("Nowak", "nowak@gmail.com", "Apple", Department.manager));
        corp.addEmployee(new Employee("Adamski", "adam@gmail.com", "Microsoft", Department.trainee));
        corp.addEmployee(new Employee("Wiśniewski", "wisnia@gmail.com", "Amazon", Department.vicePresident));
        corp.addEmployee(new Employee("Zieliński", "zielinski@gmail.com", "Netflix", Department.manager));
        corp.addEmployee(new Employee("Lewandowski", "lewy@gmail.com", "Spotify", Department.president));
        corp.addEmployee(new Employee("Lewak", "lewak@gmail.com", "Facebook", Department.president));

        System.out.println("Wszyscy pracownicy:");

        for (Employee e : corp.getAllEmployees()) {
            System.out.println(e);
        }

        System.out.println("\nPosortowani pracownicy:");

        for (Employee e : corp.getAllEmployeesSortedByName()) {
            System.out.println(e);
        }
        System.out.println("\nTYlko ze spotify:");
        for (Employee e : corp.findByCompanyName("Spotify")) {
            System.out.println(e);
        }
        System.out.println("\nlista programistow:");
        System.out.println(corp.findByDepartment(Department.programmer));
        System.out.println("\nLista osob na danym stanowisku:\n" + corp.countEmployeesByDepartment());

        System.out.println("\nLista pracownikow z najwieksza pensja:");
        for (Employee e : corp.findEmployeeWithTheBiggestSalary()) {
            System.out.println(e);
        }
        ;


    }
}
