package cz.cvut.fel.constructa.enums;

/**
 * The enum Vehicle type.
 */
public enum VehicleType {
    /**
     * Car vehicle type.
     */
    CAR("Auto"),
    /**
     * Vehicle vehicle type.
     */
    VEHICLE("Stroj"),
    /**
     * Trailer vehicle type.
     */
    TRAILER("Přívěs");

    /**
     * The Vehicle type.
     */
    private String vehicleType;

    /**
     * Instantiates a new Vehicle type.
     *
     * @param vehicleType the vehicle type
     */
    VehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    /**
     * Gets vehicle type.
     *
     * @return the vehicle type
     */
    public String getVehicleType() {
        return vehicleType;
    }
}
