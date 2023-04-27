package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.enums.VehicleType;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Vehicle.
 */
@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_id", nullable = false)
    private Long id;

    /**
     * The Factory.
     */
    @Column(name="factory", nullable = false)
    private String factory;

    /**
     * The Name.
     */
//    model
    @Column(name="name", nullable = false)
    private String name;

    /**
     * The Vin code.
     */
    @Column(name="vin_code", nullable = false)
    private String vinCode;

    /**
     * The Registration number.
     */
    @Column(name="registration_number", nullable = false)
    private String registrationNumber;

    /**
     * The Condition motorcycle watch.
     */
    @Column(name = "condition_motorcycle_watch", nullable = false)
    private Double conditionMotorcycleWatch;

    /**
     * The Mileage.
     */
    @Column(name = "mileage", nullable = false)
    private Double mileage;
    /**
     * The Bought at.
     */
    @Column(name = "bought_at", nullable = false)
    private Date boughtAt;
    /**
     * The Created at.
     */
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    /**
     * The Vehicle reports.
     */
    @OneToMany(mappedBy = "vehicle")
    private List<VehicleReport> vehicleReports = new ArrayList<>();

    /**
     * The Type.
     */
    @Enumerated(EnumType.STRING)
    private VehicleType type;
}
