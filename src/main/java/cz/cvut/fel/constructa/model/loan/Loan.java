package cz.cvut.fel.constructa.model.loan;

import cz.cvut.fel.constructa.model.Company;
import cz.cvut.fel.constructa.model.role.ResponsiblePersonInConstructionDiary;
import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "loans")
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_id", nullable = false)
    private Long id;

    @Column(name="time_from")
    private Date timeFrom;

    @Column(name="time_to")
    private Date timeTo;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User loaner;

    @ManyToMany
    @JoinTable(
            name = "device_in_loans",
            joinColumns = { @JoinColumn(name = "loan_id") },
            inverseJoinColumns = { @JoinColumn(name = "device_id") }
    )
    private Set<Device> loanedDevices = new HashSet<>();
}
