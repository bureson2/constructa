package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.UserRequestDTO;
import cz.cvut.fel.constructa.dto.response.UserResponseDTO;
import cz.cvut.fel.constructa.model.role.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

// TODO generics

@Component
public class UserMapper {
    @Autowired
    private ModelMapper modelMapper;

    public UserResponseDTO convertToDto(User user) {
        return modelMapper.map(user, UserResponseDTO.class);
    }

    // TODO implement request when it will be needed
    public User convertToEntity(UserRequestDTO userDTO) throws ParseException {
        return modelMapper.map(userDTO, User.class);
    }
}
