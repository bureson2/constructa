package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.UserController;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.mapper.UserMapper;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok().body(
                users.stream()
                        .map(userMapper::convertToDto)
                        .collect(Collectors.toList())
        );
    }
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        Optional<User> usertToReturn = userService.getUserById(userId);
        return usertToReturn.map(user -> ResponseEntity.ok().body(
                userMapper.convertToDto(user)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@RequestBody User newUser) {
        User createdUser = userService.create(newUser);
        return ResponseEntity.ok().body(
                userMapper.convertToDto(createdUser)
        );
    }

    // TODO correct data getting
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value = "/{userId}")
    public ResponseEntity<UserDTO> updateRole(@RequestBody String role, @PathVariable Long userId) {
        User updatedUser = userService.updateRole(userId, role);
        return ResponseEntity.ok().body(
                userMapper.convertToDto(updatedUser)
        );
    }
    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
