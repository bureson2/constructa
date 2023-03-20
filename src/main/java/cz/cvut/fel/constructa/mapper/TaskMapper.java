package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.response.TaskDTO;
import cz.cvut.fel.constructa.model.Task;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TaskMapper {
    private final ModelMapper modelMapper;

    public TaskDTO convertToDto(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

}
