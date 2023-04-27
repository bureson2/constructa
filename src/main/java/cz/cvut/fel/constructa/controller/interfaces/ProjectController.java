package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.ProjectRequest;
import cz.cvut.fel.constructa.dto.response.ProjectDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Project controller.
 */
@RequestMapping("/api/v1/projects")
public interface ProjectController {
    /**
     * Gets projects.
     *
     * @return the projects
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProjectDTO>> getProjects();

    /**
     * Gets projects.
     *
     * @param projectId the project id
     * @return the projects
     */
    @GetMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProjectDTO> getProjects(@PathVariable Long projectId);

    /**
     * Create project response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectRequest request) throws ParseException;

    /**
     * Edit project response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProjectDTO> editProject(@RequestBody ProjectRequest request) throws ParseException;

    /**
     * Delete project response entity.
     *
     * @param projectId the project id
     * @return the response entity
     */
    @DeleteMapping(value = "/{projectId}")
    ResponseEntity<Void> deleteProject(@PathVariable Long projectId);
}
