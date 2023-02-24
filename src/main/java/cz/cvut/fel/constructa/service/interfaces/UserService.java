package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.model.role.User;
import java.util.List;
import java.util.Optional;

public interface UserService {

    User create(User user);
    Optional<User> getUserById(Long id);
    List<User> getUsers();
    void delete(Long id);
    User updateRole(Long id, String role);

    public User update(User user);

}
