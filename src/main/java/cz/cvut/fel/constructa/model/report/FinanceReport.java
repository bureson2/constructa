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

@Entity
@Table(name = "finance_reports")
@Data
public class FinanceReport {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "finance_report_id", nullable = false)
    private Long id;

    @Column(name = "time_from")
    private Date timeFrom;

    @Column(name="time_to")
    private Date timeTo;

    @Column(name="wage")
    private int wage;

//    TODO study CascadeTypes
//    TODO check camel case
    @OneToMany(mappedBy = "financeReport")
    private List<WorkReport> attendance = new ArrayList<>();

//    TODO check if it is ok
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User salariedEmployee;
}
