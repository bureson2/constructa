package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.TaskRequest;
import cz.cvut.fel.constructa.dto.response.TaskDTO;
import cz.cvut.fel.constructa.model.Task;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.List;

public interface TaskController {
    ResponseEntity<List<TaskDTO>> getTasks();
    ResponseEntity<TaskDTO> getTask(Long taskId);
    ResponseEntity<TaskDTO> createTask(TaskRequest request) throws ParseException;
//    ResponseEntity<TaskDTO> changeAssigne(Long taskId, Long userId);
    ResponseEntity<Void> deleteTask(Long taskId);
}
