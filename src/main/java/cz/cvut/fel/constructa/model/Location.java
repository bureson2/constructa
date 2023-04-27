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

@Entity
@Table(name = "locations")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id", nullable = false)
    private Long id;
    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "descriptive_number")
    private String descriptiveNumber;
    @Column(name = "country")
    private String country;
    @Column(name = "postCode")
    private String postCode;
    @Column(name="latitude")
    private Double latitude;
    @Column(name="longitude")
    private Double longitude;
    @OneToMany(mappedBy = "location")
    private List<WorkReport> workReports = new ArrayList<>();

    @OneToOne(mappedBy = "companyAddress")
    private Company company;

    @OneToOne(mappedBy = "warehouseAddress")
    private Warehouse warehouse;

    @OneToOne(mappedBy = "projectAddress")
    private Project project;

    @OneToMany(mappedBy = "userAddress")
    private List<User> residents = new ArrayList<>();

}
