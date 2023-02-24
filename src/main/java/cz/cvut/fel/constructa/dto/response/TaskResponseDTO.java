package cz.cvut.fel.constructa.dto.response;

import cz.cvut.fel.constructa.model.role.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.geo.Point;

import java.util.Date;

@Getter
@Setter
public class TaskResponseDTO {
    private Long id;
    private Date dateOfCreation;
    private String name;
    private String description;
    private String locationName;
    private Point locationPosition;
    private Date timeFrom;
    private Date timeTo;
    private UserResponseDTO assignee;

    private UserResponseDTO author;

}
