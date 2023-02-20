package cz.cvut.fel.constructa.enums;

public enum Role {
    ADMIN("Admin"),
    MANAGER("Manager"),
    REPORTER("Reporter"),
    WAREHOUSE_MANAGER("Warehouse_manager"),
    MECHANICAL_ENGINEER("Mechanical_engineer"),
    CONSTRUCTION_MANAGER("Construction_manager"),
    DRIVER("Driver"),
    EMPLOYEE("Employee"),
    EXTERNALIST("Externalist"),
    FIRED("Fired");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
