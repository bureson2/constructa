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
}
