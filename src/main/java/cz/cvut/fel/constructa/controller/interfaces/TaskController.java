package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.TaskRequest;
import cz.cvut.fel.constructa.dto.response.TaskDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RequestMapping("/api/v1/tasks")
public interface TaskController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TaskDTO>> getTasks();

    @GetMapping(value = "/my", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TaskDTO>> getUserTasks();

    @GetMapping(value = "/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TaskDTO> getTask(@PathVariable Long taskId);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TaskDTO> createTask(@RequestBody TaskRequest request) throws ParseException;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TaskDTO> editTask(@RequestBody TaskRequest request) throws ParseException;

    @PostMapping(value = "/state",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TaskDTO> editTaskState(@RequestBody TaskRequest request);

    @DeleteMapping(value = "/{taskId}")
    ResponseEntity<Void> deleteTask(@PathVariable Long taskId);
}
