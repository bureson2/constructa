package cz.cvut.fel.constructa.model.report;

import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a financial report for a project, containing information on wages and attendance.
 */
@Entity
@Table(name = "finance_reports")
@Data
public class FinanceReport {
    /**
     * The ID of the finance report.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "finance_report_id", nullable = false)
    private Long id;

    /**
     * The start time of the reporting period.
     */
    @Column(name = "time_from", nullable = false)
    private Date timeFrom;

    /**
     * The end time of the reporting period.
     */
    @Column(name="time_to", nullable = false)
    private Date timeTo;

    /**
     * The wage rate for the reporting period.
     */
    @Column(name="wage")
    private int wage;

    /**
     * The list of work reports for the reporting period.
     */
    @OneToMany(mappedBy = "financeReport")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<WorkReport> attendance = new ArrayList<>();

    /**
     * The salaried employee whose wages are being reported.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User salariedEmployee;
}
