package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.TaskRequest;
import cz.cvut.fel.constructa.dto.response.TaskDTO;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.enums.TaskState;
import cz.cvut.fel.constructa.mapper.TaskMapper;
import cz.cvut.fel.constructa.model.Task;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.TaskRepository;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.security.AuthenticationFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository mockTaskDao;
    @Mock
    private UserRepository mockUserDao;
    @Mock
    private TaskMapper mockTaskMapper;
    @Mock
    private AuthenticationFacade mockAuthenticationFacade;

    private TaskServiceImpl taskServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        taskServiceImplUnderTest = new TaskServiceImpl(mockTaskDao, mockUserDao, mockTaskMapper,
                mockAuthenticationFacade);
    }

    @Test
    void testCreate() throws Exception {
        // Setup
        final TaskRequest request = TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build();

        // Configure TaskMapper.convertToEntity(...).
        final Task task = Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build();
        when(mockTaskMapper.convertToEntity(TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build())).thenReturn(task);

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .build());
        when(mockUserDao.findById(0L)).thenReturn(user);

        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);

        // Configure UserRepository.findByEmail(...).
        final Optional<User> user1 = Optional.of(User.builder()
                .id(0L)
                .build());
        when(mockUserDao.findByEmail("email")).thenReturn(user1);

        // Configure TaskRepository.save(...).
        final Task task1 = Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build();
        when(mockTaskDao.save(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(task1);

        // Configure TaskMapper.convertToDto(...).
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(0L);
        taskDTO.setDateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setName("name");
        taskDTO.setDescription("description");
        taskDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO assignee = new UserDTO();
        assignee.setId(0L);
        assignee.setUsername("username");
        assignee.setEmail("email");
        assignee.setPhone("phone");
        assignee.setRole("role");
        assignee.setTitleBeforeName("titleBeforeName");
        assignee.setFirstname("firstname");
        assignee.setLastname("lastname");
        taskDTO.setAssignee(assignee);
        when(mockTaskMapper.convertToDto(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(taskDTO);

        // Run the test
        final TaskDTO result = taskServiceImplUnderTest.create(request);

        // Verify the results
    }

    @Test
    void testCreate_TaskMapperConvertToEntityThrowsParseException() throws Exception {
        // Setup
        final TaskRequest request = TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build();
        when(mockTaskMapper.convertToEntity(TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build())).thenThrow(ParseException.class);

        // Run the test
        assertThatThrownBy(() -> taskServiceImplUnderTest.create(request)).isInstanceOf(ParseException.class);
    }

    @Test
    void testCreate_UserRepositoryFindByIdReturnsAbsent() throws Exception {
        // Setup
        final TaskRequest request = TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build();

        // Configure TaskMapper.convertToEntity(...).
        final Task task = Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build();
        when(mockTaskMapper.convertToEntity(TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build())).thenReturn(task);

        when(mockUserDao.findById(0L)).thenReturn(Optional.empty());
        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);

        // Configure UserRepository.findByEmail(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .build());
        when(mockUserDao.findByEmail("email")).thenReturn(user);

        // Configure TaskRepository.save(...).
        final Task task1 = Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build();
        when(mockTaskDao.save(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(task1);

        // Configure TaskMapper.convertToDto(...).
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(0L);
        taskDTO.setDateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setName("name");
        taskDTO.setDescription("description");
        taskDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO assignee = new UserDTO();
        assignee.setId(0L);
        assignee.setUsername("username");
        assignee.setEmail("email");
        assignee.setPhone("phone");
        assignee.setRole("role");
        assignee.setTitleBeforeName("titleBeforeName");
        assignee.setFirstname("firstname");
        assignee.setLastname("lastname");
        taskDTO.setAssignee(assignee);
        when(mockTaskMapper.convertToDto(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(taskDTO);

        // Run the test
        final TaskDTO result = taskServiceImplUnderTest.create(request);

        // Verify the results
    }

    @Test
    void testCreate_UserRepositoryFindByEmailReturnsAbsent() throws Exception {
        // Setup
        final TaskRequest request = TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build();

        // Configure TaskMapper.convertToEntity(...).
        final Task task = Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build();
        when(mockTaskMapper.convertToEntity(TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build())).thenReturn(task);

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .build());
        when(mockUserDao.findById(0L)).thenReturn(user);

        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);
        when(mockUserDao.findByEmail("email")).thenReturn(Optional.empty());

        // Configure TaskRepository.save(...).
        final Task task1 = Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build();
        when(mockTaskDao.save(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(task1);

        // Configure TaskMapper.convertToDto(...).
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(0L);
        taskDTO.setDateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setName("name");
        taskDTO.setDescription("description");
        taskDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO assignee = new UserDTO();
        assignee.setId(0L);
        assignee.setUsername("username");
        assignee.setEmail("email");
        assignee.setPhone("phone");
        assignee.setRole("role");
        assignee.setTitleBeforeName("titleBeforeName");
        assignee.setFirstname("firstname");
        assignee.setLastname("lastname");
        taskDTO.setAssignee(assignee);
        when(mockTaskMapper.convertToDto(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(taskDTO);

        // Run the test
        final TaskDTO result = taskServiceImplUnderTest.create(request);

        // Verify the results
    }

    @Test
    void testGetTaskById() {
        // Setup
        // Configure TaskRepository.findAll(...).
        final List<Task> tasks = List.of(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build());
        when(mockTaskDao.findAll()).thenReturn(tasks);

        // Configure TaskMapper.convertToDto(...).
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(0L);
        taskDTO.setDateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setName("name");
        taskDTO.setDescription("description");
        taskDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO assignee = new UserDTO();
        assignee.setId(0L);
        assignee.setUsername("username");
        assignee.setEmail("email");
        assignee.setPhone("phone");
        assignee.setRole("role");
        assignee.setTitleBeforeName("titleBeforeName");
        assignee.setFirstname("firstname");
        assignee.setLastname("lastname");
        taskDTO.setAssignee(assignee);
        when(mockTaskMapper.convertToDto(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(taskDTO);

        // Run the test
        final TaskDTO result = taskServiceImplUnderTest.getTaskById(0L);

        // Verify the results
    }

    @Test
    void testGetTaskById_TaskRepositoryReturnsNoItems() {
        // Setup
        when(mockTaskDao.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final TaskDTO result = taskServiceImplUnderTest.getTaskById(0L);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetTasks() {
        // Setup
        // Configure TaskRepository.findAll(...).
        final List<Task> tasks = List.of(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build());
        when(mockTaskDao.findAll()).thenReturn(tasks);

        // Configure TaskMapper.convertToDto(...).
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(0L);
        taskDTO.setDateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setName("name");
        taskDTO.setDescription("description");
        taskDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO assignee = new UserDTO();
        assignee.setId(0L);
        assignee.setUsername("username");
        assignee.setEmail("email");
        assignee.setPhone("phone");
        assignee.setRole("role");
        assignee.setTitleBeforeName("titleBeforeName");
        assignee.setFirstname("firstname");
        assignee.setLastname("lastname");
        taskDTO.setAssignee(assignee);
        when(mockTaskMapper.convertToDto(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(taskDTO);

        // Run the test
        final List<TaskDTO> result = taskServiceImplUnderTest.getTasks();

        // Verify the results
    }

    @Test
    void testGetTasks_TaskRepositoryReturnsNoItems() {
        // Setup
        when(mockTaskDao.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<TaskDTO> result = taskServiceImplUnderTest.getTasks();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetMyTasks() {
        // Setup
        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);

        // Configure UserRepository.findByEmail(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .build());
        when(mockUserDao.findByEmail("email")).thenReturn(user);

        // Configure TaskRepository.findTaskByAssigneeId(...).
        final List<Task> tasks = List.of(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build());
        when(mockTaskDao.findTaskByAssigneeId(0L)).thenReturn(tasks);

        // Configure TaskMapper.convertToDto(...).
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(0L);
        taskDTO.setDateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setName("name");
        taskDTO.setDescription("description");
        taskDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO assignee = new UserDTO();
        assignee.setId(0L);
        assignee.setUsername("username");
        assignee.setEmail("email");
        assignee.setPhone("phone");
        assignee.setRole("role");
        assignee.setTitleBeforeName("titleBeforeName");
        assignee.setFirstname("firstname");
        assignee.setLastname("lastname");
        taskDTO.setAssignee(assignee);
        when(mockTaskMapper.convertToDto(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(taskDTO);

        // Run the test
        final List<TaskDTO> result = taskServiceImplUnderTest.getMyTasks();

        // Verify the results
    }

    @Test
    void testGetMyTasks_UserRepositoryReturnsAbsent() {
        // Setup
        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);
        when(mockUserDao.findByEmail("email")).thenReturn(Optional.empty());

        // Run the test
        final List<TaskDTO> result = taskServiceImplUnderTest.getMyTasks();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetMyTasks_TaskRepositoryReturnsNoItems() {
        // Setup
        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);

        // Configure UserRepository.findByEmail(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .build());
        when(mockUserDao.findByEmail("email")).thenReturn(user);

        when(mockTaskDao.findTaskByAssigneeId(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<TaskDTO> result = taskServiceImplUnderTest.getMyTasks();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testDelete() {
        // Setup
        // Run the test
        taskServiceImplUnderTest.delete(0L);

        // Verify the results
        verify(mockTaskDao).deleteById(0L);
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        final TaskRequest request = TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build();

        // Configure TaskRepository.findAll(...).
        final List<Task> tasks = List.of(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build());
        when(mockTaskDao.findAll()).thenReturn(tasks);

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .build());
        when(mockUserDao.findById(0L)).thenReturn(user);

        // Configure TaskMapper.convertToEntity(...).
        final Task task = Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build();
        when(mockTaskMapper.convertToEntity(TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build())).thenReturn(task);

        // Configure TaskRepository.save(...).
        final Task task1 = Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build();
        when(mockTaskDao.save(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(task1);

        // Configure TaskMapper.convertToDto(...).
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(0L);
        taskDTO.setDateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setName("name");
        taskDTO.setDescription("description");
        taskDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO assignee = new UserDTO();
        assignee.setId(0L);
        assignee.setUsername("username");
        assignee.setEmail("email");
        assignee.setPhone("phone");
        assignee.setRole("role");
        assignee.setTitleBeforeName("titleBeforeName");
        assignee.setFirstname("firstname");
        assignee.setLastname("lastname");
        taskDTO.setAssignee(assignee);
        when(mockTaskMapper.convertToDto(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(taskDTO);

        // Run the test
        final TaskDTO result = taskServiceImplUnderTest.update(request);

        // Verify the results
    }

    @Test
    void testUpdate_TaskRepositoryFindAllReturnsNoItems() throws Exception {
        // Setup
        final TaskRequest request = TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build();
        when(mockTaskDao.findAll()).thenReturn(Collections.emptyList());

        // Configure TaskMapper.convertToEntity(...).
        final Task task = Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build();
        when(mockTaskMapper.convertToEntity(TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build())).thenReturn(task);

        // Configure TaskRepository.save(...).
        final Task task1 = Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build();
        when(mockTaskDao.save(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(task1);

        // Configure TaskMapper.convertToDto(...).
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(0L);
        taskDTO.setDateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setName("name");
        taskDTO.setDescription("description");
        taskDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO assignee = new UserDTO();
        assignee.setId(0L);
        assignee.setUsername("username");
        assignee.setEmail("email");
        assignee.setPhone("phone");
        assignee.setRole("role");
        assignee.setTitleBeforeName("titleBeforeName");
        assignee.setFirstname("firstname");
        assignee.setLastname("lastname");
        taskDTO.setAssignee(assignee);
        when(mockTaskMapper.convertToDto(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(taskDTO);

        // Run the test
        final TaskDTO result = taskServiceImplUnderTest.update(request);

        // Verify the results
    }

    @Test
    void testUpdate_UserRepositoryReturnsAbsent() throws Exception {
        // Setup
        final TaskRequest request = TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build();

        // Configure TaskRepository.findAll(...).
        final List<Task> tasks = List.of(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build());
        when(mockTaskDao.findAll()).thenReturn(tasks);

        when(mockUserDao.findById(0L)).thenReturn(Optional.empty());

        // Configure TaskMapper.convertToEntity(...).
        final Task task = Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build();
        when(mockTaskMapper.convertToEntity(TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build())).thenReturn(task);

        // Configure TaskRepository.save(...).
        final Task task1 = Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build();
        when(mockTaskDao.save(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(task1);

        // Configure TaskMapper.convertToDto(...).
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(0L);
        taskDTO.setDateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setName("name");
        taskDTO.setDescription("description");
        taskDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO assignee = new UserDTO();
        assignee.setId(0L);
        assignee.setUsername("username");
        assignee.setEmail("email");
        assignee.setPhone("phone");
        assignee.setRole("role");
        assignee.setTitleBeforeName("titleBeforeName");
        assignee.setFirstname("firstname");
        assignee.setLastname("lastname");
        taskDTO.setAssignee(assignee);
        when(mockTaskMapper.convertToDto(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(taskDTO);

        // Run the test
        final TaskDTO result = taskServiceImplUnderTest.update(request);

        // Verify the results
    }

    @Test
    void testUpdate_TaskMapperConvertToEntityThrowsParseException() throws Exception {
        // Setup
        final TaskRequest request = TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build();

        // Configure TaskRepository.findAll(...).
        final List<Task> tasks = List.of(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build());
        when(mockTaskDao.findAll()).thenReturn(tasks);

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .build());
        when(mockUserDao.findById(0L)).thenReturn(user);

        when(mockTaskMapper.convertToEntity(TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build())).thenThrow(ParseException.class);

        // Run the test
        assertThatThrownBy(() -> taskServiceImplUnderTest.update(request)).isInstanceOf(ParseException.class);
    }

    @Test
    void testChangeTaskState() {
        // Setup
        final TaskRequest request = TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build();

        // Configure TaskRepository.findById(...).
        final Optional<Task> task = Optional.of(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build());
        when(mockTaskDao.findById(0L)).thenReturn(task);

        // Configure TaskRepository.save(...).
        final Task task1 = Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build();
        when(mockTaskDao.save(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(task1);

        // Configure TaskMapper.convertToDto(...).
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(0L);
        taskDTO.setDateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setName("name");
        taskDTO.setDescription("description");
        taskDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        taskDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO assignee = new UserDTO();
        assignee.setId(0L);
        assignee.setUsername("username");
        assignee.setEmail("email");
        assignee.setPhone("phone");
        assignee.setRole("role");
        assignee.setTitleBeforeName("titleBeforeName");
        assignee.setFirstname("firstname");
        assignee.setLastname("lastname");
        taskDTO.setAssignee(assignee);
        when(mockTaskMapper.convertToDto(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build())).thenReturn(taskDTO);

        // Run the test
        final TaskDTO result = taskServiceImplUnderTest.changeTaskState(request);

        // Verify the results
        verify(mockTaskDao).save(Task.builder()
                .id(0L)
                .dateOfCreation(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())
                .author(User.builder()
                        .id(0L)
                        .build())
                .assignee(User.builder()
                        .id(0L)
                        .build())
                .state(TaskState.NEW)
                .build());
    }

    @Test
    void testChangeTaskState_TaskRepositoryFindByIdReturnsAbsent() {
        // Setup
        final TaskRequest request = TaskRequest.builder()
                .id(0L)
                .state("state")
                .userId(0L)
                .build();
        when(mockTaskDao.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final TaskDTO result = taskServiceImplUnderTest.changeTaskState(request);

        // Verify the results
        assertThat(result).isNull();
    }
}
