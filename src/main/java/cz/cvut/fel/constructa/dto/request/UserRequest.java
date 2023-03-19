package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserRequest {
    private Long id;
    private String username;
    private String email;
    private List<Role> roles;
    private String titleBeforeName;
    private String firstname;
    private String lastname;
    private String titleAfterName;
}
