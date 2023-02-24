package cz.cvut.fel.constructa.dto.response;

import org.springframework.data.geo.Point;

import java.util.Date;

public class TaskResponseDTO {
    private Long id;
    private Date dateOfCreation;
    private String name;
    private String description;
    private String locationName;
    private Point locationPosition;
    private Date timeFrom;
    private Date timeTo;

//    TODO
//    private User author;
//    private User assignee;

}
