package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.UserController;
import cz.cvut.fel.constructa.dto.request.UserRequest;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.dto.response.UserInputDTO;
import cz.cvut.fel.constructa.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(
                userService.getUsers()
        );
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserInputDTO>> getInputUsers() {
        return ResponseEntity.ok().body(
                userService.getInputUsers()
        );
    }


    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        UserDTO user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok().body(user);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                userService.update(request));
    }

    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.noContent().build();
    }
}
