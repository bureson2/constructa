package cz.cvut.fel.constructa.model.report;

import cz.cvut.fel.constructa.model.Task;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The type Finance report.
 */
@Entity
@Table(name = "finance_reports")
@Data
public class FinanceReport {
    /**
     * The Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "finance_report_id", nullable = false)
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
     * The Wage.
     */
    @Column(name="wage")
    private int wage;

    /**
     * The Attendance.
     */
    @OneToMany(mappedBy = "financeReport")
    private List<WorkReport> attendance = new ArrayList<>();

    /**
     * The Salaried employee.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User salariedEmployee;
}
