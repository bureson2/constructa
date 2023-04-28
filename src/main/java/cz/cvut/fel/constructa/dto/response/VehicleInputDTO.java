package cz.cvut.fel.constructa.dto.response;

import cz.cvut.fel.constructa.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Vehicle input dto.
 */
@Getter
@Setter
public class VehicleInputDTO {
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
     * The Type.
     */
    private VehicleType type;

}
