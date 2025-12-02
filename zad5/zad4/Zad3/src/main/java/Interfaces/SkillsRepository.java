package Interfaces;

import Models.Employee;

import java.util.Set;

public interface SkillsRepository {
    Set<String> getSkillsFor(Employee employee);
}
