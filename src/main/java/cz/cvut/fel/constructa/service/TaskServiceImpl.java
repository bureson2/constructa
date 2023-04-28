package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.TaskRequest;
import cz.cvut.fel.constructa.dto.response.TaskDTO;
import cz.cvut.fel.constructa.enums.TaskState;
import cz.cvut.fel.constructa.mapper.TaskMapper;
import cz.cvut.fel.constructa.model.Task;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.TaskRepository;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.security.AuthenticationFacade;
import cz.cvut.fel.constructa.service.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This class represents a service for managing tasks in a Spring application.
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    /**
     * The Task repository used for accessing task data.
     */
    private final TaskRepository taskDao;
    /**
     * The User repository used for accessing user data.
     */
    private final UserRepository userDao;
    /**
     * The Task mapper used for converting between Task and TaskDTO objects.
     */
    private final TaskMapper taskMapper;
    /**
     * The Authentication facade used for getting the currently authenticated user.
     */
    private final AuthenticationFacade authenticationFacade;

    /**
     * Creates a new task based on the provided TaskRequest object.
     *
     * @param request the TaskRequest object containing the task data.
     * @return the created TaskDTO object.
     * @throws ParseException if there is an error parsing the date in the TaskRequest object.
     */
    @Override
    public TaskDTO create(TaskRequest request) throws ParseException {
        Task createdTask = taskMapper.convertToEntity(request);
        Optional<User> user = userDao.findById(request.getUserId());
        user.ifPresent(createdTask::setAssignee);
        createdTask.setState(TaskState.NEW);
        createdTask.setDateOfCreation(new Date());

        String authorEmail = authenticationFacade.getAuthentication().getName();
        Optional<User> author = userDao.findByEmail(authorEmail);
        author.ifPresent(createdTask::setAuthor);

        createdTask = taskDao.save(createdTask);

        return taskMapper.convertToDto(createdTask);
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id the ID of the task to retrieve.
     * @return the TaskDTO object representing the retrieved task, or null if no task is found.
     */
    @Override
    public TaskDTO getTaskById(Long id) {
//        Optional<Task> task = taskDao.findById(taskId);
        Optional<Task> task = taskDao.findAll().stream().filter(it -> it.getId().equals(id)).findFirst();
        return task.map(taskMapper::convertToDto).orElse(null);
    }

    /**
     * Retrieves a list of all tasks.
     *
     * @return the list of TaskDTO objects representing all tasks.
     */
    @Override
    public List<TaskDTO> getTasks() {
        List<Task> tasks = taskDao.findAll();
        return tasks.stream()
                .map(taskMapper::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of tasks assigned to the currently authenticated user.
     *
     * @return the list of TaskDTO objects representing the user's assigned tasks.
     */
    @Override
    public List<TaskDTO> getMyTasks(){
        String authorEmail = authenticationFacade.getAuthentication().getName();
        Optional<User> assignee = userDao.findByEmail(authorEmail);
        List<Task> tasks = new ArrayList<>();

        if(assignee.isPresent()) {
            tasks = taskDao.findTaskByAssigneeId(assignee.get().getId());
        }

        return tasks.stream()
                .map(taskMapper::convertToDto)
                .collect(Collectors.toList());
    };

    /**
     * Deletes a task by its ID.
     *
     * @param id the ID of the task to delete.
     */
    @Override
    public void delete(Long id) {
        taskDao.deleteById(id);
    }

    /**
     * Updates an existing task based on the provided TaskRequest object.
     *
     * @param request the TaskRequest object containing the updated task data.
     * @return the updated TaskDTO object.
     * @throws ParseException if there is an error parsing the date in the TaskRequest object.
     */
    @Override
    public TaskDTO update(TaskRequest request) throws ParseException {
        Optional<Task> task = taskDao.findAll().stream().filter(it -> it.getId().equals(request.getId())).findFirst();

        User assignee = null;
        User author = null;

        if(task.isPresent()){
            assignee = task.get().getAssignee();
            author = task.get().getAuthor();

            if(!Objects.equals(assignee.getId(), request.getUserId())){
                Optional<User> newAssignee = userDao.findById(request.getUserId());
                if(newAssignee.isPresent()){
                    assignee = newAssignee.get();
                }
            }
        }

        Task updatedTask = taskMapper.convertToEntity(request);
        updatedTask.setAuthor(author);
        updatedTask.setAssignee(assignee);

        updatedTask = taskDao.save(updatedTask);
        return taskMapper.convertToDto(updatedTask);
    }

    /**
     * Change task state task dto.
     *
     * @param request the request
     * @return the task dto
     */
    @Override
    public TaskDTO changeTaskState(TaskRequest request){
        Optional<Task> task = taskDao.findById(request.getId());
        if(task.isPresent()){
            TaskState taskState = TaskState.getEnumByName(request.getState());
            task.get().setState(taskState);
            taskDao.save(task.get());
            return taskMapper.convertToDto(task.get());
        }
        return null;
    }
}
