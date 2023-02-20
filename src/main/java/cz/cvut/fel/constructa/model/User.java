package cz.cvut.fel.constructa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    //    @Column(name = "email", nullable = false)
    @Column(name = "email")
    private String email;
//    @Column(name = "password", nullable = false)
    @Column(name = "password")
    private String password;

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

}
