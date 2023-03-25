package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.dto.response.UserInputDTO;
import cz.cvut.fel.constructa.model.role.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

// TODO generics

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final ModelMapper modelMapper;

    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public UserInputDTO convertToInputDto(User user){
        return modelMapper.map(user, UserInputDTO.class);
    }

    // TODO implement request when it will be needed
//    public User convertToEntity(UserRequest userDTO) throws ParseException {
//        return modelMapper.map(userDTO, User.class);
//    }
}
