package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.model.report.ConstructionReport;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "document_id", nullable = false)
    private Long id;

    @Column(name = "link")
    private String link;

    @ManyToMany(mappedBy = "constructionDocumentLinks")
    private Set<ConstructionReport> constructionReports = new HashSet<>();

    @ManyToMany(mappedBy = "userDocumentLinks")
    private Set<User> employeeDocuments = new HashSet<>();


}
