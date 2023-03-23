package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.TaskController;
import cz.cvut.fel.constructa.dto.response.TaskDTO;
import cz.cvut.fel.constructa.mapper.TaskMapper;
import cz.cvut.fel.constructa.model.Task;
import cz.cvut.fel.constructa.service.impl.TaskServiceImpl;
import cz.cvut.fel.constructa.service.interfaces.TaskService;
import cz.cvut.fel.constructa.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO response body everywhere

@CrossOrigin
@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
public class TaskControllerImpl implements TaskController {
    private final TaskService taskService;
//    TODO concrete security for owners
    private final UserService userService;
    private final TaskMapper taskMapper;

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TaskDTO>> getTasks() {
        List<Task> tasks = taskService.getTasks();
        return ResponseEntity.ok().body(
                tasks.stream()
                        .map(taskMapper::convertToDto)
                        .collect(Collectors.toList())
        );
    }
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> getTask(@PathVariable Long taskId) {
        Optional<Task> taskToReturn = taskService.getTaskById(taskId);
        return taskToReturn.map(task -> ResponseEntity.ok().body(
                taskMapper.convertToDto(task)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> createTask(@RequestBody Task newTask) {
        Task createdTask = taskService.create(newTask);
        return ResponseEntity.ok().body(
                taskMapper.convertToDto(createdTask)
        );
    }

    // TODO move to service
//
//    @PutMapping(value = "/{taskId}/user/{userId}")
//    public ResponseEntity<TaskDTO> changeAssigne(@PathVariable Long taskId, @PathVariable Long userId) {
//        Optional<User> user = userService.getUserById(userId);
//        Optional<Task> task = taskService.getTaskById(taskId);
//        if (user.isPresent() && task.isPresent()) {
//            task.get().setAssignee(user.get());
//            task.get().setAuthor(user.get());
//            user.get().getAssignedTasks().add(task.get());
//            taskService.update(task.get());
//            userService.update(user.get());
//            return ResponseEntity.ok().body(
//                    taskMapper.convertToDto(task.get())
//            );
//        }
//        return ResponseEntity.notFound().build();
//    }

// TODO TaskRequest better than updatedTask
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TaskDTO> editTask(@RequestBody Task updatedTask){
        Task taskToReturn = taskService.update(updatedTask);
        return ResponseEntity.ok().body(
                taskMapper.convertToDto(taskToReturn));
    }

    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        taskService.delete(taskId);
        return ResponseEntity.noContent().build();
    }
}
