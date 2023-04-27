package cz.cvut.fel.constructa.dto.response;

import lombok.Data;

import java.util.Date;

/**
 * The type Construction report dto.
 */
@Data
public class ConstructionReportDTO {
    /**
     * The Id.
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
     * The Executor.
     */
    private UserDTO executor;
    /**
     * The State.
     */
    private String state;
}
