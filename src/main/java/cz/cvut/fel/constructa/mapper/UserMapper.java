package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.UserRequest;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.dto.response.UserInputDTO;
import cz.cvut.fel.constructa.model.role.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * The type User mapper.
 */
@Component
@RequiredArgsConstructor
public class UserMapper {
    /**
     * The Model mapper.
     */
    private final ModelMapper modelMapper;

    /**
     * Convert to dto user dto.
     *
     * @param user the user
     * @return the user dto
     */
    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    /**
     * Convert to input dto user input dto.
     *
     * @param user the user
     * @return the user input dto
     */
    public UserInputDTO convertToInputDto(User user) {
        return modelMapper.map(user, UserInputDTO.class);
    }

    /**
     * Convert to entity user.
     *
     * @param request the request
     * @return the user
     * @throws ParseException the parse exception
     */
    public User convertToEntity(UserRequest request) throws ParseException {
        return modelMapper.map(request, User.class);
    }
}
