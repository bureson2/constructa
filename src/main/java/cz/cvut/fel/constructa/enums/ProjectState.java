package cz.cvut.fel.constructa.enums;

/**
 * The enum Project state.
 */
public enum ProjectState {
    /**
     * Finished project state.
     */
    FINISHED("Hotovo"),
    /**
     * In preparation project state.
     */
    IN_PREPARATION("Příprava"),
    /**
     * In realization project state.
     */
    IN_REALIZATION("Realizace");

    /**
     * The Project state.
     */
    private final String projectState;

    /**
     * Instantiates a new Project state.
     *
     * @param projectState the project state
     */
    ProjectState(String projectState) {
        this.projectState = projectState;
    }

    /**
     * Gets project state.
     *
     * @return the project state
     */
    public String getProjectState() {
        return projectState;
    }
}
