package cz.cvut.fel.constructa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TaskDTO {
    private Long id;
    private Date dateOfCreation;
    private String name;
    private String description;
    private Date timeFrom;
    private Date timeTo;
    private UserDTO assignee;
    private UserDTO author;
    private String locationName;
    private Double latitude;
    private Double longitude;
}
