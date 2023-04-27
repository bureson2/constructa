package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.model.loan.Warehouse;
import cz.cvut.fel.constructa.model.report.WorkReport;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Location.
 */
@Entity
@Table(name = "locations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id", nullable = false)
    private Long id;
    /**
     * The City.
     */
    @Column(name = "city")
    private String city;
    /**
     * The Street.
     */
    @Column(name = "street")
    private String street;
    /**
     * The Descriptive number.
     */
    @Column(name = "descriptive_number")
    private String descriptiveNumber;
    /**
     * The Country.
     */
    @Column(name = "country")
    private String country;
    /**
     * The Post code.
     */
    @Column(name = "postCode")
    private String postCode;
    /**
     * The Latitude.
     */
    @Column(name="latitude")
    private Double latitude;
    /**
     * The Longitude.
     */
    @Column(name="longitude")
    private Double longitude;
    /**
     * The Work reports.
     */
    @OneToMany(mappedBy = "location")
    private List<WorkReport> workReports = new ArrayList<>();

    /**
     * The Company.
     */
    @OneToOne(mappedBy = "companyAddress")
    private Company company;

    /**
     * The Warehouse.
     */
    @OneToOne(mappedBy = "warehouseAddress")
    private Warehouse warehouse;

    /**
     * The Project.
     */
    @OneToOne(mappedBy = "projectAddress")
    private Project project;

    /**
     * The Residents.
     */
    @OneToMany(mappedBy = "userAddress")
    private List<User> residents = new ArrayList<>();

}
