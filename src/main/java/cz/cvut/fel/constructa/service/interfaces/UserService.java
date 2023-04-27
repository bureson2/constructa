package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.UserRequest;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.dto.response.UserInputDTO;

import java.text.ParseException;
import java.util.List;

/**
 * The interface User service.
 */
public interface UserService {
    /**
     * Gets user by id.
     *
     * @param id the id
     * @return the user by id
     */
    UserDTO getUserById(Long id);

    /**
     * Gets users.
     *
     * @return the users
     */
    List<UserDTO> getUsers();

    /**
     * Gets input users.
     *
     * @return the input users
     */
    List<UserInputDTO> getInputUsers();

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);

    /**
     * Update user dto.
     *
     * @param request the request
     * @return the user dto
     * @throws ParseException the parse exception
     */
    UserDTO update(UserRequest request) throws ParseException;

}
