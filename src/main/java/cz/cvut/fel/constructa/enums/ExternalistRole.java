package cz.cvut.fel.constructa.enums;

/**
 * The enum Externalist role.
 */
public enum ExternalistRole {
    /**
     * Client externalist role.
     */
    CLIENT("Client"),
    /**
     * General planner externalist role.
     */
    GENERAL_PLANNER("General_planner"),
    /**
     * Responsible planner externalist role.
     */
    RESPONSIBLE_PLANNER("Responsible_planner");

    /**
     * The Externalist role.
     */
    private final String externalistRole;

    /**
     * Instantiates a new Externalist role.
     *
     * @param externalistRole the externalist role
     */
    ExternalistRole(String externalistRole) {
        this.externalistRole = externalistRole;
    }

    /**
     * Gets externalist role.
     *
     * @return the externalist role
     */
    public String getExternalistRole() {
        return externalistRole;
    }
}
