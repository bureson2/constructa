package cz.cvut.fel.constructa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
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

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location companyAddress;
}
