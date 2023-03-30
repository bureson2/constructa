package cz.cvut.fel.constructa.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ProjectDTO {
    private Long id;
    private String name;
    private String buldingFacility;
    private String state;
    private UserDTO projectManager;
    private LocationDTO projectAddress;
    private Date startedAt;
    private Date deadline;
}
