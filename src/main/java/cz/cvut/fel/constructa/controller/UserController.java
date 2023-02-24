package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.enums.Role;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/users/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> getUser(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(value = "/user/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Optional<User> createUser(@RequestBody User newUser) {
        return userService.create(newUser);
    }

    // TODO
    @ResponseStatus(code = HttpStatus.OK)
    @PutMapping(value = "/user/{userId}")
    public Optional<User> changeEmail(@RequestBody String role, @PathVariable Long userId) {
        Optional<User> user = userService.getUserById(userId); // TODO KONTROLA
        if(user.get() != null){
            if(user.get().getRoles() == null){
                List<Role> newRoles = new ArrayList<>();
                newRoles.add(Role.valueOf(role));
                user.get().setRoles(newRoles);
            } else {
                user.get().getRoles().add(Role.valueOf(role));
            }
//            user.get().setEmail(email);
            return userService.update(userId, user.get());
        }
        return null;
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/user/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }

}
