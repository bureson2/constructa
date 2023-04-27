package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Company.
 */
@Entity
@Table(name = "companies")
@Data
public class Company {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id", nullable = false)
    private Long id;

    /**
     * The Name.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The Din.
     */
    @Column(name = "din", nullable = false)
    private String din;

    /**
     * The Cin.
     */
    @Column(name = "cin", nullable = false)
    private String cin;

    /**
     * The Phone.
     */
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * The Company address.
     */
    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
    private Location companyAddress;

    /**
     * The Externalist.
     */
    @OneToMany(mappedBy = "company")
    private List<User> externalist = new ArrayList<>();

    /**
     * The Contractors transport reports.
     */
    @ManyToMany(mappedBy = "transportContractors")
    private Set<VehicleReport> contractors_transport_reports = new HashSet<>();

}
