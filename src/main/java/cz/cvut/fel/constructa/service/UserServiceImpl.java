package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.security.AuthenticationFacade;
import cz.cvut.fel.constructa.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userDao;
//    private final AuthenticationFacade authenticationFacade;

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
    public User update(User user) {
        return userDao.save(user);
    }
}
