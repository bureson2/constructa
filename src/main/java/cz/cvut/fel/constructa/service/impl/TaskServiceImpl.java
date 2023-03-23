package cz.cvut.fel.constructa.service.impl;

import cz.cvut.fel.constructa.enums.TaskState;
import cz.cvut.fel.constructa.model.Task;
import cz.cvut.fel.constructa.repository.TaskRepository;
import cz.cvut.fel.constructa.service.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskDao;

    @Override
    public Task create(Task createdTask) {
        createdTask.setState(TaskState.NEW);
        return taskDao.save(createdTask);
    }

    @Override
    public Optional<Task> getTaskById(Long taskId) {
//        Optional<Task> task = taskDao.findById(taskId);
        return taskDao.findAll().stream().filter(it->it.getId().equals(taskId)).findFirst();
    }

    @Override
    public List<Task> getTasks() {
        return taskDao.findAll();
    }

    @Override
    public void delete(Long id) {
        taskDao.deleteById(id);
    }

    @Override
    public Task update(Task updatedTask) {
        return taskDao.save(updatedTask);
    }

//    @Override
//    public Task addAssignee(Long userId, Task task) {
//
//    }
}
