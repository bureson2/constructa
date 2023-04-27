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
 * The type User.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class User implements UserDetails {
    /**
     * The Id.
     */
//    TODO do bakalarky napis o 2 moznostech extends user
//    https://www.youtube.com/watch?v=KxqlJblhzfI&t=3595s 28:00
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;

    /**
     * The Username.
     */
    @Column(name = "username", nullable = false)
    private String username;

    /**
     * The Email.
     */
    @Column(name = "email")
    private String email;
    /**
     * The Password.
     */
    @Column(name = "password")
    private String password;

    /**
     * The Phone.
     */
    @Column(name = "phone")
    private String phone;

    /**
     * The Role.
     */
//    @Enumerated(EnumType.STRING)
//    private List<Role> roles;
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * The Title before name.
     */
    @Column(name = "title_before_name")
    private String titleBeforeName;

    /**
     * The Firstname.
     */
//    @Column(name = "firstname", nullable = false)
    @Column(name = "firstname")
    private String firstname;

    /**
     * The Lastname.
     */
//    @Column(name = "lastname", nullable = false)
    @Column(name = "lastname")
    private String lastname;

    /**
     * The Title after name.
     */
    @Column(name = "title_after_name")
    private String titleAfterName;

    /**
     * The Bank account.
     */
//    @Column(name = "bank_account", nullable = false)
    @Column(name = "bank_account")
    private String bankAccount;

    /**
     * The Date of acceptance.
     */
//    @Column(name = "date_of_acceptance", nullable = false)
    @Column(name = "date_of_acceptance")
    private Date dateOfAcceptance;

    /**
     * The Date of birth.
     */
//    @Column(name = "date_of_birth", nullable = false)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    /**
     * The Birth id.
     */
//    @Column(name = "birth_id", nullable = false)
    @Column(name = "birth_id")
    private String birthId;

    /**
     * The Hour rate.
     */
    @Column(name = "hour_rate")
    private int hourRate;

    /**
     * The Month salary.
     */
    @Column(name = "month_salary")
    private int monthSalary;

    /**
     * The Created tasks.
     */
    @OneToMany(mappedBy = "author")
    private List<Task> createdTasks = new ArrayList<>();

    /**
     * The Assigned tasks.
     */
    @OneToMany(mappedBy = "assignee")
    private List<Task> assignedTasks = new ArrayList<>();

    /**
     * The Attendance.
     */
    @OneToMany(mappedBy = "reportingEmployee")
    private List<WorkReport> attendance = new ArrayList<>();

    /**
     * The Finance reports.
     */
    @OneToMany(mappedBy = "salariedEmployee")
    private List<FinanceReport> financeReports = new ArrayList<>();

    /**
     * The Projects.
     */
    @OneToMany(mappedBy = "projectManager")
    private List<Project> projects = new ArrayList<>();

    /**
     * The User address.
     */
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location userAddress;

    /**
     * The External project work.
     */
    @OneToMany(mappedBy = "externalist")
    private List<ExternalistInProject> externalProjectWork = new ArrayList<>();

    /**
     * The Vehicle reports.
     */
    @OneToMany(mappedBy = "driver")
    private List<VehicleReport> vehicleReports = new ArrayList<>();

    /**
     * The Loans.
     */
    @OneToMany(mappedBy = "loaner")
    private List<Loan> loans = new ArrayList<>();

    /**
     * The Company.
     */
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    /**
     * The Responsible persons.
     */
    @ManyToMany
    @JoinTable(
            name = "responsible_persons_construction_diaries",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "responsible_person_id") }
    )
    private Set<ResponsiblePersonInConstructionDiary> responsiblePersons = new HashSet<>();

    /**
     * The User document links.
     */
    @ManyToMany
    @JoinTable(
            name = "user_documents",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "document_id") }
    )
    private Set<Document> userDocumentLinks = new HashSet<>();

    /**
     * The Constructions report.
     */
    @OneToMany(mappedBy = "executor")
    private List<ConstructionReport> constructionsReport = new ArrayList<>();

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return hourRate == user.hourRate && monthSalary == user.monthSalary && id.equals(user.id) && username.equals(user.username) && email.equals(user.email) && password.equals(user.password) && roles.equals(user.roles) && Objects.equals(titleBeforeName, user.titleBeforeName) && Objects.equals(firstname, user.firstname) && lastname.equals(user.lastname) && Objects.equals(titleAfterName, user.titleAfterName) && Objects.equals(bankAccount, user.bankAccount) && dateOfAcceptance.equals(user.dateOfAcceptance) && dateOfBirth.equals(user.dateOfBirth) && birthId.equals(user.birthId);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, username, email, dateOfAcceptance, birthId);
//    }

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
