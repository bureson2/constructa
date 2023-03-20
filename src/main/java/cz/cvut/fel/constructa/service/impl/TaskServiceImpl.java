package cz.cvut.fel.constructa.service.impl;

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
        return taskDao.save(createdTask);
    }

    @Override
    public Optional<Task> getTaskById(Long taskId) {
        return taskDao.findById(taskId);
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
