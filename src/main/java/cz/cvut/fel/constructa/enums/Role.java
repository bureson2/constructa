package cz.cvut.fel.constructa.enums;

public enum Role {

//    USER,
//    ADMIN

    ROLE_ADMIN("Admin"),
    ROLE_MANAGER("Manager"),
    ROLE_REPORTER("Reporter"),
    ROLE_WAREHOUSE_MANAGER("Warehouse_manager"),
    ROLE_MECHANICAL_ENGINEER("Mechanical_engineer"),
    ROLE_CONSTRUCTION_MANAGER("Construction_manager"),
    ROLE_DRIVER("Driver"),
    ROLE_WORKER("Worker"),
    ROLE_EMPLOYEE("Employee"),
    ROLE_EXTERNALIST("Externalist"),
    ROLE_FIRED("Fired");

    private String role;
    Role(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
