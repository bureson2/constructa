package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.enums.WorkReportType;
import lombok.Data;

import java.util.Date;

@Data
public class WorkReportRequest {
    private Long id;
    private Date timeFrom;
    private Date timeTo;
    //    todo
//    private LocationDTO location;
    private WorkReportType type;
    private Long userId;
}
