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

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "din", nullable = false)
    private String din;

    @Column(name = "cin", nullable = false)
    private String cin;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
    private Location companyAddress;

    @OneToMany(mappedBy = "company")
    private List<User> externalist = new ArrayList<>();

    @ManyToMany(mappedBy = "transportContractors")
    private Set<VehicleReport> contractors_transport_reports = new HashSet<>();

}
