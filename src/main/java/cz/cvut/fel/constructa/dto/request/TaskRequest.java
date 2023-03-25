package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.dto.response.UserDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskRequest {
    private Long id;
    private String name;
    private String description;
    private Date timeFrom;
    private Date timeTo;
    private UserDTO assignee;
    private String locationName;
    private Double latitude;
    private Double longitude;
    private String state;
    private Long UserId;
}
