package Atrapy.Fake;

import Interfaces.SkillsRepository;
import Models.Employee;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class InMemorySkillsRepositoryFake implements SkillsRepository {
    private Map<Employee,Set<String>> skillsByEmployee = new HashMap<>();

    public InMemorySkillsRepositoryFake withSkills(Employee employee, Set<String> skills) {
        skillsByEmployee.put(employee, new HashSet<>(skills));
        return this;
    }

    @Override
    public Set<String> getSkillsFor(Employee employee) {
        return skillsByEmployee.getOrDefault(employee,Set.of());
    }
}
