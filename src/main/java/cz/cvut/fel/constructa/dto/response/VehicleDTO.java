package cz.cvut.fel.constructa.dto.response;

import cz.cvut.fel.constructa.enums.VehicleType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VehicleDTO {
    private Long id;
    private String factory;
    private String name;
    private String registrationNumber;
    private Double conditionMotorcycleWatch;
    private Double mileage;
    private String type;
    private Date boughtAt;
    private Date createdAt;
    private String qrCode;
    private String vinCode;
}
