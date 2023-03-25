package cz.cvut.fel.constructa.enums;

public enum ProjectState {
    FINISHED("Hotovo"),
    IN_PREPARATION("Příprava"),
    IN_REALIZATION("Realizace");

    private String projectState;
    ProjectState(String projectState) {
        this.projectState = projectState;
    }
    public String getProjectState() {
        return projectState;
    }
}
