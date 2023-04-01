package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.UserRequest;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.dto.response.UserInputDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RequestMapping("/api/v1/users")
public interface UserController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserDTO>> getUsers();

    @GetMapping(value="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserInputDTO>> getInputUsers();

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDTO> getUser(@PathVariable Long userId);

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDTO> updateUser(@RequestBody UserRequest request) throws ParseException;

    @DeleteMapping(value = "/{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable Long userId);
}
