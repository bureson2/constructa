package cz.cvut.fel.constructa.service.impl;

import cz.cvut.fel.constructa.model.role.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> create(User user);
    Optional<User> getUserById(Long id);
    List<User> getUsers();
    void delete(Long id);
    Optional<User> update(Long id, User updatedUser);

}
