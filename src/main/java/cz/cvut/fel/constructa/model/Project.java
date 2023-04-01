package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.enums.ProjectState;
import cz.cvut.fel.constructa.model.report.ConstructionReport;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.model.role.ExternalistInProject;
import cz.cvut.fel.constructa.model.role.ResponsiblePersonInConstructionDiary;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "buildingFacility", nullable = false)
    private String buldingFacility;
    @Column(name = "started_at", nullable = false)
    private Date startedAt;
    @Column(name = "deadline", nullable = false)
    private Date deadline;

    @Enumerated(EnumType.STRING)
    private ProjectState state;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User projectManager;

    @OneToMany(mappedBy = "project")
    private List<ExternalistInProject> externalWorkers = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
    private Location projectAddress;

    @OneToMany(mappedBy = "project")
    private List<ConstructionReport> constructionReports = new ArrayList<>();

    @OneToMany(mappedBy = "constructionDiary")
    private List<VehicleReport> vehicleReports = new ArrayList<>();

    @OneToMany(mappedBy = "constructionDiary")
    private List<ResponsiblePersonInConstructionDiary> responsiblePerson = new ArrayList<>();
}
