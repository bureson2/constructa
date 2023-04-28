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
     * Creates a new project with the given parameters.
     *
     * @param request the request containing the data for the new project
     * @return the created project DTO
     * @throws ParseException if there is an error parsing the date in the request
     */
    ProjectDTO create(ProjectRequest request) throws ParseException;

    /**
     * Gets the project with the given ID.
     *
     * @param id the ID of the project to retrieve
     * @return the project DTO with the given ID, or null if no project with that ID exists
     */
    ProjectDTO getProjectById(Long id);

    /**
     * Returns a list of all projects in the database, mapped to DTO objects.
     * Note that this method returns all projects, which could potentially be a large amount of data.
     * Consider using pagination or filtering to limit the amount of data returned.
     *
     * @return a list of all projects
     */
    List<ProjectDTO> getProjects();


    /**
     * Deletes a project.
     *
     * @param id the project ID
     */
    void delete(Long id);

    /**
     * Updates an existing project.
     *
     * @param request the project request
     * @return the updated project DTO
     * @throws ParseException if there's a problem parsing the request
     */
    ProjectDTO update(ProjectRequest request) throws ParseException;
}
