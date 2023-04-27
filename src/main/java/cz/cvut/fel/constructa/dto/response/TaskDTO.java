package cz.cvut.fel.constructa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * The type Task dto.
 */
@Getter
@Setter
public class TaskDTO {
    /**
     * The Id.
     */
    private Long id;
    /**
     * The Date of creation.
     */
    private Date dateOfCreation;
    /**
     * The Name.
     */
    private String name;
    /**
     * The Description.
     */
    private String description;
    /**
     * The Time from.
     */
    private Date timeFrom;
    /**
     * The Time to.
     */
    private Date timeTo;
    /**
     * The Assignee.
     */
    private UserDTO assignee;
    /**
     * The Author.
     */
    private UserDTO author;
    /**
     * The Location name.
     */
    private String locationName;
    /**
     * The Latitude.
     */
    private Double latitude;
    /**
     * The Longitude.
     */
    private Double longitude;
    /**
     * The State.
     */
    private String state;
}
