import Models.Department;
import Models.Employee;
import Service.Corp;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Corp corp = new Corp();
        corp.addEmployee(new Employee("Kowalski", "kowal@gmail.com", "Google", Department.programmer, LocalDate.of(2025,1,1)));
        corp.addEmployee(new Employee("Nowak", "nowak@gmail.com", "Apple", Department.manager,LocalDate.of(2025,1,1)));
        corp.addEmployee(new Employee("Adamski", "adam@gmail.com", "Microsoft", Department.trainee,LocalDate.of(2025,1,1)));
        corp.addEmployee(new Employee("Wiśniewski", "wisnia@gmail.com", "Amazon", Department.vicePresident,LocalDate.of(2025,1,1)));
        corp.addEmployee(new Employee("Zieliński", "zielinski@gmail.com", "Netflix", Department.manager,LocalDate.of(2025,1,1)));
        corp.addEmployee(new Employee("Lewandowski", "lewy@gmail.com", "Spotify", Department.president,LocalDate.of(2025,1,1)));
        corp.addEmployee(new Employee("Lewak", "lewak@gmail.com", "Facebook", Department.president,LocalDate.of(2025,1,1)));

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
