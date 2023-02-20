package cz.cvut.fel.constructa.enums;

public enum ProjectState {
    FINISHED("Finished"),
    IN_PREPARATION("In_preparation"),
    IN_REALIZATION("In_realization");

    private String projectState;
    ProjectState(String projectState) {
        this.projectState = projectState;
    }
    public String getProjectState() {
        return projectState;
    }
}
