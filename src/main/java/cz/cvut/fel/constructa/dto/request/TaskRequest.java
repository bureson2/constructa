package cz.cvut.fel.constructa.dto.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class TaskRequest {
    private Long id;
    private String name;
    private String description;
    private Date timeFrom;
    private Date timeTo;
    private String locationName;
    private Double latitude;
    private Double longitude;
    private String state;
    private Long userId;
}
