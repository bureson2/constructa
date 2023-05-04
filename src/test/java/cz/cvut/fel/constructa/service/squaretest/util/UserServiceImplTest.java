package cz.cvut.fel.constructa.service.squaretest.util;

import cz.cvut.fel.constructa.dto.request.UserRequest;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.dto.response.UserInputDTO;
import cz.cvut.fel.constructa.mapper.UserMapper;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.*;
import cz.cvut.fel.constructa.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.text.ParseException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository mockUserDao;
    @Mock
    private LocationRepository mockLocationDao;
    @Mock
    private ProjectRepository mockProjectDao;
    @Mock
    private WorkReportRepository mockWorkReportDao;
    @Mock
    private ConstructionReportRepository mockConstructionReportDao;
    @Mock
    private VehicleRepository mockVehicleDao;
    @Mock
    private TaskRepository mockTaskDao;
    @Mock
    private PasswordEncoder mockPasswordEncoder;
    @Mock
    private UserMapper mockUserMapper;

    private UserServiceImpl userServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        userServiceImplUnderTest = new UserServiceImpl(mockUserDao, mockLocationDao, mockProjectDao, mockWorkReportDao,
                mockConstructionReportDao, mockVehicleDao, mockTaskDao, mockPasswordEncoder, mockUserMapper);
    }

    @Test
    void testGetUserById() {
        // Setup
        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build());
        when(mockUserDao.findById(0L)).thenReturn(user);

        // Configure UserMapper.convertToDto(...).
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(0L);
        userDTO.setUsername("username");
        userDTO.setEmail("email");
        userDTO.setPhone("phone");
        userDTO.setRole("role");
        userDTO.setTitleBeforeName("titleBeforeName");
        userDTO.setFirstname("firstname");
        userDTO.setLastname("lastname");
        userDTO.setTitleAfterName("titleAfterName");
        userDTO.setBankAccount("bankAccount");
        userDTO.setDateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userDTO.setDateOfBirth(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userDTO.setBirthId("birthId");
        userDTO.setHourRate(0);
        userDTO.setMonthSalary(1);
        when(mockUserMapper.convertToDto(User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build())).thenReturn(userDTO);

        // Run the test
        final UserDTO result = userServiceImplUnderTest.getUserById(0L);

        // Verify the results
    }

    @Test
    void testGetUserById_UserRepositoryReturnsAbsent() {
        // Setup
        when(mockUserDao.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final UserDTO result = userServiceImplUnderTest.getUserById(0L);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetUsers() {
        // Setup
        // Configure UserRepository.findAll(...).
        final List<User> users = List.of(User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build());
        when(mockUserDao.findAll(Sort.by("properties"))).thenReturn(users);

        // Configure UserMapper.convertToDto(...).
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(0L);
        userDTO.setUsername("username");
        userDTO.setEmail("email");
        userDTO.setPhone("phone");
        userDTO.setRole("role");
        userDTO.setTitleBeforeName("titleBeforeName");
        userDTO.setFirstname("firstname");
        userDTO.setLastname("lastname");
        userDTO.setTitleAfterName("titleAfterName");
        userDTO.setBankAccount("bankAccount");
        userDTO.setDateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userDTO.setDateOfBirth(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userDTO.setBirthId("birthId");
        userDTO.setHourRate(0);
        userDTO.setMonthSalary(1);
        when(mockUserMapper.convertToDto(User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build())).thenReturn(userDTO);

        // Run the test
        final List<UserDTO> result = userServiceImplUnderTest.getUsers();

        // Verify the results
    }

    @Test
    void testGetUsers_UserRepositoryReturnsNoItems() {
        // Setup
        when(mockUserDao.findAll(Sort.by("properties"))).thenReturn(Collections.emptyList());

        // Run the test
        final List<UserDTO> result = userServiceImplUnderTest.getUsers();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetInputUsers() {
        // Setup
        // Configure UserRepository.findAll(...).
        final List<User> users = List.of(User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build());
        when(mockUserDao.findAll(Sort.by("properties"))).thenReturn(users);

        // Configure UserMapper.convertToInputDto(...).
        final UserInputDTO userInputDTO = new UserInputDTO();
        userInputDTO.setId(0L);
        userInputDTO.setFirstname("firstname");
        userInputDTO.setLastname("lastname");
        when(mockUserMapper.convertToInputDto(User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build())).thenReturn(userInputDTO);

        // Run the test
        final List<UserInputDTO> result = userServiceImplUnderTest.getInputUsers();

        // Verify the results
    }

    @Test
    void testGetInputUsers_UserRepositoryReturnsNoItems() {
        // Setup
        when(mockUserDao.findAll(Sort.by("properties"))).thenReturn(Collections.emptyList());

        // Run the test
        final List<UserInputDTO> result = userServiceImplUnderTest.getInputUsers();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testDelete() {
        // Setup
        // Run the test
        userServiceImplUnderTest.delete(0L);

        // Verify the results
        verify(mockTaskDao).setAssigneeToNullByUserId(0L);
        verify(mockTaskDao).setAuthorToNullByUserId(0L);
        verify(mockWorkReportDao).deleteByReportingEmployeeId(0L);
        verify(mockConstructionReportDao).setExecutorToNullByUserId(0L);
        verify(mockProjectDao).setProjectManagerToNullByUserId(0L);
        verify(mockVehicleDao).setDriverToNullByUserId(0L);
        verify(mockUserDao).deleteById(0L);
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        final UserRequest request = UserRequest.builder()
                .id(0L)
                .password("password")
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build();

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build());
        when(mockUserDao.findById(0L)).thenReturn(user);

        // Configure LocationRepository.save(...).
        final Location location = Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build();
        when(mockLocationDao.save(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build())).thenReturn(location);

        // Configure UserMapper.convertToEntity(...).
        final User user1 = User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build();
        when(mockUserMapper.convertToEntity(UserRequest.builder()
                .id(0L)
                .password("password")
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build())).thenReturn(user1);

        when(mockPasswordEncoder.encode("password")).thenReturn("password");

        // Configure UserRepository.save(...).
        final User user2 = User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build();
        when(mockUserDao.save(User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build())).thenReturn(user2);

        // Configure UserMapper.convertToDto(...).
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(0L);
        userDTO.setUsername("username");
        userDTO.setEmail("email");
        userDTO.setPhone("phone");
        userDTO.setRole("role");
        userDTO.setTitleBeforeName("titleBeforeName");
        userDTO.setFirstname("firstname");
        userDTO.setLastname("lastname");
        userDTO.setTitleAfterName("titleAfterName");
        userDTO.setBankAccount("bankAccount");
        userDTO.setDateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userDTO.setDateOfBirth(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userDTO.setBirthId("birthId");
        userDTO.setHourRate(0);
        userDTO.setMonthSalary(1);
        when(mockUserMapper.convertToDto(User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build())).thenReturn(userDTO);

        // Run the test
        final UserDTO result = userServiceImplUnderTest.update(request);

        // Verify the results
        verify(mockLocationDao).save(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
    }

    @Test
    void testUpdate_UserRepositoryFindByIdReturnsAbsent() throws Exception {
        // Setup
        final UserRequest request = UserRequest.builder()
                .id(0L)
                .password("password")
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build();
        when(mockUserDao.findById(0L)).thenReturn(Optional.empty());

        // Configure UserMapper.convertToEntity(...).
        final User user = User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build();
        when(mockUserMapper.convertToEntity(UserRequest.builder()
                .id(0L)
                .password("password")
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build())).thenReturn(user);

        when(mockPasswordEncoder.encode("password")).thenReturn("password");

        // Configure UserRepository.save(...).
        final User user1 = User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build();
        when(mockUserDao.save(User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build())).thenReturn(user1);

        // Configure UserMapper.convertToDto(...).
        final UserDTO userDTO = new UserDTO();
        userDTO.setId(0L);
        userDTO.setUsername("username");
        userDTO.setEmail("email");
        userDTO.setPhone("phone");
        userDTO.setRole("role");
        userDTO.setTitleBeforeName("titleBeforeName");
        userDTO.setFirstname("firstname");
        userDTO.setLastname("lastname");
        userDTO.setTitleAfterName("titleAfterName");
        userDTO.setBankAccount("bankAccount");
        userDTO.setDateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userDTO.setDateOfBirth(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        userDTO.setBirthId("birthId");
        userDTO.setHourRate(0);
        userDTO.setMonthSalary(1);
        when(mockUserMapper.convertToDto(User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build())).thenReturn(userDTO);

        // Run the test
        final UserDTO result = userServiceImplUnderTest.update(request);

        // Verify the results
    }

    @Test
    void testUpdate_UserMapperConvertToEntityThrowsParseException() throws Exception {
        // Setup
        final UserRequest request = UserRequest.builder()
                .id(0L)
                .password("password")
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build();

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(User.builder()
                .username("username")
                .password("password")
                .dateOfAcceptance(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .userAddress(Location.builder()
                        .city("city")
                        .street("street")
                        .descriptiveNumber("descriptiveNumber")
                        .country("country")
                        .postCode("postCode")
                        .build())
                .build());
        when(mockUserDao.findById(0L)).thenReturn(user);

        // Configure LocationRepository.save(...).
        final Location location = Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build();
        when(mockLocationDao.save(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build())).thenReturn(location);

        when(mockUserMapper.convertToEntity(UserRequest.builder()
                .id(0L)
                .password("password")
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build())).thenThrow(ParseException.class);

        // Run the test
        assertThatThrownBy(() -> userServiceImplUnderTest.update(request)).isInstanceOf(ParseException.class);
        verify(mockLocationDao).save(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
    }
}
