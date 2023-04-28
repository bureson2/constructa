package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.ProjectRequest;
import cz.cvut.fel.constructa.dto.response.ProjectDTO;
import cz.cvut.fel.constructa.model.Project;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * The type Project mapper.
 */
@Component
@RequiredArgsConstructor
public class ProjectMapper {
    /**
     * The Model mapper.
     */
    private final ModelMapper modelMapper;

    /**
     * Convert to dto project dto.
     *
     * @param project the project
     * @return the project dto
     */
    public ProjectDTO convertToDto(Project project) {
        return modelMapper.map(project, ProjectDTO.class);
    }

    /**
     * Convert to entity project.
     *
     * @param request the request
     * @return the project
     * @throws ParseException the parse exception
     */
    public Project convertToEntity(ProjectRequest request) throws ParseException {
        return modelMapper.map(request, Project.class);
    }
}
