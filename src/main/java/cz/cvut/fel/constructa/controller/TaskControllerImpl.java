package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.TaskController;
import cz.cvut.fel.constructa.dto.request.TaskRequest;
import cz.cvut.fel.constructa.dto.response.TaskDTO;
import cz.cvut.fel.constructa.service.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * The type Task controller.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskControllerImpl implements TaskController {
    /**
     * The Task service.
     */
    private final TaskService taskService;

    /**
     * Gets tasks.
     *
     * @return the tasks
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskDTO>> getTasks() {
        return ResponseEntity.ok().body(
                taskService.getTasks()
        );
    }

    /**
     * Gets user tasks.
     *
     * @return the user tasks
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/my", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskDTO>> getUserTasks() {
        return ResponseEntity.ok().body(
                taskService.getMyTasks()
        );
    }

    /**
     * Gets task.
     *
     * @param taskId the task id
     * @return the task
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long taskId) {
        TaskDTO task = taskService.getTaskById(taskId);
        if (task != null) {
            return ResponseEntity.ok().body(task);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Create task response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> createTask(@RequestBody TaskRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                taskService.create(request)
        );
    }

    /**
     * Edit task response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> editTask(@RequestBody TaskRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                taskService.update(request)
        );
    }

    /**
     * Edit task state response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PostMapping(value = "/state",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> editTaskState(@RequestBody TaskRequest request) {
        return ResponseEntity.ok().body(
                taskService.changeTaskState(request)
        );
    }

    /**
     * Delete task response entity.
     *
     * @param taskId the task id
     * @return the response entity
     */
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }
}
