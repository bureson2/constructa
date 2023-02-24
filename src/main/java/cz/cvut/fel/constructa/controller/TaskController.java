package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.dto.response.TaskResponseDTO;
import cz.cvut.fel.constructa.mapper.TaskMapper;
import cz.cvut.fel.constructa.model.Task;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.service.interfaces.TaskService;
import cz.cvut.fel.constructa.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskMapper taskMapper;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskResponseDTO>> getTasks() {
        List<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok().body(
                tasks.stream()
                        .map(task -> taskMapper.convertToDto(task))
                        .collect(Collectors.toList())
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskResponseDTO> getTask(@PathVariable Long taskId) {
        Optional<Task> taskToReturn = taskService.getTaskById(taskId);
        return taskToReturn.map(task -> ResponseEntity.ok().body(
                taskMapper.convertToDto(task)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody Task newTask) {
        Task createdTask = taskService.create(newTask);
        return ResponseEntity.ok().body(
                taskMapper.convertToDto(createdTask)
        );
    }

    // TODO move to service
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value = "/{taskId}/user/{userId}")
    public ResponseEntity<TaskResponseDTO> changeAssigne(@PathVariable Long taskId, @PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        Optional<Task> task = taskService.getTaskById(taskId);
        if (user.isPresent() && task.isPresent()) {
            task.get().setAssignee(user.get());
            task.get().setAuthor(user.get());
            user.get().getAssignedTasks().add(task.get());
            taskService.update(task.get());
            userService.update(user.get());
            return ResponseEntity.ok().body(
                    taskMapper.convertToDto(task.get())
            );
        }
        return ResponseEntity.notFound().build();

    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }
}
