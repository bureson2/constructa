package cz.cvut.fel.constructa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type Vehicle report dto.
 */
@Getter
@Setter
public class VehicleReportDTO {
    /**
     * The Id.
     */
    private Long id;
    /**
     * The Original condition motorcycle watch.
     */
    private Double originalConditionMotorcycleWatch;
    /**
     * The Afterwork condition motorcycle watch.
     */
    private Double afterworkConditionMotorcycleWatch;
    /**
     * The Cargo mass.
     */
    private int cargoMass;
    /**
     * The Cargo type.
     */
    private String cargoType;
    /**
     * The Distance.
     */
    private Double distance;
    /**
     * The Purchase of fuel litres.
     */
    private Double purchaseOfFuelLitres;
    /**
     * The Time from.
     */
    private Date timeFrom;
    /**
     * The Time to.
     */
    private Date timeTo;
    /**
     * The Description.
     */
    private String description;
    /**
     * The Vehicle.
     */
    private VehicleDTO vehicle;
    /**
     * The Driver.
     */
    private UserDTO driver;

}
