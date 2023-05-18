package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.ConstructionReportRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionReportDTO;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.enums.ConstructionDiaryReportState;
import cz.cvut.fel.constructa.enums.ProjectState;
import cz.cvut.fel.constructa.mapper.ConstructionReportMapper;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.Project;
import cz.cvut.fel.constructa.model.report.ConstructionReport;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.ConstructionReportRepository;
import cz.cvut.fel.constructa.repository.ProjectRepository;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.security.AuthenticationFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.text.ParseException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConstructionReportImplTest {

    @Mock
    private ConstructionReportRepository mockConstructionReportDao;
    @Mock
    private UserRepository mockUserDao;
    @Mock
    private ProjectRepository mockProjectDao;
    @Mock
    private ConstructionReportMapper mockConstructionReportMapper;
    @Mock
    private AuthenticationFacade mockAuthenticationFacade;

    private ConstructionReportImpl constructionReportImplUnderTest;

    @BeforeEach
    void setUp() {
        constructionReportImplUnderTest = new ConstructionReportImpl(mockConstructionReportDao, mockUserDao,
                mockProjectDao, mockConstructionReportMapper, mockAuthenticationFacade);
    }

    @Test
    void testCreate() throws Exception {
        // Setup
        final ConstructionReportRequest request = new ConstructionReportRequest();
        request.setId(0L);
        request.setTaskName("taskName");
        request.setNote("note");
        request.setWeather("weather");
        request.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setExecutorId(0L);
        request.setState(ConstructionDiaryReportState.FINISHED);
        request.setProjectId(0L);

        final ConstructionReportDTO expectedResult = new ConstructionReportDTO();
        expectedResult.setId(0L);
        expectedResult.setTaskName("taskName");
        expectedResult.setNote("note");
        expectedResult.setWeather("weather");
        expectedResult.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO executor = new UserDTO();
        executor.setId(0L);
        executor.setUsername("username");
        executor.setEmail("email");
        executor.setPhone("phone");
        executor.setRole("role");
        executor.setTitleBeforeName("titleBeforeName");
        executor.setFirstname("firstname");
        executor.setLastname("lastname");
        executor.setTitleAfterName("titleAfterName");
        expectedResult.setExecutor(executor);

        // Configure ConstructionReportMapper.convertToEntity(...).
        final ConstructionReport constructionReport = new ConstructionReport();
        constructionReport.setId(0L);
        constructionReport.setTaskName("taskName");
        constructionReport.setNote("note");
        constructionReport.setWeather("weather");
        constructionReport.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final User executorUser = User.builder()
                .id(0L)
                .username("username")
                .build();
        constructionReport.setExecutor(executorUser);

        final Project project = new Project();
        project.setId(0L);
        constructionReport.setProject(project);
        constructionReport.setState(ConstructionDiaryReportState.FINISHED);
        when(mockConstructionReportMapper.convertToEntity(request)).thenReturn(constructionReport);

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(executorUser);
        when(mockUserDao.findById(0L)).thenReturn(user);

        // Configure ProjectRepository.findById(...).
        final Project project2 = new Project();
        project2.setId(0L);
        project2.setName("name");
        project2.setBuldingFacility("buldingFacility");
        project2.setStartedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project2.setDeadline(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project2.setState(ProjectState.FINISHED);
        project2.setProjectManager(executorUser);

        project2.setProjectAddress(Location.builder().build());
        final ConstructionReport constructionReport1 = new ConstructionReport();
        constructionReport1.setId(0L);
        constructionReport1.setTaskName("taskName");
        project2.setConstructionReports(List.of(constructionReport1));
        final Optional<Project> project1 = Optional.of(project2);
        when(mockProjectDao.findById(0L)).thenReturn(project1);

        // Configure ConstructionReportRepository.save(...).
        final ConstructionReport constructionReport2 = new ConstructionReport();
        constructionReport2.setId(0L);
        constructionReport2.setTaskName("taskName");
        constructionReport2.setNote("note");
        constructionReport2.setWeather("weather");
        constructionReport2.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        constructionReport2.setExecutor(executorUser);
        final Project project3 = new Project();
        project3.setId(0L);
        constructionReport2.setProject(project3);
        constructionReport2.setState(ConstructionDiaryReportState.FINISHED);
        when(mockConstructionReportDao.save(constructionReport)).thenReturn(constructionReport2);

        // Configure ConstructionReportMapper.convertToDto(...).
        final ConstructionReportDTO constructionReportDTO = new ConstructionReportDTO();
        constructionReportDTO.setId(0L);
        constructionReportDTO.setTaskName("taskName");
        constructionReportDTO.setNote("note");
        constructionReportDTO.setWeather("weather");
        constructionReportDTO.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        constructionReportDTO.setExecutor(executor);
        when(mockConstructionReportMapper.convertToDto(constructionReport2)).thenReturn(constructionReportDTO);

        // Run the test
        final ConstructionReportDTO result = constructionReportImplUnderTest.create(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testGetConstructionReporttById() {
        // Setup
        final ConstructionReportDTO expectedResult = new ConstructionReportDTO();
        expectedResult.setId(0L);
        expectedResult.setTaskName("taskName");
        expectedResult.setNote("note");
        expectedResult.setWeather("weather");
        expectedResult.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO executor = new UserDTO();
        executor.setId(0L);
        executor.setUsername("username");
        executor.setEmail("email");
        executor.setPhone("phone");
        executor.setRole("role");
        executor.setTitleBeforeName("titleBeforeName");
        executor.setFirstname("firstname");
        executor.setLastname("lastname");
        executor.setTitleAfterName("titleAfterName");
        expectedResult.setExecutor(executor);

        // Configure ConstructionReportRepository.findById(...).
        final ConstructionReport constructionReport1 = new ConstructionReport();
        constructionReport1.setId(0L);
        constructionReport1.setTaskName("taskName");
        constructionReport1.setNote("note");
        constructionReport1.setWeather("weather");
        constructionReport1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final User executorUser = User.builder()
                .id(0L)
                .username("username")
                .build();
        constructionReport1.setExecutor(executorUser);

        final Project project = new Project();
        project.setId(0L);
        constructionReport1.setProject(project);
        constructionReport1.setState(ConstructionDiaryReportState.FINISHED);
        final Optional<ConstructionReport> constructionReport = Optional.of(constructionReport1);
        when(mockConstructionReportDao.findById(0L)).thenReturn(constructionReport);

        // Configure ConstructionReportMapper.convertToDto(...).
        final ConstructionReportDTO constructionReportDTO = new ConstructionReportDTO();
        constructionReportDTO.setId(0L);
        constructionReportDTO.setTaskName("taskName");
        constructionReportDTO.setNote("note");
        constructionReportDTO.setWeather("weather");
        constructionReportDTO.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        constructionReportDTO.setExecutor(executor);
        when(mockConstructionReportMapper.convertToDto(constructionReport1)).thenReturn(constructionReportDTO);

        // Run the test
        final ConstructionReportDTO result = constructionReportImplUnderTest.getConstructionReporttById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetConstructionReporttById_ConstructionReportRepositoryReturnsAbsent() {
        // Setup
        when(mockConstructionReportDao.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final ConstructionReportDTO result = constructionReportImplUnderTest.getConstructionReporttById(0L);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetConstructionReports() {
        // Setup
        final ConstructionReportDTO constructionReportDTO = new ConstructionReportDTO();
        constructionReportDTO.setId(0L);
        constructionReportDTO.setTaskName("taskName");
        constructionReportDTO.setNote("note");
        constructionReportDTO.setWeather("weather");
        constructionReportDTO.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO executor = new UserDTO();
        executor.setId(0L);
        executor.setUsername("username");
        executor.setEmail("email");
        executor.setPhone("phone");
        executor.setRole("role");
        executor.setTitleBeforeName("titleBeforeName");
        executor.setFirstname("firstname");
        executor.setLastname("lastname");
        executor.setTitleAfterName("titleAfterName");
        constructionReportDTO.setExecutor(executor);
        final List<ConstructionReportDTO> expectedResult = List.of(constructionReportDTO);

        // Configure ConstructionReportRepository.findAll(...).
        final ConstructionReport constructionReport = new ConstructionReport();
        constructionReport.setId(0L);
        constructionReport.setTaskName("taskName");
        constructionReport.setNote("note");
        constructionReport.setWeather("weather");
        constructionReport.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final User executorUser = User.builder()
                .username("username")
                .build();
        constructionReport.setExecutor(executorUser);

        final Project project = new Project();
        project.setId(0L);
        constructionReport.setProject(project);
        constructionReport.setState(ConstructionDiaryReportState.FINISHED);
        final List<ConstructionReport> constructionReports = List.of(constructionReport);
        when(mockConstructionReportDao.findAll(Sort.by(Sort.Direction.DESC, "date"))).thenReturn(constructionReports);

        // Configure ConstructionReportMapper.convertToDto(...).
        final ConstructionReportDTO constructionReportDTO1 = new ConstructionReportDTO();
        constructionReportDTO1.setId(0L);
        constructionReportDTO1.setTaskName("taskName");
        constructionReportDTO1.setNote("note");
        constructionReportDTO1.setWeather("weather");
        constructionReportDTO1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        constructionReportDTO1.setExecutor(executor);
        when(mockConstructionReportMapper.convertToDto(constructionReport)).thenReturn(constructionReportDTO1);

        // Run the test
        final List<ConstructionReportDTO> result = constructionReportImplUnderTest.getConstructionReports();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetConstructionReportsByProjectId() {
        // Setup
        final ConstructionReportDTO constructionReportDTO = new ConstructionReportDTO();
        constructionReportDTO.setId(0L);
        constructionReportDTO.setTaskName("taskName");
        constructionReportDTO.setNote("note");
        constructionReportDTO.setWeather("weather");
        constructionReportDTO.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO executor = new UserDTO();
        executor.setId(0L);
        executor.setUsername("username");
        executor.setEmail("email");
        executor.setPhone("phone");
        executor.setRole("role");
        executor.setTitleBeforeName("titleBeforeName");
        executor.setFirstname("firstname");
        executor.setLastname("lastname");
        executor.setTitleAfterName("titleAfterName");
        constructionReportDTO.setExecutor(executor);
        final List<ConstructionReportDTO> expectedResult = List.of(constructionReportDTO);

        // Configure ConstructionReportRepository.findAllByProjectId(...).
        final ConstructionReport constructionReport = new ConstructionReport();
        constructionReport.setId(0L);
        constructionReport.setTaskName("taskName");
        constructionReport.setNote("note");
        constructionReport.setWeather("weather");
        constructionReport.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final User executorUser = User.builder()
                .username("username")
                .build();
        constructionReport.setExecutor(executorUser);

        final Project project = new Project();
        project.setId(0L);
        constructionReport.setProject(project);
        constructionReport.setState(ConstructionDiaryReportState.FINISHED);
        final List<ConstructionReport> constructionReports = List.of(constructionReport);
        when(mockConstructionReportDao.findAllByProjectId(0L, Sort.by(Sort.Direction.DESC, "date"))).thenReturn(constructionReports);

        // Configure ConstructionReportMapper.convertToDto(...).
        final ConstructionReportDTO constructionReportDTO1 = new ConstructionReportDTO();
        constructionReportDTO1.setId(0L);
        constructionReportDTO1.setTaskName("taskName");
        constructionReportDTO1.setNote("note");
        constructionReportDTO1.setWeather("weather");
        constructionReportDTO1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        constructionReportDTO1.setExecutor(executor);
        when(mockConstructionReportMapper.convertToDto(constructionReport)).thenReturn(constructionReportDTO1);

        // Run the test
        final List<ConstructionReportDTO> result = constructionReportImplUnderTest.getConstructionReportsByProjectId(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    void testDelete() {
        // Setup
        // Run the test
        constructionReportImplUnderTest.delete(0L);

        // Verify the results
        verify(mockConstructionReportDao).deleteById(0L);
    }

    @Test
    void testUpdate() {
        // Setup
        final ConstructionReportRequest request = new ConstructionReportRequest();
        request.setId(0L);
        request.setTaskName("taskName");
        request.setNote("note");
        request.setWeather("weather");
        request.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setExecutorId(0L);
        request.setState(ConstructionDiaryReportState.FINISHED);
        request.setProjectId(0L);

        // Configure ConstructionReportRepository.findById(...).
        final ConstructionReport constructionReport1 = new ConstructionReport();
        constructionReport1.setId(0L);
        constructionReport1.setTaskName("taskName");
        constructionReport1.setNote("note");
        constructionReport1.setWeather("weather");
        constructionReport1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        final User executorUser = User.builder()
                .username("username")
                .build();
        constructionReport1.setExecutor(executorUser);

        final Project project = new Project();
        project.setId(0L);
        constructionReport1.setProject(project);
        constructionReport1.setState(ConstructionDiaryReportState.FINISHED);
        final Optional<ConstructionReport> constructionReport = Optional.of(constructionReport1);
        when(mockConstructionReportDao.findById(0L)).thenReturn(constructionReport);

        // Configure ConstructionReportRepository.save(...).
        final ConstructionReport constructionReport2 = new ConstructionReport();
        constructionReport2.setId(0L);
        constructionReport2.setTaskName("taskName");
        constructionReport2.setNote("note");
        constructionReport2.setWeather("weather");
        constructionReport2.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        constructionReport2.setExecutor(executorUser);

        final Project project1 = new Project();
        project1.setId(0L);
        constructionReport2.setProject(project1);
        constructionReport2.setState(ConstructionDiaryReportState.FINISHED);
        when(mockConstructionReportDao.save(constructionReport2)).thenReturn(constructionReport2);

        // Run the test
        final ConstructionReportDTO result = constructionReportImplUnderTest.update(request);

        // Verify the results
        assertThat(result).isNull();
        verify(mockConstructionReportDao).save(constructionReport2);
    }


    @Test
    void testUpdate_ConstructionReportRepositoryFindByIdReturnsAbsent() {
        // Setup
        final ConstructionReportRequest request = new ConstructionReportRequest();
        request.setId(0L);
        request.setTaskName("taskName");
        request.setNote("note");
        request.setWeather("weather");
        request.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setExecutorId(0L);
        request.setState(ConstructionDiaryReportState.FINISHED);
        request.setProjectId(0L);

        when(mockConstructionReportDao.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final ConstructionReportDTO result = constructionReportImplUnderTest.update(request);

        // Verify the results
        assertThat(result).isNull();
    }
}
