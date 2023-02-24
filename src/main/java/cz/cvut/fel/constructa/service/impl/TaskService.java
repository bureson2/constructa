package cz.cvut.fel.constructa.service.impl;

import cz.cvut.fel.constructa.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task create(Task user);
    Optional<Task> getTaskById(Long id);
    List<Task> getTasks();
    void delete(Long id);
    Task update(Long id, Task updatedTask);
}
