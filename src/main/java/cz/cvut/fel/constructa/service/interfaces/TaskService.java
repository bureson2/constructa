package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.TaskRequest;
import cz.cvut.fel.constructa.dto.response.TaskDTO;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Task service.
 */
public interface TaskService {
    /**
     * Create task dto.
     *
     * @param request the request
     * @return the task dto
     * @throws ParseException the parse exception
     */
    TaskDTO create(TaskRequest request) throws ParseException;

    /**
     * Gets task by id.
     *
     * @param id the id
     * @return the task by id
     */
    TaskDTO getTaskById(Long id);

    /**
     * Gets tasks.
     *
     * @return the tasks
     */
    List<TaskDTO> getTasks();

    /**
     * Gets my tasks.
     *
     * @return the my tasks
     */
    List<TaskDTO> getMyTasks();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);

    /**
     * Update task dto.
     *
     * @param request the request
     * @return the task dto
     * @throws ParseException the parse exception
     */
    TaskDTO update(TaskRequest request) throws ParseException;

    /**
     * Change task state task dto.
     *
     * @param request the request
     * @return the task dto
     */
    TaskDTO changeTaskState(TaskRequest request);

}
