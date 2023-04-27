package cz.cvut.fel.constructa.dto.response;

import lombok.Getter;
import lombok.Setter;

/**
 * The type User input dto.
 */
@Getter
@Setter
public class UserInputDTO {
    /**
     * The Id.
     */
    private Long id;
    /**
     * The Firstname.
     */
    private String firstname;
    /**
     * The Lastname.
     */
    private String lastname;
}
