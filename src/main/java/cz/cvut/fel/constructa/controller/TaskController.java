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
    public List<TaskResponseDTO> getTasks() {
        List<Task> tasks = taskService.getTasks();
        return tasks.stream()
                .map(task -> taskMapper.convertToDto(task))
                .collect(Collectors.toList());
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TaskResponseDTO getTask(@PathVariable Long taskId) {
        Optional<Task> taskToReturn = taskService.getTaskById(taskId);
        return taskToReturn.map(task -> taskMapper.convertToDto(task)).orElse(null);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public TaskResponseDTO createTask(@RequestBody Task newTask) {
        Task createdTask = taskService.create(newTask);
//        return createdTask;
        return taskMapper.convertToDto(createdTask);
    }

    // TODO move to service
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value = "/{taskId}/user/{userId}")
    public TaskResponseDTO changeAssigne(@PathVariable Long taskId, @PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        Optional<Task> task = taskService.getTaskById(taskId);
        if(user.get() != null && task.get() != null){
            task.get().setAssignee(user.get());
            task.get().setAuthor(user.get());
            user.get().getAssignedTasks().add(task.get());
            taskService.update(task.get());
            userService.update(user.get());
        }
        return taskMapper.convertToDto(task.get());
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.delete(taskId);
    }
}
