package cz.cvut.fel.constructa.enums;

public enum DeviceType {
    ELECTRIC("Electric"),
    BENZINE("Benzine"),
    OTHERS("Others");

    private String deviceType;
    DeviceType(String deviceType) {
        this.deviceType = deviceType;
    }
    public String getDeviceType() {
        return deviceType;
    }
}
