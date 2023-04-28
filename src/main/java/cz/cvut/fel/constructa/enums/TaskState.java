package cz.cvut.fel.constructa.enums;

/**
 * The enum Task state.
 */
public enum TaskState {
    /**
     * New task state.
     */
    NEW("Nový"),
    /**
     * In progress task state.
     */
    IN_PROGRESS("V řešení"),
    /**
     * Stopped task state.
     */
    STOPPED("Pozastaveno"),
    /**
     * Done task state.
     */
    DONE("Hotovo");

    /**
     * The Task state.
     */
    private String taskState;

    /**
     * Instantiates a new Task state.
     *
     * @param taskState the task state
     */
    TaskState(String taskState) {
        this.taskState = taskState;
    }

    /**
     * Gets test state.
     *
     * @return the test state
     */
    public String getTestState() {
        return taskState;
    }

    /**
     * Gets enum by name.
     *
     * @param taskStateValue the task state value
     * @return the enum by name
     */
    public static TaskState getEnumByName(String taskStateValue) {
        for (TaskState taskState : TaskState.values()) {
            if (taskState.getTestState().equals(taskStateValue)) {
                return taskState;
            }
        }
        return null;
    }
}
