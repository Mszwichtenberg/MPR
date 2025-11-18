package model;

public enum Position {
    PROGRAMMER(8000),
    MANAGER(12000),
    HR(6000);

    private final double baseSalary;

    Position(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
}
