package cz.cvut.fel.constructa.model.role;

import cz.cvut.fel.constructa.enums.ConstructionDiaryRole;
import cz.cvut.fel.constructa.model.ConstructionDiary;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "responsible_persons_in_construction_diary")
@Data
public class ResponsiblePersonInConstructionDiary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "responsible_person_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private List<ConstructionDiaryRole> roles;

    @ManyToOne
    @JoinColumn(name = "construction_diary_id")
    private ConstructionDiary constructionDiary;

    @ManyToMany(mappedBy = "responsiblePersons")
    private Set<User> responsiblePersons = new HashSet<>();
}
