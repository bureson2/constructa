package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "project_id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "buildingFacility")
    private String buldingFacility;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User projectManager;
}
