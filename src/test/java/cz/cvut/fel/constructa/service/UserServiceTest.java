package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.enums.Role;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.service.interfaces.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import cz.cvut.fel.constructa.generator.UserGenerator;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.List;

import static cz.cvut.fel.constructa.generator.UserGenerator.getRandomDate;
import static cz.cvut.fel.constructa.generator.UserGenerator.randomInt;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userDao;

    @Test
    public void createUser_validParams_userCreated() throws ParseException {
        User user = UserGenerator.generateBasicUser();

        userService.create(user);
        Optional<User> savedUser = userDao.findById(user.getId());

        assertEquals(user, savedUser.get());
    }

//    TODO fix
    @Test
    public void createUser_unvalidParams_exceptionThrow() throws ParseException {
        User user = UserGenerator.generateBasicUser();
        user.setId(null);

        assertThrows(Exception.class,
                () -> userService.create(user));
    }

    @Test
    public void getAllUsers_databaseWithUser_returnAllUsers() throws ParseException {
        int employeeCounter = 5;
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < employeeCounter; i++){
            User user = UserGenerator.generateBasicUser();
            userService.create(user);
            userList.add(user);
        }
        List<User> returnedUser = userService.getUsers();

        assertEquals(userList.size(), returnedUser.size());
    }

    @Test
    public void updateUser_validParams_returnChangedUser() throws ParseException {
        User user = UserGenerator.generateBasicUser();
        int originUserHashCode = user.hashCode();
        userService.create(user);

        String randomNumber = Integer.toString(randomInt());
        List<Role> roles = new ArrayList<>();
        roles.add(Role.ADMIN);
        user.setRoles(roles);
        user.setEmail("test@gmail.com".concat(randomNumber));
        user.setUsername("username".concat(randomNumber));
        user.setFirstname("testname".concat(randomNumber));
        user.setLastname("testlastname".concat(randomNumber));
        user.setBirthId("0012127189".concat(randomNumber));
        user.setDateOfBirth(getRandomDate());
        user.setBankAccount("0123456789".concat(randomNumber));
        user.setDateOfAcceptance(getRandomDate());
        user.setHourRate(200);
        user.setPassword("password".concat(randomNumber));
//        TODO really id in update?
        User updatedUser = userService.updateRole(user.getId(), role);

        assertNotEquals(originUserHashCode, updatedUser.hashCode());
        assertEquals(user, updatedUser);

    }

// TODO Make actual
//    FIX control
    @Test
    public void updateUser_nullParams_exceptionThrow() throws ParseException {
        User user = UserGenerator.generateBasicUser();
        userService.create(user);
        String username = user.getUsername();
        Long id = user.getId();

        user.setUsername(null);
        assertThrows(Exception.class,
                () -> userService.update(user.getId(), user));

        user.setUsername(username);
        user.setId(null);
        assertThrows(Exception.class,
                () -> userService.update(id, user));
    }

    @Test
    public void deleteUser_validUserId_userDelete() throws ParseException {
        User user = UserGenerator.generateBasicUser();
        userService.create(user);

        userService.delete(user.getId());
        Optional<User> deletedUser = userDao.findById(user.getId());

        assertEquals(Optional.empty(), deletedUser);
    }
}
