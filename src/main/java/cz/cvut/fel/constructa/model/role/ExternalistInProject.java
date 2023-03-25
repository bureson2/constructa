package cz.cvut.fel.constructa.model.role;

import cz.cvut.fel.constructa.enums.ExternalistRole;
import cz.cvut.fel.constructa.enums.Role;
import cz.cvut.fel.constructa.model.Project;
import cz.cvut.fel.constructa.model.report.FinanceReport;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "externalist_in_projects")
@Data
public class ExternalistInProject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "externalist_in_project_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private List<ExternalistRole> roles;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User externalist;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


}
