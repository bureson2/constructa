package cz.cvut.fel.constructa.dto.response;

import cz.cvut.fel.constructa.enums.ProjectState;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDTO {
    private Long id;
    private String name;
    private String buldingFacility;
    private String state;
    private UserDTO projectManager;
    private LocationDTO projectAddress;
}
