package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.enums.ProjectState;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProjectRequest {
    private String name;
    private String buldingFacility;
    private ProjectState state;
    private Long userId;
    private Date startedAt;
    private Date deadline;
    private String country;
    private String city;
    private String street;
    private String descriptiveNumber;
    private String postCode;
}
