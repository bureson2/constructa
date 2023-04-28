package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.enums.WorkReportType;
import lombok.Data;

import java.util.Date;

/**
 * The type Work report request.
 */
@Data
public class WorkReportRequest {
    /**
     * The ID.
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
     * The Type.
     */
    private WorkReportType type;
    /**
     * The User id.
     */
    private Long userId;
}
