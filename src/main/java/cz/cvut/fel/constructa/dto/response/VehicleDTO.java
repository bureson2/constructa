package cz.cvut.fel.constructa.dto.response;

import cz.cvut.fel.constructa.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VehicleDTO {
    private Long id;
    private String factory;
    private String name;
    private String registrationNumber;
    private Double conditionMotorcycleWatch;
    private Double mileage;
    private VehicleType type;
}
