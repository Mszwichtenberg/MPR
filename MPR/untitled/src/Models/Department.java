package Models;

public enum Department {
    president(25000,1),
    manager(12000,3),
    vicePresident(18000,2),
    programmer(8000,4),
    trainee(3000,5)

    ;
    private int hierarchyLvl;
    private int price;

    private Department(int hierarchy, int price) {
        this.hierarchyLvl = hierarchy;
        this.price = price;
    }

    public int getHierarchyLvl() {
        return hierarchyLvl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
