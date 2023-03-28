package cz.cvut.fel.constructa.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class ConstructionReportDTO {
    private Long id;
    private String taskName;
    private String note;
    private String weather;
    private Date date;
    private UserDTO executor;
    private String state;
}
