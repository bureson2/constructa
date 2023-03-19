package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.model.role.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {
    ResponseEntity<List<UserDTO>> getUsers();

    ResponseEntity<UserDTO> getUser(Long userId);

    public ResponseEntity<UserDTO> createUser(User newUser);

//    TODO update
    ResponseEntity<UserDTO> updateRole(String role, Long userId);

    ResponseEntity<Void> deleteUser(Long id);
}
