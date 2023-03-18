package cz.cvut.fel.constructa.service.impl;

import cz.cvut.fel.constructa.enums.Role;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

//    TODO upravit do verze bez optional - i testy

    @Autowired
    private UserRepository userDao;

    @Override
    public User create(User createdUser) {

        return userDao.save(createdUser);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userDao.findById(userId);
    }

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public void delete(Long userId) {
        userDao.deleteById(userId);
    }


    // todo delete
    @Override
    public User updateRole(Long id, String role) {
        return null;
    }

//    @Override
//    public User updateRole(Long userId, String role) {
//        Optional<User> user = getUserById(userId);
//        if (user.isPresent()) {
//            if (user.get().getRoles() == null) {
//                List<Role> newRoles = new ArrayList<>();
//                newRoles.add(Role.valueOf(role));
//                user.get().setRoles(newRoles);
//            } else {
//                user.get().getRoles().add(Role.valueOf(role));
//            }
//            userDao.save(user.get());
//            return user.get();
//        }
//        return null;
//    }

    @Override
    public User update(User user){
        return userDao.save(user);
    }
}
