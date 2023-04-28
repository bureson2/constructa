package cz.cvut.fel.constructa.model.loan;

import cz.cvut.fel.constructa.model.role.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * The class represents a loan of devices to a user for a certain period of time.
 */
@Entity
@Table(name = "loans")
@Data
public class Loan {
    /**
     * The loan ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_id", nullable = false)
    private Long id;

    /**
     * The starting time of the loan.
     */
    @Column(name="time_from")
    private Date timeFrom;

    /**
     * The ending time of the loan.
     */
    @Column(name="time_to")
    private Date timeTo;

    /**
     * The user who is borrowing the devices.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User loaner;

    /**
     * The set of devices that are loaned to the user.
     */
    @ManyToMany
    @JoinTable(
            name = "device_in_loans",
            joinColumns = { @JoinColumn(name = "loan_id") },
            inverseJoinColumns = { @JoinColumn(name = "device_id") }
    )
    private Set<Device> loanedDevices = new HashSet<>();
}
