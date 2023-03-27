package cz.cvut.fel.constructa.model.report;

import cz.cvut.fel.constructa.enums.WorkReportType;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "work_reports")
@Data
public class WorkReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "work_report_id", nullable = false)
    private Long id;

    @Column(name = "time_from")
    private Date timeFrom;

    @Column(name="time_to")
    private Date timeTo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reportingEmployee;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "finance_report_id")
    private FinanceReport financeReport;

    @Enumerated(EnumType.STRING)
    private WorkReportType type;
}
