package cz.cvut.fel.constructa.enums;

/**
 * The enum Device type.
 */
public enum DeviceType {
    /**
     * Electric device type.
     */
    ELECTRIC("Electric"),
    /**
     * Benzine device type.
     */
    BENZINE("Benzine"),
    /**
     * Others device type.
     */
    OTHERS("Others");

    /**
     * The Device type.
     */
    private String deviceType;

    /**
     * Instantiates a new Device type.
     *
     * @param deviceType the device type
     */
    DeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    /**
     * Gets device type.
     *
     * @return the device type
     */
    public String getDeviceType() {
        return deviceType;
    }
}
