package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.TaskRequest;
import cz.cvut.fel.constructa.dto.response.TaskDTO;
import cz.cvut.fel.constructa.model.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;


/**
 * The type Task mapper.
 */
@Component
@RequiredArgsConstructor
public class TaskMapper {
    /**
     * The Model mapper.
     */
    private final ModelMapper modelMapper;

    /**
     * Convert to dto task dto.
     *
     * @param task the task
     * @return the task dto
     */
    public TaskDTO convertToDto(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

    /**
     * Convert to entity task.
     *
     * @param request the request
     * @return the task
     * @throws ParseException the parse exception
     */
    public Task convertToEntity(TaskRequest request) throws ParseException {
        return modelMapper.map(request, Task.class);
    }

}
