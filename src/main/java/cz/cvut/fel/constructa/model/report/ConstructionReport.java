package cz.cvut.fel.constructa.model.report;

import cz.cvut.fel.constructa.enums.ConstructionDiaryReportState;
import cz.cvut.fel.constructa.enums.WorkReportType;
import cz.cvut.fel.constructa.model.Company;
import cz.cvut.fel.constructa.model.ConstructionDiary;
import cz.cvut.fel.constructa.model.Document;
import cz.cvut.fel.constructa.model.Image;
import cz.cvut.fel.constructa.model.role.ExternalistInProject;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "construction_reports")
@Data
public class ConstructionReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "construction_report_id", nullable = false)
    private Long id;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "note")
    private String note;

    @Column(name = "weather_description")
    private String weather;

    @ManyToMany
    @JoinTable(
            name = "reports_documents",
            joinColumns = { @JoinColumn(name = "construction_report_id") },
            inverseJoinColumns = { @JoinColumn(name = "document_id") }
    )
    private Set<Document> constructionDocumentLinks = new HashSet<>();

    @OneToMany(mappedBy = "report")
    private List<Image> constructionImages = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User executor;

    @ManyToOne
    @JoinColumn(name = "construction_diary_id")
    private ConstructionDiary constructionDiary;

    @Enumerated(EnumType.STRING)
    private ConstructionDiaryReportState state;
}
