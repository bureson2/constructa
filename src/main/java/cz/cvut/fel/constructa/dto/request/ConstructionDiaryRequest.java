package cz.cvut.fel.constructa.dto.request;

import cz.cvut.fel.constructa.model.Project;
import lombok.Data;

@Data
public class ConstructionDiaryRequest {
    private Long id;
    private Long projectId;

}
