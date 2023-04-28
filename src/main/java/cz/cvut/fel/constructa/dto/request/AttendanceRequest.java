package cz.cvut.fel.constructa.dto.request;

import lombok.Data;

/**
 * The type Attendance request.
 */
@Data
public class AttendanceRequest {
    /**
     * The Location id.
     */
    private Long locationId;
    /**
     * The Longitude.
     */
    private Double longitude;
    /**
     * The Latitude.
     */
    private Double latitude;
}
