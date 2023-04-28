package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.enums.ConstructionDiaryReportState;
import lombok.Data;

import java.util.Date;

/**
 * The type Construction report request.
 */
@Data
public class ConstructionReportRequest {
    /**
     * The ID.
     */
    private Long id;
    /**
     * The Task name.
     */
    private String taskName;
    /**
     * The Note.
     */
    private String note;
    /**
     * The Weather.
     */
    private String weather;
    /**
     * The Date.
     */
    private Date date;
    /**
     * The Executor id.
     */
    private Long executorId;
    /**
     * The State.
     */
    private ConstructionDiaryReportState state;
    /**
     * The Project id.
     */
    private Long projectId;
}
