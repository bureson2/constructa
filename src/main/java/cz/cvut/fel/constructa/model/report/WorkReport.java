package cz.cvut.fel.constructa.model.report;

import cz.cvut.fel.constructa.enums.WorkReportType;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

/**
 * The type Work report.
 */
@Entity
@Table(name = "work_reports")
@Data
public class WorkReport {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "work_report_id", nullable = false)
    private Long id;

    /**
     * The Time from.
     */
    @Column(name = "time_from")
    private Date timeFrom;

    /**
     * The Time to.
     */
    @Column(name="time_to")
    private Date timeTo;

    /**
     * The Minutes.
     */
    @Column(name="minutes")
    private int minutes;

    /**
     * The Reporting employee.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reportingEmployee;

    /**
     * The Location.
     */
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    /**
     * The Finance report.
     */
    @ManyToOne
    @JoinColumn(name = "finance_report_id")
    private FinanceReport financeReport;

    /**
     * The Type.
     */
    @Enumerated(EnumType.STRING)
    private WorkReportType type;
}
