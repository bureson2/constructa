package cz.cvut.fel.constructa.enums;

public enum TaskState {
    NEW("New"),
    IN_PROGRESS("In_progress"),
    DONE("Done");

    private String taskState;
    TaskState(String taskState) {
        this.taskState = taskState;
    }
    public String getTestState() {
        return taskState;
    }
}
