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

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskDao;
    private final UserRepository userDao;
    private final TaskMapper taskMapper;
    private final AuthenticationFacade authenticationFacade;

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

    @Override
    public TaskDTO getTaskById(Long taskId) {
//        Optional<Task> task = taskDao.findById(taskId);
        Optional<Task> task = taskDao.findAll().stream().filter(it -> it.getId().equals(taskId)).findFirst();
        return task.map(taskMapper::convertToDto).orElse(null);
    }

    @Override
    public List<TaskDTO> getTasks() {
        List<Task> tasks = taskDao.findAll();
        return tasks.stream()
                .map(taskMapper::convertToDto)
                .collect(Collectors.toList());
    }

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

    @Override
    public void delete(Long id) {
        taskDao.deleteById(id);
    }

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
