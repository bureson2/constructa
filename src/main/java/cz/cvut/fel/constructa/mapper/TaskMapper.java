package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.TaskRequest;
import cz.cvut.fel.constructa.dto.response.TaskDTO;
import cz.cvut.fel.constructa.model.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

// TODO generics

@Component
public class TaskMapper {
    @Autowired
    private ModelMapper modelMapper;

    public TaskDTO convertToDto(Task task) {
        return modelMapper.map(task, TaskDTO.class);
    }

}
