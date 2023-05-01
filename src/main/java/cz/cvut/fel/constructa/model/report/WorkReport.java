package cz.cvut.fel.constructa.model.report;

import cz.cvut.fel.constructa.enums.WorkReportType;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

/**
 * The WorkReport class represents a report of work performed by an employee.
 * It contains information about the time spent on the task, the location where the work was done,
 * the reporting employee, the finance report associated with the work, and the type of work.
 */
@Entity
@Table(name = "work_reports")
@Data
public class WorkReport {
    /**
     * The unique identifier for the work report.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "work_report_id", nullable = false)
    private Long id;

    /**
     * The start time of the work.
     */
    @Column(name = "time_from", nullable = false)
    private Date timeFrom;

    /**
     * The end time of the work.
     */
    @Column(name="time_to", nullable = false)
    private Date timeTo;

    /**
     * The duration of the work in minutes.
     */
    @Column(name="minutes", nullable = false)
    private int minutes;

    /**
     * The employee who reported the work.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User reportingEmployee;

    /**
     * The location where the work was performed.
     */
    @ManyToOne
    @JoinColumn(name = "location_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Location location;

    /**
     * The finance report associated with the work.
     */
    @ManyToOne
    @JoinColumn(name = "finance_report_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private FinanceReport financeReport;

    /**
     * The type of work performed.
     */
    @Enumerated(EnumType.STRING)
    private WorkReportType type;
}
