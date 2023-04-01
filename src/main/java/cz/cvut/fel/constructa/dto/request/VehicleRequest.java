package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.enums.VehicleType;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class VehicleRequest {
    private Long id;
    private String factory;
    private String name;
    private String registrationNumber;
    private Double conditionMotorcycleWatch;
    private Double mileage;
    private VehicleType type;
    private Date boughtAt;
    private Date createdAt;
    private String qrCode;
    private String vinCode;
}
