package cz.cvut.fel.constructa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type Vehicle dto.
 */
@Getter
@Setter
public class VehicleDTO {
    /**
     * The ID.
     */
    private Long id;
    /**
     * The Factory.
     */
    private String factory;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Registration number.
     */
    private String registrationNumber;
    /**
     * The Condition motorcycle watch.
     */
    private Double conditionMotorcycleWatch;
    /**
     * The Mileage.
     */
    private Double mileage;
    /**
     * The Type.
     */
    private String type;
    /**
     * The Bought at.
     */
    private Date boughtAt;
    /**
     * The Created at.
     */
    private Date createdAt;
    /**
     * The Qr code.
     */
    private String qrCode;
    /**
     * The Vin code.
     */
    private String vinCode;
}
