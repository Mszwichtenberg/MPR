package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProjectTeam {
    private String name;
    private List<Employee> members = new ArrayList<>();

    public ProjectTeam(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Employee> getMembers() {
        return members;
    }

    public void addMember(Employee member) {
        if (!members.contains(member)) {
            members.add(member);
        }
    }
    public void removeMember(Employee member) {
        members.remove(member);
    }
    @Override
    public String toString() {
        return "ProjectTeam{" + "name='" + name + "\n" + ", members=" + members + '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectTeam that)) return false;
        return Objects.equals(name, that.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
