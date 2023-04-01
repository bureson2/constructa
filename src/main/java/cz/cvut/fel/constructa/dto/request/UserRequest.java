package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UserRequest {
    private Long id;
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
