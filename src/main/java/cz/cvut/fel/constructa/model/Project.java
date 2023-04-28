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

/**
 * Class representing a project.
 */
@Entity
@Table(name = "projects")
@Data
public class Project {
    /**
     * The project identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id", nullable = false)
    private Long id;
    /**
     * The name of the project.
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * The name of the building facility associated with the project.
     */
    @Column(name = "buildingFacility", nullable = false)
    private String buldingFacility;
    /**
     * The date when the project started.
     */
    @Column(name = "started_at", nullable = false)
    private Date startedAt;
    /**
     * The deadline for the project.
     */
    @Column(name = "deadline", nullable = false)
    private Date deadline;

    /**
     * The state of the project.
     */
    @Enumerated(EnumType.STRING)
    private ProjectState state;

    /**
     * The project manager responsible for the project.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User projectManager;

    /**
     * The list of external workers involved in the project.
     */
    @OneToMany(mappedBy = "project")
    private List<ExternalistInProject> externalWorkers = new ArrayList<>();

    /**
     * The address of the project.
     */
    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
    private Location projectAddress;

    /**
     * The list of construction reports associated with the project.
     */
    @OneToMany(mappedBy = "project")
    private List<ConstructionReport> constructionReports = new ArrayList<>();

    /**
     * The list of vehicle reports associated with the project.
     */
    @OneToMany(mappedBy = "constructionDiary")
    private List<VehicleReport> vehicleReports = new ArrayList<>();

    /**
     * The list of responsible persons in the construction diary associated with the project.
     */
    @OneToMany(mappedBy = "constructionDiary")
    private List<ResponsiblePersonInConstructionDiary> responsiblePerson = new ArrayList<>();
}
