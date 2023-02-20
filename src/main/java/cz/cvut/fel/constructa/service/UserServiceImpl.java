package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.model.role.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userDao;
    @Override
    public Optional<User> createUser(User createdUser) {
        userDao.save(createdUser);
        return getUserById(createdUser.getId());
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public Optional<User> updateUser(Long id, User updatedUser) {
        userDao.save(updatedUser);
        return getUserById(updatedUser.getId());
    }
}
