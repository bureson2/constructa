package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class VehicleReportRequest {
//    private Long id;
    private Double originalConditionMotorcycleWatch;
    private Double afterworkConditionMotorcycleWatch;
    private int cargoMass;
    private String cargoType;
    private Double distance;
    private Double purchaseOfFuelLitres;
    private Date timeFrom;
    private Date timeTo;
    private Long vehicle;
    private Long driver;
}