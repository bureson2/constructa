package cz.cvut.fel.constructa.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * The type Task request.
 */
@Data
@Builder
public class TaskRequest {
    /**
     * The Id.
     */
    private Long id;
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
    /**
     * The User id.
     */
    private Long userId;
}
