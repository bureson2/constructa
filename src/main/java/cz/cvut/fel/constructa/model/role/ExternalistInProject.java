package cz.cvut.fel.constructa.model.role;

import cz.cvut.fel.constructa.enums.ExternalistRole;
import cz.cvut.fel.constructa.model.Project;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * The type Externalist in project.
 */
@Entity
@Table(name = "externalist_in_projects")
@Data
public class ExternalistInProject {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "externalist_in_project_id", nullable = false)
    private Long id;

    /**
     * The Roles.
     */
    @Enumerated(EnumType.STRING)
    private List<ExternalistRole> roles;

    /**
     * The Externalist.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User externalist;

    /**
     * The Project.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


}
