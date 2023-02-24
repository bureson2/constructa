package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

//    TODO upravit do verze bez optional - i testy

    @Autowired
    private UserRepository userDao;
    @Override
    public Optional<User> create(User createdUser) {
        userDao.save(createdUser);
        return getUserById(createdUser.getId());
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public void delete(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public Optional<User> update(Long id, User updatedUser) {
        userDao.save(updatedUser);
        return getUserById(updatedUser.getId());
    }
}
