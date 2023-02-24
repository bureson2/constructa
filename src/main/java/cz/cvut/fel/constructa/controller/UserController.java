package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.dto.response.UserResponseDTO;
import cz.cvut.fel.constructa.mapper.UserMapper;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

//    TODO response body specifikace?
//    Upravit na value a jeden hlavni path

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserResponseDTO> getUsers() {
        List<User> users = userService.getUsers();
        return users.stream()
                .map(user -> userMapper.convertToDto(user))
                .collect(Collectors.toList());
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDTO getUser(@PathVariable Long userId) {
        Optional<User> usertToReturn = userService.getUserById(userId);
        return usertToReturn.map(user -> userMapper.convertToDto(user)).orElse(null);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDTO createUser(@RequestBody User newUser) {
        User createdUser = userService.create(newUser);
        return userMapper.convertToDto(createdUser);
    }

    // TODO
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value = "/{userId}")
    public UserResponseDTO updateRole(@RequestBody String role, @PathVariable Long userId) {
        User updatedUser = userService.updateRole(userId, role);
        return userMapper.convertToDto(updatedUser);
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }
}
