package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.UserRequest;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.dto.response.UserInputDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * The interface User controller.
 */
@RequestMapping("/api/v1/users")
public interface UserController {
    /**
     * Gets users.
     *
     * @return the users
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserDTO>> getUsers();

    /**
     * Gets input users.
     *
     * @return the input users
     */
    @GetMapping(value="/search", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<UserInputDTO>> getInputUsers();

    /**
     * Gets user.
     *
     * @param userId the user id
     * @return the user
     */
    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDTO> getUser(@PathVariable Long userId);

    /**
     * Update user response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDTO> updateUser(@RequestBody UserRequest request) throws ParseException;

    /**
     * Delete user response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @DeleteMapping(value = "/{userId}")
    ResponseEntity<Void> deleteUser(@PathVariable Long userId);
}
