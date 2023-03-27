package cz.cvut.fel.constructa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VehicleReportDTO {
    private Long id;
    private Double originalConditionMotorcycleWatch;
    private Double afterworkConditionMotorcycleWatch;
    private int cargoMass;
    private String cargoType;
    private Double distance;
    private Double purchaseOfFuelLitres;
    private Date timeFrom;
    private Date timeTo;
    //    private Location startAt;
//    private Location finishAt;
    private Date description;
    private VehicleDTO vehicle;
    private UserDTO driver;

}
