package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.ProjectController;
import cz.cvut.fel.constructa.dto.request.ProjectRequest;
import cz.cvut.fel.constructa.dto.response.ProjectDTO;
import cz.cvut.fel.constructa.security.AuthenticationFacade;
import cz.cvut.fel.constructa.service.interfaces.ProjectService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * The type Project controller.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/projects")
@SecurityRequirement(name="bearerAuth")
@Tag(name = "Projects")
@RequiredArgsConstructor
public class ProjectControllerImpl implements ProjectController {
    /**
     * The Authentication facade.
     */
    private final AuthenticationFacade authenticationFacade;

    /**
     * The Project service.
     */
    private final ProjectService projectService;

    /**
     * Has permission boolean.
     *
     * @param requiredRoles the required roles
     * @return the boolean
     */
    private boolean hasPermission(List<GrantedAuthority> requiredRoles){
        Authentication authentication = authenticationFacade.getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream().anyMatch(requiredRoles::contains);
    }

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
        List<GrantedAuthority> requiredRoles = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_MANAGER")
        );

        if (hasPermission(requiredRoles)) {
            return ResponseEntity.ok().body(projectService.create(request));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
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
        List<GrantedAuthority> requiredRoles = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_MANAGER")
        );

        if (hasPermission(requiredRoles)) {
            return ResponseEntity.ok().body(projectService.update(request));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
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
        List<GrantedAuthority> requiredRoles = Arrays.asList(
                new SimpleGrantedAuthority("ROLE_ADMIN"),
                new SimpleGrantedAuthority("ROLE_MANAGER")
        );

        if (hasPermission(requiredRoles)) {
            projectService.delete(projectId);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}
