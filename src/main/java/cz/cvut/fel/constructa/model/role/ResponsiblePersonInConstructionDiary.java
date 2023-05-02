package cz.cvut.fel.constructa.model.role;

import cz.cvut.fel.constructa.enums.ConstructionDiaryRole;
import cz.cvut.fel.constructa.model.Project;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The ResponsiblePersonInConstructionDiary class represents a responsible person working on a construction diary.
 * It contains information about the responsible person's role on the construction diary,
 * the construction diary project object, and the set of responsible persons.
 */
@Entity
@Table(name = "responsible_persons_in_construction_diary")
@Data
public class ResponsiblePersonInConstructionDiary {
    /**
     * The unique identifier for the responsible person in construction diary.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "responsible_person_id", nullable = false)
    private Long id;

    /**
     * The roles of the responsible person on the construction diary.
     */
    @Enumerated(EnumType.STRING)
    private List<ConstructionDiaryRole> roles;

    /**
     * The construction diary.
     */
    @ManyToOne
    @JoinColumn(name = "project_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project constructionDiary;

    /**
     * The set of responsible persons.
     */
    @ManyToMany(mappedBy = "responsiblePersons")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<User> responsiblePersons = new HashSet<>();
}
