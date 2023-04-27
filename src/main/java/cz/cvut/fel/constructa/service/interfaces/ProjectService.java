package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.ProjectRequest;
import cz.cvut.fel.constructa.dto.response.ProjectDTO;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Project service.
 */
public interface ProjectService {
    /**
     * Create project dto.
     *
     * @param request the request
     * @return the project dto
     * @throws ParseException the parse exception
     */
    ProjectDTO create(ProjectRequest request) throws ParseException;

    /**
     * Gets project by id.
     *
     * @param id the id
     * @return the project by id
     */
    ProjectDTO getProjectById(Long id);

    /**
     * Gets projects.
     *
     * @return the projects
     */
    List<ProjectDTO> getProjects();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);

    /**
     * Update project dto.
     *
     * @param request the request
     * @return the project dto
     * @throws ParseException the parse exception
     */
    ProjectDTO update(ProjectRequest request) throws ParseException;
}
