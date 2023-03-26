package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.ProjectRequest;
import cz.cvut.fel.constructa.model.Project;
import cz.cvut.fel.constructa.model.Task;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project create(ProjectRequest request) throws ParseException;
    Optional<Project> getProjectById(Long id);
    List<Project> getProjects();
    void delete(Long id);
    Project update(Project updatedProject);
}
