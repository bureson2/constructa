package cz.cvut.fel.constructa.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class WorkReportDTO {
    private Long id;
    private Date timeFrom;
    private Date timeTo;
    private int minutes;
    private UserDTO reportingEmployee;
    private LocationDTO location;
    private String type;

}
