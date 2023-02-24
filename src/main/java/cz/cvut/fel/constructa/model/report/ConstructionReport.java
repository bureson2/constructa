package cz.cvut.fel.constructa.model.report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "construction_reports")
@Getter
@Setter
@NoArgsConstructor
public class ConstructionReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "construction_report_id", nullable = false)
    private Long id;

    //    TODO
}
