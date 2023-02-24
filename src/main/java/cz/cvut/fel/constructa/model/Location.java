package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.model.report.WorkReport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "locations")
@Getter
@Setter
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id", nullable = false)
    private Long id;

    @Column(name = "city")
    private String city;
    @Column(name = "street")
    private String street;
    @Column(name = "name")
    private String name;
    //    TODO own type?
    @Column(name = "country")
    private String country;
    //    TODO own validated type?
    @Column(name = "postCode")
    private String postCode;
    @Column(name = "qrCode")
    private String qrCode;
    @OneToMany(mappedBy = "location")
    private List<WorkReport> workReports = new ArrayList<>();

    @OneToOne(mappedBy = "companyAddress")
    private Company company;

}
