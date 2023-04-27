package cz.cvut.fel.constructa.model.report;

import cz.cvut.fel.constructa.model.*;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The type Vehicle report.
 */
@Entity
@Table(name = "vehicle_reports")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleReport {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_report_id", nullable = false)
    private Long id;

    /**
     * The Original condition motorcycle watch.
     */
    @Column(name = "original_condition_motorcycle_watch")
    private Double originalConditionMotorcycleWatch;

    /**
     * The Afterwork condition motorcycle watch.
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
     * The Distance.
     */
    @Column(name = "distance")
    private Double distance;

    /**
     * The Purchase of fuel litres.
     */
    @Column(name = "purchase_of_fuel_litres")
    private Double purchaseOfFuelLitres;

    /**
     * The Time from.
     */
    @Column(name = "time_from")
    private Date timeFrom;

    /**
     * The Time to.
     */
    @Column(name = "time_to")
    private Date timeTo;

    /**
     * The Description.
     */
    @Column(name = "description")
    private String description;

    /**
     * The Vehicle.
     */
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    /**
     * The Construction diary.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project constructionDiary;

    /**
     * The Driver.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User driver;

    /**
     * The Transport contractors.
     */
    @ManyToMany
    @JoinTable(
            name = "constractors_transport_for_companies",
            joinColumns = { @JoinColumn(name = "vehicle_report_id") },
            inverseJoinColumns = { @JoinColumn(name = "company_id") }
    )
    private Set<Company> transportContractors = new HashSet<>();

}
