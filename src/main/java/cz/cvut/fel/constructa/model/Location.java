package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.model.loan.Warehouse;
import cz.cvut.fel.constructa.model.report.WorkReport;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "locations")
@Data
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id", nullable = false)
    private Long id;

//    todo fi boolean

    @Column(name = "active_workplace")
    private Boolean active;
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
    @Column(name = "qr_code")
    private String qrCode;
    @OneToMany(mappedBy = "location")
    private List<WorkReport> workReports = new ArrayList<>();

//    @OneToMany(mappedBy = "location")
//    private List<Task> tasks = new ArrayList<>();

    @OneToOne(mappedBy = "companyAddress")
    private Company company;

    @OneToOne(mappedBy = "warehouseAddress")
    private Warehouse warehouse;

    @OneToOne(mappedBy = "projectAddress")
    private Project project;

    @OneToMany(mappedBy = "userAddress")
    private List<User> residents = new ArrayList<>();

}
