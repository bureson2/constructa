package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import cz.cvut.fel.constructa.generator.UserGenerator;
import java.text.ParseException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImlTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userDao;

    @Test
    public void createUser_validParams_userCreated() throws ParseException {
        User user = UserGenerator.generateBasicUser();

        userService.createUser(user);
        Optional<User> savedUser = userDao.findById(user.getId());

        assertEquals(user, savedUser.get());
    }


}
