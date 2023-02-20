package cz.cvut.fel.constructa.enums;

public enum VehicleType {
    CAR("Car"),
    VEHICLE("Vehicle"),
    TRAILER("Trailer");

    private String vehicleType;
    VehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }
    public String getVehicleType() {
        return vehicleType;
    }
}
