package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.ProjectController;
import cz.cvut.fel.constructa.dto.response.ProjectDTO;
import cz.cvut.fel.constructa.mapper.ProjectMapper;
import cz.cvut.fel.constructa.model.Project;
import cz.cvut.fel.constructa.service.interfaces.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectControllerImpl implements ProjectController {
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;


    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProjectDTO>> getProjects() {
        List<Project> projects = projectService.getProjects();
        return ResponseEntity.ok().body(
                projects.stream()
                        .map(projectMapper::convertToDto)
                        .collect(Collectors.toList())
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDTO> getProjects(@PathVariable Long projectId) {
        Optional<Project> projectsToReturn = projectService.getProjectById(projectId);
        return projectsToReturn.map(task -> ResponseEntity.ok().body(
                projectMapper.convertToDto(task)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDTO> createProject(@RequestBody Project newProject) {
        Project createdProject = projectService.create(newProject);
        return ResponseEntity.ok().body(
                projectMapper.convertToDto(createdProject)
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectDTO> editProject(@RequestBody Project updatedProject){
        Project projectToReturn = projectService.update(updatedProject);
        return ResponseEntity.ok().body(
                projectMapper.convertToDto(projectToReturn));
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{projectId}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long projectId) {
        projectService.delete(projectId);
        return ResponseEntity.noContent().build();
    }
}
