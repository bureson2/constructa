package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.enums.Role;
import cz.cvut.fel.constructa.model.Task;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.service.impl.TaskService;
import cz.cvut.fel.constructa.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getTasks() {
        return taskService.getTasks();
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/tasks /{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Task> getTask(@PathVariable Long taskId) {
        return taskService.getTaskById(taskId);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/task/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Task createTask(@RequestBody Task newTask) {
        return taskService.create(newTask);
    }

//    TODO
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value = "/task/{taskId}/user/userId")
    public Optional<User> changeAssigne(@PathVariable Long taskId, @PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId);
        Optional<Task> task = taskService.getTaskById(taskId);
        if(user.get() != null && task.get() != null){
            task.get().setAssignee(user.get());
            task.get().setAuthor(user.get());
            return userService.update(userId, user.get());
        }
        return null;
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/task/{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.delete(taskId);
    }
}
