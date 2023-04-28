package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.enums.MultimediaType;
import cz.cvut.fel.constructa.enums.ProjectState;
import cz.cvut.fel.constructa.model.report.ConstructionReport;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * Class representing a document.
 */
@Entity
@Table(name = "documents")
@Data
public class Document {
    /**
     * The document identifier.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "document_id", nullable = false)
    private Long id;

    /**
     * The link to the document.
     */
    @Column(name = "link")
    private String link;

    /**
     * The set of construction reports to which the document is linked.
     */
    @ManyToMany(mappedBy = "constructionDocumentLinks")
    private Set<ConstructionReport> constructionReports = new HashSet<>();

    /**
     * The set of employees to which the document is linked.
     */
    @ManyToMany(mappedBy = "userDocumentLinks")
    private Set<User> employeeDocuments = new HashSet<>();

    /**
     * The type of multimedia.
     */
    @Enumerated(EnumType.STRING)
    private MultimediaType type;
}
