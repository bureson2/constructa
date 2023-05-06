package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Class representing a company.
 */
@Entity
@Table(name = "companies")
@Data
public class Company {
    /**
     * The company identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "company_id", nullable = false)
    private Long id;

    /**
     * The name of the company.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The VAT identification number of the company.
     */
    @Column(name = "din", nullable = false)
    private String din;

    /**
     * The commercial register identification number of the company.
     */
    @Column(name = "cin", nullable = false)
    private String cin;

    /**
     * The phone number of the company.
     */
    @Column(name = "phone", nullable = false)
    private String phone;

    /**
     * The address of the company.
     */
    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Location companyAddress;

    /**
     * The list of externalists working for the company.
     */
    @OneToMany(mappedBy = "company")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<User> externalist = new ArrayList<>();

    /**
     * The set of vehicle reports for which the company is a transport contractor.
     */
    @ManyToMany(mappedBy = "transportContractors")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Set<VehicleReport> contractorsTransportReports = new HashSet<>();

}
