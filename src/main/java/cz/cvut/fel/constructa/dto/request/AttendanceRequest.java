package cz.cvut.fel.constructa.dto.request;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

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
