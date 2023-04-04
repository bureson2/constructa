package cz.cvut.fel.constructa.security.auth;

import cz.cvut.fel.constructa.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String titleBeforeName;
    private String firstname;
    private String lastname;
    private String titleAfterName;
    private String bankAccount;
    private String email;
    private String password;
    private Date dateOfBirth;
    private String birthId;
    private int hourRate;
    private int monthSalary;
    private String phone;
    private Role role;
    private String country;
    private String city;
    private String street;
    private String descriptiveNumber;
    private String postCode;
}
