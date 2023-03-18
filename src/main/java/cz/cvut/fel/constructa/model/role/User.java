package cz.cvut.fel.constructa.model.role;

import cz.cvut.fel.constructa.enums.Role;
import cz.cvut.fel.constructa.model.*;
import cz.cvut.fel.constructa.model.loan.Loan;
import cz.cvut.fel.constructa.model.report.ConstructionReport;
import cz.cvut.fel.constructa.model.report.FinanceReport;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.model.report.WorkReport;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Getter
//@Setter
@Entity
@Table(name = "employees")
public class User implements UserDetails {
//    TODO do bakalarky napis o 2 moznostech extends user
//    https://www.youtube.com/watch?v=KxqlJblhzfI&t=3595s 28:00
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
//    @Column(name = "username")
    private String username;

    //    @Column(name = "email", nullable = false)
    @Column(name = "email")
    private String email;
    //    @Column(name = "password", nullable = false)
    @Column(name = "password")
    private String password;

//    @Enumerated(EnumType.STRING)
//    private List<Role> roles;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "title_before_name")
    private String titleBeforeName;

    //    @Column(name = "firstname", nullable = false)
    @Column(name = "firstname")
    private String firstname;

    //    @Column(name = "lastname", nullable = false)
    @Column(name = "lastname")
    private String lastname;

    @Column(name = "title_after_name")
    private String titleAfterName;

    //    @Column(name = "bank_account", nullable = false)
    @Column(name = "bank_account")
    private String bankAccount;

    //    @Column(name = "date_of_acceptance", nullable = false)
    @Column(name = "date_of_acceptance")
    private Date dateOfAcceptance;

    //    @Column(name = "date_of_birth", nullable = false)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    //    @Column(name = "birth_id", nullable = false)
    @Column(name = "birth_id")
    private String birthId;

    @Column(name = "hour_rate")
    private int hourRate;

    @Column(name = "month_salary")
    private int monthSalary;

    @OneToMany(mappedBy = "author")
    private List<Task> createdTasks = new ArrayList<>();

    @OneToMany(mappedBy = "assignee")
    private List<Task> assignedTasks = new ArrayList<>();

    //    todo better name
    @OneToMany(mappedBy = "reportingEmployee")
    private List<WorkReport> attendance = new ArrayList<>();

    @OneToMany(mappedBy = "salariedEmployee")
    private List<FinanceReport> financeReports = new ArrayList<>();

    @OneToOne(mappedBy = "projectManager")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location userAddress;

    @OneToMany(mappedBy = "externalist")
    private List<ExternalistInProject> externalProjectWork = new ArrayList<>();

    @OneToMany(mappedBy = "driver")
    private List<VehicleReport> vehicleReports = new ArrayList<>();

    @OneToMany(mappedBy = "loaner")
    private List<Loan> loans = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany
    @JoinTable(
            name = "responsible_persons_construction_diaries",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "responsible_person_id") }
    )
    private Set<ResponsiblePersonInConstructionDiary> responsiblePersons = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_documents",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "document_id") }
    )
    private Set<Document> userDocumentLinks = new HashSet<>();

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
