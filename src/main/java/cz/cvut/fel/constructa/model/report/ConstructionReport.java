package cz.cvut.fel.constructa.model.report;

import cz.cvut.fel.constructa.enums.ConstructionDiaryReportState;
import cz.cvut.fel.constructa.enums.WorkReportType;
import cz.cvut.fel.constructa.model.*;
import cz.cvut.fel.constructa.model.role.ExternalistInProject;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

/**
 * The type Construction report.
 */
@Entity
@Table(name = "construction_reports")
@Data
public class ConstructionReport {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "construction_report_id", nullable = false)
    private Long id;

    /**
     * The Task name.
     */
    @Column(name = "task_name")
    private String taskName;

    /**
     * The Note.
     */
    @Column(name = "note")
    private String note;

    /**
     * The Weather.
     */
    @Column(name = "weather_description")
    private String weather;

    /**
     * The Date.
     */
    @Column(name = "date")
    private Date date;

    /**
     * The Construction document links.
     */
    @ManyToMany
    @JoinTable(
            name = "reports_documents",
            joinColumns = { @JoinColumn(name = "construction_report_id") },
            inverseJoinColumns = { @JoinColumn(name = "document_id") }
    )
    private Set<Document> constructionDocumentLinks = new HashSet<>();

    /**
     * The Construction images.
     */
    @OneToMany(mappedBy = "report")
    private List<Image> constructionImages = new ArrayList<>();

    /**
     * The Executor.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User executor;

    /**
     * The Project.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    /**
     * The State.
     */
    @Enumerated(EnumType.STRING)
    private ConstructionDiaryReportState state;
}
