package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.enums.ConstructionDiaryReportState;
import cz.cvut.fel.constructa.model.role.User;
import lombok.Data;

import java.util.Date;

@Data
public class ConstructionReportRequest {
    private Long id;
    private String taskName;
    private String note;
    private String weather;
    private Date date;
    private Long executorId;
    private ConstructionDiaryReportState state;
    private Long projectId;
}
