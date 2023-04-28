package cz.cvut.fel.constructa.enums;

/**
 * The enum Device state.
 */
public enum DeviceState {
    /**
     * Functional device state.
     */
    FUNCTIONAL("Functional"),
    /**
     * Unfunctional device state.
     */
    UNFUNCTIONAL("Unfunctional"),
    /**
     * Under repair device state.
     */
    UNDER_REPAIR("Under_repair"),
    /**
     * To discard device state.
     */
    TO_DISCARD("To_discard");

    /**
     * The Device state.
     */
    private String deviceState;

    /**
     * Instantiates a new Device state.
     *
     * @param deviceState the device state
     */
    DeviceState(String deviceState) {
        this.deviceState = deviceState;
    }

    /**
     * Gets device state.
     *
     * @return the device state
     */
    public String getDeviceState() {
        return deviceState;
    }
}
