package cz.cvut.fel.constructa.enums;

public enum Role {

    ROLE_ADMIN("Administrátor"),
    ROLE_MANAGER("Manager"),
    ROLE_REPORTER("Referent"),
    ROLE_WAREHOUSE_MANAGER("Vedoucí skladu"),
    ROLE_MECHANICAL_ENGINEER("Strojní technik"),
    ROLE_CONSTRUCTION_MANAGER("Stavbyvedoucí"),
    ROLE_DRIVER("Řidič"),
    ROLE_WORKER("Zedník"),
    ROLE_EMPLOYEE("Zaměstnanec"),
    ROLE_EXTERNALIST("Externalista"),
    ROLE_FIRED("Vyhozen");

    private String role;
    Role(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }
}
