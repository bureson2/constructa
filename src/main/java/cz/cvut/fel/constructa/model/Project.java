package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.enums.ProjectState;
import cz.cvut.fel.constructa.enums.Role;
import cz.cvut.fel.constructa.model.report.ConstructionReport;
import cz.cvut.fel.constructa.model.role.ExternalistInProject;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@Table(name = "projects")
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id", nullable = false)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "buildingFacility")
    private String buldingFacility;
    @Column(name = "started_at")
    private Date startedAt;
    @Column(name = "deadline")
    private Date deadline;

    @Enumerated(EnumType.STRING)
    private ProjectState state;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User projectManager;

    @OneToMany(mappedBy = "project")
    private List<ExternalistInProject> externalWorkers = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location projectAddress;

    @OneToMany(mappedBy = "constructionDiaryProject")
    private List<ConstructionDiary> constructionDiaries = new ArrayList<>();
}
