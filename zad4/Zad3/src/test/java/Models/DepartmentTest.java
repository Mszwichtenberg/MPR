package Models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DepartmentTest {
    @Test
    void presidentShouldHaveCorrectPriceHierarchy() {
        assertEquals(25000,Department.president.getPrice());
        assertEquals(1,Department.president.getHierarchyLvl());
    }
    @Test
    void managerShouldHaveCorrectPriceHierarchy() {
        assertEquals(12000,Department.manager.getPrice());
        assertEquals(3,Department.manager.getHierarchyLvl());
    }
    @Test
    void vicePresidentShouldHaveCorrectPriceHierarchy() {
        assertEquals(18000,Department.vicePresident.getPrice());
        assertEquals(2,Department.vicePresident.getHierarchyLvl());
    }
    @Test
    void traineeShouldHaveCorrectPriceHierarchy() {
        assertEquals(3000,Department.trainee.getPrice());
        assertEquals(5,Department.trainee.getHierarchyLvl());
    }

    @Test
    void programmerShouldHaveCorrectPriceHierarchy() {
        assertEquals(8000,Department.programmer.getPrice());
        assertEquals(4,Department.programmer.getHierarchyLvl());
    }
}
