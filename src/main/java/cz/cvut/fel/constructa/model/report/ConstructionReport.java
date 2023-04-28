package cz.cvut.fel.constructa.model.report;

import cz.cvut.fel.constructa.enums.ConstructionDiaryReportState;
import cz.cvut.fel.constructa.model.Document;
import cz.cvut.fel.constructa.model.Project;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A report about a construction site's work for a given day.
 */
@Entity
@Table(name = "construction_reports")
@Data
public class ConstructionReport {
    /**
     * The unique identifier of this report.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "construction_report_id", nullable = false)
    private Long id;

    /**
     * The name of the task that this report is about.
     */
    @Column(name = "task_name")
    private String taskName;

    /**
     * Additional notes related to the report.
     */
    @Column(name = "note")
    private String note;

    /**
     * The weather condition during the work.
     */
    @Column(name = "weather_description")
    private String weather;

    /**
     * The date when the work was performed and this report was written.
     */
    @Column(name = "date")
    private Date date;

    /**
     * The set of documents related to the construction project, included in this report.
     */
    @ManyToMany
    @JoinTable(
            name = "reports_documents",
            joinColumns = { @JoinColumn(name = "construction_report_id") },
            inverseJoinColumns = { @JoinColumn(name = "document_id") }
    )
    private Set<Document> constructionDocumentLinks = new HashSet<>();

    /**
     * The user who wrote this report.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User executor;

    /**
     * The project that this report is related to.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    /**
     * The state of this report, indicating if it is draft, final or cancelled.
     */
    @Enumerated(EnumType.STRING)
    private ConstructionDiaryReportState state;
}
