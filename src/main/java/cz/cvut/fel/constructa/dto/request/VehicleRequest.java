package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.enums.VehicleType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * The type Vehicle request.
 */
@Data
@Builder
public class VehicleRequest {
    /**
     * The Id.
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
    private VehicleType type;
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
