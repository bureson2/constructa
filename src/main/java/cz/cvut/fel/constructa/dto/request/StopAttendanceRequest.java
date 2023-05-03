package cz.cvut.fel.constructa.dto.request;

import lombok.Data;

/**
 * The type Stop attendance request.
 */
@Data
public class StopAttendanceRequest {
    /**
     * The Time.
     */
    int time;

    /**
     * The Longitude.
     */
    private Double longitude;

    /**
     * The Latitude.
     */
    private Double latitude;
}
