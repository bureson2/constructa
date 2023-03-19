package cz.cvut.fel.constructa.service.impl;

import cz.cvut.fel.constructa.security.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userDao;

    @Autowired
    private AuthenticationFacade authenticationFacade;


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

        Authentication authentication = authenticationFacade.getAuthentication();
        String username = authentication.getName();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        // výpis uživatelského jména
        System.out.println("Uživatel: " + username);

        // výpis seznamu rolí
        System.out.println("Role: ");
        for (GrantedAuthority authority : authorities) {
            System.out.println(authority.getAuthority());
        }



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
