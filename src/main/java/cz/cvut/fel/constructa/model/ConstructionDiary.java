package cz.cvut.fel.constructa.model;

import cz.cvut.fel.constructa.model.report.ConstructionReport;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.model.role.ResponsiblePersonInConstructionDiary;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "construction_diaries")
@Data
public class ConstructionDiary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "construction_diary_id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project constructionDiaryProject;

    @OneToMany(mappedBy = "constructionDiary")
    private List<ResponsiblePersonInConstructionDiary> responsiblePerson = new ArrayList<>();

    @OneToMany(mappedBy = "constructionDiary")
    private List<ConstructionReport> constructionReports = new ArrayList<>();

    @OneToMany(mappedBy = "constructionDiary")
    private List<VehicleReport> vehicleReports = new ArrayList<>();
}
