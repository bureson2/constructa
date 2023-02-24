package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.model.Task;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task create(Task user);
    Optional<Task> getTaskById(Long id);
    List<Task> getTasks();
    void delete(Long id);
    Task update(Task updatedTask);

//    Task addAssignee(Long userId, Task task);
}
