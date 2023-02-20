package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.model.role.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> createUser(User user);
    Optional<User> getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
    Optional<User> updateUser(Long id, User updatedUser);

}
