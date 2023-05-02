package cz.cvut.fel.constructa.model.role;

import cz.cvut.fel.constructa.enums.ExternalistRole;
import cz.cvut.fel.constructa.model.Project;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

/**
 * The ExternalistInProject class represents an external collaborator working on a project.
 * It contains information about the externalist's role on the project, the externalist user object,
 * and the project object.
 */
@Entity
@Table(name = "externalist_in_projects")
@Data
public class ExternalistInProject {
    /**
     * The unique identifier for the externalist in project.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "externalist_in_project_id", nullable = false)
    private Long id;

    /**
     * The roles of the externalist on the project.
     */
    @Enumerated(EnumType.STRING)
    private List<ExternalistRole> roles;

    /**
     * The externalist user object.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User externalist;

    /**
     * The project.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;
}
