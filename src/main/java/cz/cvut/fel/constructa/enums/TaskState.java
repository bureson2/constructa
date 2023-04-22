package cz.cvut.fel.constructa.enums;

public enum TaskState {
    NEW("Nový"),
    IN_PROGRESS("V řešení"),
    STOPPED("Pozastaveno"),
    DONE("Hotovo");

    private String taskState;
    TaskState(String taskState) {
        this.taskState = taskState;
    }
    public String getTestState() {
        return taskState;
    }
    public static TaskState getEnumByName(String taskStateValue) {
        for (TaskState taskState : TaskState.values()) {
            if (taskState.getTestState().equals(taskStateValue)) {
                return taskState;
            }
        }
        return null;
    }
}
