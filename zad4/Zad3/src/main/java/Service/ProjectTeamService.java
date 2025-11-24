package Service;

import Models.Department;
import Models.Employee;
import Models.ProjectTeam;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class ProjectTeamService {
    private static final int MIN_TEAM_SIZE = 2;
    private static final int MAX_TEAM_SIZE = 10;

    public ProjectTeam createTeam(String name, Collection<Employee> members) {
        if (members.size() < MIN_TEAM_SIZE || members.size() > MAX_TEAM_SIZE) {
            throw new IllegalArgumentException("Invalid team size");
        }

        Set<Department> departments = new HashSet<>();
        members.forEach(e -> departments.add(e.getDepartment()));

        if (departments.size() < 2) {
            throw new IllegalArgumentException("Team cannot have less than 2 departments");
        }

        ProjectTeam team = new ProjectTeam(name);
        members.forEach(team::addMember);
        return team;
    }

    public void transferEmployee(ProjectTeam from, ProjectTeam to, Employee employee) {
        if (!from.getMembers().contains(employee)) {
            throw new IllegalArgumentException("Employee doesn't belong to this team");
        }
        if (to.getMembers().contains(employee)) {
            throw new IllegalArgumentException("Employee already belongs to this team");
        }

        from.removeMember(employee);
        to.addMember(employee);
    }
}

