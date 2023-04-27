package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.TaskRequest;
import cz.cvut.fel.constructa.dto.response.TaskDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Task controller.
 */
@RequestMapping("/api/v1/tasks")
public interface TaskController {
    /**
     * Gets tasks.
     *
     * @return the tasks
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TaskDTO>> getTasks();

    /**
     * Gets user tasks.
     *
     * @return the user tasks
     */
    @GetMapping(value = "/my", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<TaskDTO>> getUserTasks();

    /**
     * Gets task.
     *
     * @param taskId the task id
     * @return the task
     */
    @GetMapping(value = "/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TaskDTO> getTask(@PathVariable Long taskId);

    /**
     * Create task response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TaskDTO> createTask(@RequestBody TaskRequest request) throws ParseException;

    /**
     * Edit task response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TaskDTO> editTask(@RequestBody TaskRequest request) throws ParseException;

    /**
     * Edit task state response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping(value = "/state",consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<TaskDTO> editTaskState(@RequestBody TaskRequest request);

    /**
     * Delete task response entity.
     *
     * @param taskId the task id
     * @return the response entity
     */
    @DeleteMapping(value = "/{taskId}")
    ResponseEntity<Void> deleteTask(@PathVariable Long taskId);
}
