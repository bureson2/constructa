package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.UserRequest;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.dto.response.UserInputDTO;
import cz.cvut.fel.constructa.mapper.UserMapper;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.LocationRepository;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Sort;

/**
 * The UserServiceImpl class implements the UserService interface and provides operations related to the User model.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    /**
     * The User dao.
     */
    private final UserRepository userDao;
    /**
     * The Location dao.
     */
    private final LocationRepository locationDao;
    /**
     * The Password encoder.
     */
    private final PasswordEncoder passwordEncoder;
    /**
     * The User mapper.
     */
    private final UserMapper userMapper;

    /**
     * Gets user by id.
     *
     * @param userId the user id
     * @return the user by id
     */
    @Override
    public UserDTO getUserById(Long userId) {
        Optional<User> user = userDao.findById(userId);
        return user.map(userMapper::convertToDto).orElse(null);
    }

    /**
     * Gets users.
     *
     * @return the users
     */
    @Override
    public List<UserDTO> getUsers() {
        Sort sortByName = Sort.by(Sort.Direction.ASC, "lastname");
        List<User> users = userDao.findAll(sortByName);
        return users.stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Gets input users.
     *
     * @return the input users
     */
    @Override
    public List<UserInputDTO> getInputUsers() {
        Sort sortByName = Sort.by(Sort.Direction.ASC, "lastname");
        List<User> users = userDao.findAll(sortByName);
        return users.stream()
                .map(userMapper::convertToInputDto)
                .collect(Collectors.toList());
    }

    /**
     * Delete.
     *
     * @param userId the user id
     */
    @Override
    public void delete(Long userId) {
        userDao.deleteById(userId);
    }

    /**
     * Update user dto.
     *
     * @param request the request
     * @return the user dto
     * @throws ParseException the parse exception
     */
    @Override
    public UserDTO update(UserRequest request) throws ParseException {
        Optional<User> user = userDao.findById(request.getId());
        String username = "";
        Date acceptance = new Date();
        Location address = null;

        if (user.isPresent()) {
            username = user.get().getUsername();
            acceptance = user.get().getDateOfAcceptance();
            address = user.get().getUserAddress();
            if (!Objects.equals(address.getCity(), request.getCity())) {
                address.setCity(request.getCity());
            }
            if (!Objects.equals(address.getStreet(), request.getStreet())) {
                address.setStreet(request.getStreet());
            }
            if (!Objects.equals(address.getDescriptiveNumber(), request.getDescriptiveNumber())) {
                address.setDescriptiveNumber(request.getDescriptiveNumber());
            }
            if (!Objects.equals(address.getPostCode(), request.getPostCode())) {
                address.setPostCode(request.getPostCode());
            }
            if (!Objects.equals(address.getCountry(), request.getCountry())) {
                address.setCountry(request.getCountry());
            }
            locationDao.save(address);
        }

        User updatedUser = userMapper.convertToEntity(request);
        updatedUser.setUserAddress(address);
        updatedUser.setUsername(username);
        updatedUser.setDateOfAcceptance(acceptance);
        updatedUser.setPassword(passwordEncoder.encode(request.getPassword()));
        updatedUser = userDao.save(updatedUser);
        return userMapper.convertToDto(updatedUser);
    }
}
