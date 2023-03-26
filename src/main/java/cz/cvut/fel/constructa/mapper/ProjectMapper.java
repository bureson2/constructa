package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.ProjectRequest;
import cz.cvut.fel.constructa.dto.request.TaskRequest;
import cz.cvut.fel.constructa.dto.response.ProjectDTO;
import cz.cvut.fel.constructa.model.Project;
import cz.cvut.fel.constructa.model.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class ProjectMapper {
    private final ModelMapper modelMapper;
    public ProjectDTO convertToDto(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }

    public Project convertToEntity(ProjectRequest request) throws ParseException {
        return modelMapper.map(request, Project.class);
    }
}
