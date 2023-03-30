package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "companies")
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;
//    TODO special type
    @Column(name = "din")
    private String din;
    //    TODO special type
    @Column(name = "cin")
    private String cin;

    @Column(name = "phone")
    private String phone;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location companyAddress;

    @OneToMany(mappedBy = "company")
    private List<User> externalist = new ArrayList<>();

    @ManyToMany(mappedBy = "transportContractors")
    private Set<VehicleReport> contractors_transport_reports = new HashSet<>();

}
