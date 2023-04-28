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
 * Class representing a location.
 */
@Entity
@Table(name = "locations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    /**
     * The location identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id", nullable = false)
    private Long id;
    /**
     * The city where the location is situated.
     */
    @Column(name = "city")
    private String city;
    /**
     * The street where the location is situated.
     */
    @Column(name = "street")
    private String street;
    /**
     * The descriptive number of the location.
     */
    @Column(name = "descriptive_number")
    private String descriptiveNumber;
    /**
     * The country where the location is situated.
     */
    @Column(name = "country")
    private String country;
    /**
     * The post code of the location.
     */
    @Column(name = "postCode")
    private String postCode;
    /**
     * The latitude of the location.
     */
    @Column(name="latitude")
    private Double latitude;
    /**
     * The longitude of the location.
     */
    @Column(name="longitude")
    private Double longitude;
    /**
     * The list of work reports associated with the location.
     */
    @OneToMany(mappedBy = "location")
    private List<WorkReport> workReports = new ArrayList<>();

    /**
     * The company with the address at the location.
     */
    @OneToOne(mappedBy = "companyAddress")
    private Company company;

    /**
     * The warehouse with the address at the location.
     */
    @OneToOne(mappedBy = "warehouseAddress")
    private Warehouse warehouse;

    /**
     * The project with the address at the location.
     */
    @OneToOne(mappedBy = "projectAddress")
    private Project project;

    /**
     * The list of residents who have their address at the location.
     */
    @OneToMany(mappedBy = "userAddress")
    private List<User> residents = new ArrayList<>();

}
