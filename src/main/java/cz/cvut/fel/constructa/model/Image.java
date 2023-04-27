package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.model.report.ConstructionReport;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "images")
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "image_id", nullable = false)
    private Long id;

    @Column(name = "link")
    private String link;

    @ManyToOne
    @JoinColumn(name = "construction_report_id")
    private ConstructionReport report;
}
