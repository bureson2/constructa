package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.UserRequest;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.dto.response.UserInputDTO;

import java.text.ParseException;
import java.util.List;

public interface UserService {
    UserDTO getUserById(Long id);
    List<UserDTO> getUsers();
    List<UserInputDTO> getInputUsers();
    void delete(Long id);
    UserDTO update(UserRequest request) throws ParseException;

}
