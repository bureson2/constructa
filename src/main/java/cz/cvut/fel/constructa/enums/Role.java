package cz.cvut.fel.constructa.enums;

/**
 * The enum Role.
 */
public enum Role {

    /**
     * Role admin role.
     */
    ROLE_ADMIN("Administrátor"),
    /**
     * Role manager role.
     */
    ROLE_MANAGER("Manager"),
    /**
     * Role reporter role.
     */
    ROLE_REPORTER("Referent"),
    /**
     * Role warehouse manager role.
     */
    ROLE_WAREHOUSE_MANAGER("Vedoucí skladu"),
    /**
     * Role mechanical engineer role.
     */
    ROLE_MECHANICAL_ENGINEER("Strojní technik"),
    /**
     * Role construction manager role.
     */
    ROLE_CONSTRUCTION_MANAGER("Stavbyvedoucí"),
    /**
     * Role driver role.
     */
    ROLE_DRIVER("Řidič"),
    /**
     * Role worker role.
     */
    ROLE_WORKER("Zedník"),
    /**
     * Role employee role.
     */
    ROLE_EMPLOYEE("Zaměstnanec"),
    /**
     * Role externalist role.
     */
    ROLE_EXTERNALIST("Externalista"),
    /**
     * Role fired role.
     */
    ROLE_FIRED("Vyhozen");

    /**
     * The Role.
     */
    private String role;

    /**
     * Instantiates a new Role.
     *
     * @param role the role
     */
    Role(String role) {
        this.role = role;
    }

    /**
     * Gets role.
     *
     * @return the role
     */
    public String getRole() {
        return role;
    }
}
