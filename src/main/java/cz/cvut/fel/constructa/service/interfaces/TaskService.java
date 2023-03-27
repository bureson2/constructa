package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.TaskRequest;
import cz.cvut.fel.constructa.model.Task;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public interface TaskService {
    Task create(TaskRequest request) throws ParseException;
    Optional<Task> getTaskById(Long id);
    List<Task> getTasks();
    List<Task> getTaskByAssigneeId(Long id);

    void delete(Long id);
    Task update(Task updatedTask);


//    Task addAssignee(Long userId, Task task);
}
