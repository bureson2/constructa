package cz.cvut.fel.constructa.dto.response;

import lombok.Data;

@Data
public class ConstructionReportDTO {
    private Long id;
    private String taskName;
    private String note;
    private String weather;
    private UserDTO executor;
    private String state;
}
