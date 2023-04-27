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
 * The type Project.
 */
@Entity
@Table(name = "projects")
@Data
public class Project {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id", nullable = false)
    private Long id;
    /**
     * The Name.
     */
    @Column(name = "name", nullable = false)
    private String name;
    /**
     * The Bulding facility.
     */
    @Column(name = "buildingFacility", nullable = false)
    private String buldingFacility;
    /**
     * The Started at.
     */
    @Column(name = "started_at", nullable = false)
    private Date startedAt;
    /**
     * The Deadline.
     */
    @Column(name = "deadline", nullable = false)
    private Date deadline;

    /**
     * The State.
     */
    @Enumerated(EnumType.STRING)
    private ProjectState state;

    /**
     * The Project manager.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User projectManager;

    /**
     * The External workers.
     */
    @OneToMany(mappedBy = "project")
    private List<ExternalistInProject> externalWorkers = new ArrayList<>();

    /**
     * The Project address.
     */
    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id", nullable = false)
    private Location projectAddress;

    /**
     * The Construction reports.
     */
    @OneToMany(mappedBy = "project")
    private List<ConstructionReport> constructionReports = new ArrayList<>();

    /**
     * The Vehicle reports.
     */
    @OneToMany(mappedBy = "constructionDiary")
    private List<VehicleReport> vehicleReports = new ArrayList<>();

    /**
     * The Responsible person.
     */
    @OneToMany(mappedBy = "constructionDiary")
    private List<ResponsiblePersonInConstructionDiary> responsiblePerson = new ArrayList<>();
}
