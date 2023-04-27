package cz.cvut.fel.constructa.dto.response;

import lombok.Data;

import java.util.Date;

/**
 * The type Work report dto.
 */
@Data
public class WorkReportDTO {
    /**
     * The Id.
     */
    private Long id;
    /**
     * The Time from.
     */
    private Date timeFrom;
    /**
     * The Time to.
     */
    private Date timeTo;
    /**
     * The Minutes.
     */
    private int minutes;
    /**
     * The Reporting employee.
     */
    private UserDTO reportingEmployee;
    /**
     * The Location.
     */
    private LocationDTO location;
    /**
     * The Type.
     */
    private String type;

}
