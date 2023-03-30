package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.model.role.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> getUserById(Long id);
    List<User> getUsers();
    void delete(Long id);
    public User update(User user);

}
