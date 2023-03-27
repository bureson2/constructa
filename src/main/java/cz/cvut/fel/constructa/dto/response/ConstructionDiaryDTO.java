package cz.cvut.fel.constructa.dto.response;

import cz.cvut.fel.constructa.model.Project;
import lombok.Data;

@Data
public class ConstructionDiaryDTO {
    private Long id;
    private ProjectDTO constructionDiaryProject;

}
