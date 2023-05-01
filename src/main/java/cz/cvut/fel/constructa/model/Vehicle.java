package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.enums.VehicleType;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class representing a vehicle.
 */
@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {
    /**
     * The identifier of the vehicle.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_id", nullable = false)
    private Long id;

    /**
     * The factory that produced the vehicle.
     */
    @Column(name="factory", nullable = false)
    private String factory;

    /**
     * The name or model of the vehicle.
     */
    @Column(name="model", nullable = false)
    private String name;

    /**
     * The VIN code of the vehicle.
     */
    @Column(name="vin_code", nullable = false)
    private String vinCode;

    /**
     * The registration number of the vehicle.
     */
    @Column(name="registration_number", nullable = false)
    private String registrationNumber;

    /**
     * The condition of the vehicle's motorcycle watch.
     */
    @Column(name = "condition_motorcycle_watch", nullable = false)
    private Double conditionMotorcycleWatch;

    /**
     * The mileage of the vehicle.
     */
    @Column(name = "mileage", nullable = false)
    private Double mileage;
    /**
     * The date when the vehicle was bought.
     */
    @Column(name = "bought_at", nullable = false)
    private Date boughtAt;
    /**
     * The date when the vehicle was created.
     */
    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    /**
     * The reports associated with the vehicle.
     */
    @OneToMany(mappedBy = "vehicle")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<VehicleReport> vehicleReports = new ArrayList<>();

    /**
     * The type of the vehicle.
     */
    @Enumerated(EnumType.STRING)
    private VehicleType type;
}
