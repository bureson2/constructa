package cz.cvut.fel.constructa.dto.response;

import cz.cvut.fel.constructa.enums.WorkReportType;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.role.User;
import lombok.Data;

import java.util.Date;

@Data
public class WorkReportDTO {
    private Long id;
    private Date timeFrom;
    private Date timeTo;
    private UserDTO reportingEmployee;
    private LocationDTO location;
    private String type;

}
