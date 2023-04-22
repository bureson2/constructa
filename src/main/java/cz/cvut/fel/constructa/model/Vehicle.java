package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.enums.VehicleType;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_id", nullable = false)
    private Long id;

    @Column(name="factory", nullable = false)
    private String factory;

    //    model
    @Column(name="name", nullable = false)
    private String name;

    @Column(name="vin_code", nullable = false)
    private String vinCode;

    @Column(name="registration_number", nullable = false)
    private String registrationNumber;

    @Column(name = "condition_motorcycle_watch", nullable = false)
    private Double conditionMotorcycleWatch;

    @Column(name = "mileage", nullable = false)
    private Double mileage;
    @Column(name = "bought_at", nullable = false)
    private Date boughtAt;
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @OneToMany(mappedBy = "vehicle")
    private List<VehicleReport> vehicleReports = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private VehicleType type;
}
