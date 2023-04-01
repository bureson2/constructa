package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.UserRequest;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.dto.response.UserInputDTO;
import cz.cvut.fel.constructa.model.role.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public UserInputDTO convertToInputDto(User user) {
        return modelMapper.map(user, UserInputDTO.class);
    }

    public User convertToEntity(UserRequest request) throws ParseException {
        return modelMapper.map(request, User.class);
    }
}
