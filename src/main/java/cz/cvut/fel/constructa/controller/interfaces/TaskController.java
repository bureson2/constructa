package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.response.TaskDTO;
import cz.cvut.fel.constructa.model.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskController {
    ResponseEntity<List<TaskDTO>> getTasks();
    ResponseEntity<TaskDTO> getTask(Long taskId);
    ResponseEntity<TaskDTO> createTask(Task newTask);
    ResponseEntity<TaskDTO> changeAssigne(Long taskId, Long userId);
    ResponseEntity<Void> deleteTask(Long taskId);
}
