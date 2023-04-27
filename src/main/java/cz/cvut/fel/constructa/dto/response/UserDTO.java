package cz.cvut.fel.constructa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type User dto.
 */
@Getter
@Setter
public class UserDTO {
    /**
     * The Id.
     */
    private Long id;
    /**
     * The Username.
     */
    private String username;
    /**
     * The Email.
     */
    private String email;
    /**
     * The Phone.
     */
    private String phone;
    /**
     * The Role.
     */
    private String role;
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
     * The Date of acceptance.
     */
    private Date dateOfAcceptance;
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
     * The Address.
     */
    private LocationDTO address;
}
