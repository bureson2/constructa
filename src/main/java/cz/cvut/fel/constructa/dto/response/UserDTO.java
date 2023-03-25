package cz.cvut.fel.constructa.dto.response;

import cz.cvut.fel.constructa.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private List<Role> roles;
    private String titleBeforeName;
    private String firstname;
    private String lastname;
    private String titleAfterName;
    private String bankAccount;
    private Date dateOfAcceptance;
    private Date dateOfBirth;
    private String birthId;
    private int hourRate;
    private int monthSalary;





}
