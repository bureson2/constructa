package cz.cvut.fel.constructa.enums;

public enum VehicleType {
    CAR("Auto"),
    VEHICLE("Stroj"),
    TRAILER("Přívěs");

    private String vehicleType;
    VehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getVehicleType() {
        return vehicleType;
    }
}
