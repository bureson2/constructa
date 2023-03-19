package cz.cvut.fel.constructa.dto.response;

import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.model.role.User;
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
    private Double distanceAfter;
    private Double distanceBefore;
    private int purchaseOfFuelLitres;
    private Date timeFrom;
    private Date timeTo;
    private Location startAt;
    private Location finishAt;
    private Vehicle vehicle;
    private User driver;

}
