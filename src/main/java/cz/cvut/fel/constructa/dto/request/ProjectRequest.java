package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.enums.ProjectState;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * The type Project request.
 */
@Data
@Builder
public class ProjectRequest {
    /**
     * The Id.
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
    private ProjectState state;
    /**
     * The User id.
     */
    private Long userId;
    /**
     * The Started at.
     */
    private Date startedAt;
    /**
     * The Deadline.
     */
    private Date deadline;
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
