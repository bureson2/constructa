package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.response.TaskResponseDTO;
import cz.cvut.fel.constructa.model.Task;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TaskController {
    ResponseEntity<List<TaskResponseDTO>> getTasks();
    ResponseEntity<TaskResponseDTO> getTask(Long taskId);
    ResponseEntity<TaskResponseDTO> createTask(Task newTask);
    ResponseEntity<TaskResponseDTO> changeAssigne(Long taskId, Long userId);
    ResponseEntity<Void> deleteTask(Long taskId);
}
