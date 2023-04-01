package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.ProjectRequest;
import cz.cvut.fel.constructa.dto.response.ProjectDTO;

import java.text.ParseException;
import java.util.List;

public interface ProjectService {
    ProjectDTO create(ProjectRequest request) throws ParseException;
    ProjectDTO getProjectById(Long id);
    List<ProjectDTO> getProjects();
    void delete(Long id);
    ProjectDTO update(ProjectRequest request) throws ParseException;
}
