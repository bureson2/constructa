package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.response.UserResponseDTO;
import cz.cvut.fel.constructa.model.role.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserController {
    ResponseEntity<List<UserResponseDTO>> getUsers();

    ResponseEntity<UserResponseDTO> getUser(Long userId);

    public ResponseEntity<UserResponseDTO> createUser(User newUser);

//    TODO update
    ResponseEntity<UserResponseDTO> updateRole(String role, Long userId);

    ResponseEntity<Void> deleteUser(Long id);
}
