package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.response.ProjectDTO;
import cz.cvut.fel.constructa.model.Project;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectMapper {
    private final ModelMapper modelMapper;
    public ProjectDTO convertToDto(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }
}
