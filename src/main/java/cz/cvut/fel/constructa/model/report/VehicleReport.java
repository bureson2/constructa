package cz.cvut.fel.constructa.model.report;

import cz.cvut.fel.constructa.model.*;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "vehicle_reports")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VehicleReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_report_id", nullable = false)
    private Long id;

    @Column(name = "original_condition_motorcycle_watch")
    private Double originalConditionMotorcycleWatch;

    @Column(name = "afterwork_condition_motorcycle_watch")
    private Double afterworkConditionMotorcycleWatch;

    @Column(name = "cargo_mass")
    private int cargoMass;

    @Column(name = "cargo_type")
    private String cargoType;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "purchase_of_fuel_litres")
    private Double purchaseOfFuelLitres;

    @Column(name = "time_from")
    private Date timeFrom;

    @Column(name = "time_to")
    private Date timeTo;

    @Column(name = "description")
    private Date description;

//    MAYBE TODO
    @ManyToOne
    @JoinColumn(name = "location_id", insertable=false, updatable=false)
    private Location startAt;

    @ManyToOne
    @JoinColumn(name = "location_id", insertable=false, updatable=false)
    private Location finishAt;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project constructionDiary;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User driver;

    @ManyToMany
    @JoinTable(
            name = "constractors_transport_for_companies",
            joinColumns = { @JoinColumn(name = "vehicle_report_id") },
            inverseJoinColumns = { @JoinColumn(name = "company_id") }
    )
    private Set<Company> transportContractors = new HashSet<>();

}
