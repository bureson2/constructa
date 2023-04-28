package cz.cvut.fel.constructa.model.report;

import cz.cvut.fel.constructa.model.*;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents a vehicle report entity.
 */
@Entity
@Table(name = "vehicle_reports")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleReport {
    /**
     * The ID of the vehicle report.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_report_id", nullable = false)
    private Long id;

    /**
     * The original condition of the motorcycle watch.
     */
    @Column(name = "original_condition_motorcycle_watch")
    private Double originalConditionMotorcycleWatch;

    /**
     * The after-work condition of the motorcycle watch.
     */
    @Column(name = "afterwork_condition_motorcycle_watch")
    private Double afterworkConditionMotorcycleWatch;

    /**
     * The Cargo mass.
     */
    @Column(name = "cargo_mass")
    private int cargoMass;

    /**
     * The Cargo type.
     */
    @Column(name = "cargo_type")
    private String cargoType;

    /**
     * The distance traveled.
     */
    @Column(name = "distance")
    private Double distance;

    /**
     * The amount of fuel purchased in litres.
     */
    @Column(name = "purchase_of_fuel_litres")
    private Double purchaseOfFuelLitres;

    /**
     * The start time of the report.
     */
    @Column(name = "time_from")
    private Date timeFrom;

    /**
     * The end time of the report.
     */
    @Column(name = "time_to")
    private Date timeTo;

    /**
     * A description of the report.
     */
    @Column(name = "description")
    private String description;

    /**
     * The vehicle associated with this report.
     */
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    /**
     * The construction diary associated with this report.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project constructionDiary;

    /**
     * The driver associated with this report.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User driver;

    /**
     * The transport contractors associated with this report.
     */
    @ManyToMany
    @JoinTable(
            name = "constractors_transport_for_companies",
            joinColumns = { @JoinColumn(name = "vehicle_report_id") },
            inverseJoinColumns = { @JoinColumn(name = "company_id") }
    )
    private Set<Company> transportContractors = new HashSet<>();

}
