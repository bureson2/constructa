package cz.cvut.fel.constructa.model.role;

import cz.cvut.fel.constructa.enums.Role;
import cz.cvut.fel.constructa.model.*;
import cz.cvut.fel.constructa.model.loan.Loan;
import cz.cvut.fel.constructa.model.report.ConstructionReport;
import cz.cvut.fel.constructa.model.report.FinanceReport;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.model.report.WorkReport;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * The User class represents a user in the system.
 * It contains information about the user's role, login credentials, contact information,
 * and the various reports and tasks associated with them.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class User implements UserDetails {
    /**
     * The unique identifier for the user.
     */
//    TODO do bakalarky napis o 2 moznostech extends user
//    https://www.youtube.com/watch?v=KxqlJblhzfI&t=3595s 28:00
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;

    /**
     * The username for the user.
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * The email address for the user.
     */
    @Column(name = "email")
    private String email;
    /**
     * The password for the user.
     */
    @Column(name = "password")
    private String password;

    /**
     * The phone number for the user.
     */
    @Column(name = "phone")
    private String phone;

    /**
     * The role of the user in the system.
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * The title before name for the user.
     */
    @Column(name = "title_before_name")
    private String titleBeforeName;

    /**
     * The first name of the user.
     */
//    @Column(name = "firstname", nullable = false)
    @Column(name = "firstname")
    private String firstname;

    /**
     * The last name of the user.
     */
//    @Column(name = "lastname", nullable = false)
    @Column(name = "lastname")
    private String lastname;

    /**
     * The title after name for the user.
     */
    @Column(name = "title_after_name")
    private String titleAfterName;

    /**
     * The bank account number for the user.
     */
    @Column(name = "bank_account")
    private String bankAccount;

    /**
     * The date the user was accepted into the system.
     */
    @Column(name = "date_of_acceptance")
    private Date dateOfAcceptance;

    /**
     * The date of birth for the user.
     */
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    /**
     * The birth ID for the user.
     */
//    @Column(name = "birth_id", nullable = false)
    @Column(name = "birth_id")
    private String birthId;

    /**
     * The hourly rate for the user.
     */
    @Column(name = "hour_rate")
    private int hourRate;

    /**
     * The monthly salary for the user.
     */
    @Column(name = "month_salary")
    private int monthSalary;

    /**
     * The list of tasks created by the user.
     */
    @OneToMany(mappedBy = "author")
    private List<Task> createdTasks = new ArrayList<>();

    /**
     * The list of tasks assigned to the user.
     */
    @OneToMany(mappedBy = "assignee")
    private List<Task> assignedTasks = new ArrayList<>();

    /**
     * The User's attendance report.
     */
    @OneToMany(mappedBy = "reportingEmployee")
    private List<WorkReport> attendance = new ArrayList<>();

    /**
     * The User's finance reports.
     */
    @OneToMany(mappedBy = "salariedEmployee")
    private List<FinanceReport> financeReports = new ArrayList<>();

    /**
     * The list of projects managed by the user.
     */
    @OneToMany(mappedBy = "projectManager")
    private List<Project> projects = new ArrayList<>();

    /**
     * The User's address.
     */
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location userAddress;

    /**
     * The list of external projects the user has worked on.
     */
    @OneToMany(mappedBy = "externalist")
    private List<ExternalistInProject> externalProjectWork = new ArrayList<>();

    /**
     * The list of vehicle reports submitted by the user.
     */
    @OneToMany(mappedBy = "driver")
    private List<VehicleReport> vehicleReports = new ArrayList<>();

    /**
     * The list of loans that the user has taken.
     */
    @OneToMany(mappedBy = "loaner")
    private List<Loan> loans = new ArrayList<>();

    /**
     * The company that the user is associated with.
     */
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    /**
     * The set of responsible persons in construction diaries that the user is associated with.
     */
    @ManyToMany
    @JoinTable(
            name = "responsible_persons_construction_diaries",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "responsible_person_id") }
    )
    private Set<ResponsiblePersonInConstructionDiary> responsiblePersons = new HashSet<>();

    /**
     * The set of user document links.
     */
    @ManyToMany
    @JoinTable(
            name = "user_documents",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "document_id") }
    )
    private Set<Document> userDocumentLinks = new HashSet<>();

    /**
     * The list of construction reports that the user has executed.
     */
    @OneToMany(mappedBy = "executor")
    private List<ConstructionReport> constructionsReport = new ArrayList<>();

    /**
     * Gets authorities.
     *
     * @return the authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * Is account non expired boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Is account non locked boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Is credentials non expired boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Is enabled boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
