package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.TaskRequest;
import cz.cvut.fel.constructa.dto.response.TaskDTO;

import java.text.ParseException;
import java.util.List;

public interface TaskService {
    TaskDTO create(TaskRequest request) throws ParseException;
    TaskDTO getTaskById(Long id);
    List<TaskDTO> getTasks();
    List<TaskDTO> getMyTasks();
    void delete(Long id);
    TaskDTO update(TaskRequest request) throws ParseException;
    TaskDTO changeTaskState(TaskRequest request)

}
