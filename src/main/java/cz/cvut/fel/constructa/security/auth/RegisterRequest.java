package cz.cvut.fel.constructa.security.auth;

import cz.cvut.fel.constructa.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * The type Register request.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    /**
     * The Title before name.
     */
    private String titleBeforeName;
    /**
     * The Firstname.
     */
    private String firstname;
    /**
     * The Lastname.
     */
    private String lastname;
    /**
     * The Title after name.
     */
    private String titleAfterName;
    /**
     * The Bank account.
     */
    private String bankAccount;
    /**
     * The Email.
     */
    private String email;
    /**
     * The Password.
     */
    private String password;
    /**
     * The Date of birth.
     */
    private Date dateOfBirth;
    /**
     * The Birth id.
     */
    private String birthId;
    /**
     * The Hour rate.
     */
    private int hourRate;
    /**
     * The Month salary.
     */
    private int monthSalary;
    /**
     * The Phone.
     */
    private String phone;
    /**
     * The Role.
     */
    private Role role;
    /**
     * The Country.
     */
    private String country;
    /**
     * The City.
     */
    private String city;
    /**
     * The Street.
     */
    private String street;
    /**
     * The Descriptive number.
     */
    private String descriptiveNumber;
    /**
     * The Post code.
     */
    private String postCode;
}
