package cz.cvut.fel.constructa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "construction_diaries")
@Getter
@Setter
@NoArgsConstructor
public class ConstructionDiary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "construction_diary_id", nullable = false)
    private Long id;

//    TODO
}
