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
     * Creates a new task based on the provided TaskRequest object.
     *
     * @param request the TaskRequest object containing the task data.
     * @return the created TaskDTO object.
     * @throws ParseException if there is an error parsing the date in the TaskRequest object.
     */
    TaskDTO create(TaskRequest request) throws ParseException;

    /**
     * Retrieves a task by its ID.
     *
     * @param id the ID of the task to retrieve.
     * @return the TaskDTO object representing the retrieved task, or null if no task is found.
     */
    TaskDTO getTaskById(Long id);

    /**
     * Retrieves a list of all tasks.
     *
     * @return the list of TaskDTO objects representing all tasks.
     */
    List<TaskDTO> getTasks();

    /**
     * Retrieves a list of tasks assigned to the currently authenticated user.
     *
     * @return the list of TaskDTO objects representing the user's assigned tasks.
     */
    List<TaskDTO> getMyTasks();

    /**
     * Deletes a task by its ID.
     *
     * @param id the ID of the task to delete.
     */
    void delete(Long id);

    /**
     * Updates an existing task based on the provided TaskRequest object.
     *
     * @param request the TaskRequest object containing the updated task data.
     * @return the updated TaskDTO object.
     * @throws ParseException if there is an error parsing the date in the TaskRequest object.
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
