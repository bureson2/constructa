package cz.cvut.fel.constructa.enums;

public enum ExternalistRole {
    CLIENT("Client"),
    GENERAL_PLANNER("General_planner"),
    RESPONSIBLE_PLANNER("Responsible_planner");

    private String externalistRole;
    ExternalistRole(String externalistRole) {
        this.externalistRole = externalistRole;
    }
    public String getExternalistRole() {
        return externalistRole;
    }
}
