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
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userDao;
    private final LocationRepository locationDao;

    private final UserMapper userMapper;

    @Override
    public UserDTO getUserById(Long userId) {
        Optional<User> user = userDao.findById(userId);
        return user.map(userMapper::convertToDto).orElse(null);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = userDao.findAll();
        return users.stream()
                .map(userMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserInputDTO> getInputUsers(){
        List<User> users = userDao.findAll();
        return users.stream()
                .map(userMapper::convertToInputDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long userId) {
        userDao.deleteById(userId);
    }

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

        updatedUser = userDao.save(updatedUser);
        return userMapper.convertToDto(updatedUser);
    }
}
