package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.ProjectController;
import cz.cvut.fel.constructa.dto.request.ProjectRequest;
import cz.cvut.fel.constructa.dto.response.ProjectDTO;
import cz.cvut.fel.constructa.service.interfaces.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * The type Project controller.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectControllerImpl implements ProjectController {
    /**
     * The Project service.
     */
    private final ProjectService projectService;

    /**
     * Gets projects.
     *
     * @return the projects
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjectDTO>> getProjects() {
        return ResponseEntity.ok().body(projectService.getProjects());
    }

    /**
     * Gets projects.
     *
     * @param projectId the project id
     * @return the projects
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDTO> getProjects(@PathVariable Long projectId) {
        ProjectDTO project = projectService.getProjectById(projectId);
        if (project != null) {
            return ResponseEntity.ok().body(project);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Create project response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectRequest request) throws ParseException {
        return ResponseEntity.ok().body(projectService.create(request));
    }

    /**
     * Edit project response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDTO> editProject(@RequestBody ProjectRequest request) throws ParseException {
        return ResponseEntity.ok().body(projectService.update(request));
    }

    /**
     * Delete project response entity.
     *
     * @param projectId the project id
     * @return the response entity
     */
    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.delete(projectId);
        return ResponseEntity.noContent().build();
    }
}
