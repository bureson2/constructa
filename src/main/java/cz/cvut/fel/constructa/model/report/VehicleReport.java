package cz.cvut.fel.constructa.model.report;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicle_reports")
@Getter
@Setter
@NoArgsConstructor
public class VehicleReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_report_id", nullable = false)
    private Long id;

//    TODO

}
