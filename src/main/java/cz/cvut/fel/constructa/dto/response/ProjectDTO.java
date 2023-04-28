package cz.cvut.fel.constructa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type Project dto.
 */
@Getter
@Setter
public class ProjectDTO {
    /**
     * The ID.
     */
    private Long id;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Bulding facility.
     */
    private String buldingFacility;
    /**
     * The State.
     */
    private String state;
    /**
     * The Project manager.
     */
    private UserDTO projectManager;
    /**
     * The Project address.
     */
    private LocationDTO projectAddress;
    /**
     * The Started at.
     */
    private Date startedAt;
    /**
     * The Deadline.
     */
    private Date deadline;
}
