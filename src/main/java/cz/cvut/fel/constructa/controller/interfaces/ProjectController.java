package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.ProjectRequest;
import cz.cvut.fel.constructa.dto.response.ProjectDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RequestMapping("/api/v1/projects")
public interface ProjectController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ProjectDTO>> getProjects();

    @GetMapping(value = "/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProjectDTO> getProjects(@PathVariable Long projectId);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProjectDTO> createProject(@RequestBody ProjectRequest request) throws ParseException;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ProjectDTO> editProject(@RequestBody ProjectRequest request) throws ParseException;

    @DeleteMapping(value = "/{projectId}")
    ResponseEntity<Void> deleteProject(@PathVariable Long projectId);
}
