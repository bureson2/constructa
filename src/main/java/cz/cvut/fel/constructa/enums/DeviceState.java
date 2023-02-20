package cz.cvut.fel.constructa.enums;

public enum DeviceState {
    FUNCTIONAL("Functional"),
    UNFUNCTIONAL("Unfunctional"),
    UNDER_REPAIR("Under_repair"),
    TO_DISCARD("To_discard");

    private String deviceState;
    DeviceState(String deviceState) {
        this.deviceState = deviceState;
    }
    public String getDeviceState() {
        return deviceState;
    }
}
