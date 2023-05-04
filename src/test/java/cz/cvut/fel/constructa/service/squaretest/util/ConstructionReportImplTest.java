package cz.cvut.fel.constructa.service.squaretest.util;

import cz.cvut.fel.constructa.dto.request.ConstructionReportRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionReportDTO;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.enums.ConstructionDiaryReportState;
import cz.cvut.fel.constructa.enums.ExternalistRole;
import cz.cvut.fel.constructa.enums.MultimediaType;
import cz.cvut.fel.constructa.enums.ProjectState;
import cz.cvut.fel.constructa.mapper.ConstructionReportMapper;
import cz.cvut.fel.constructa.model.Document;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.Project;
import cz.cvut.fel.constructa.model.report.ConstructionReport;
import cz.cvut.fel.constructa.model.role.ExternalistInProject;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.ConstructionReportRepository;
import cz.cvut.fel.constructa.repository.ProjectRepository;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.security.AuthenticationFacade;
import cz.cvut.fel.constructa.service.ConstructionReportImpl;
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
        final Document document = new Document();
        document.setId(0L);
        document.setLink("link");
        document.setConstructionReports(Set.of(new ConstructionReport()));
        document.setEmployeeDocuments(Set.of(User.builder().build()));
        document.setType(MultimediaType.PHOTO);
        constructionReport.setConstructionDocumentLinks(Set.of(document));
        constructionReport.setExecutor(User.builder().build());
        final Project project = new Project();
        project.setId(0L);
        project.setName("name");
        constructionReport.setProject(project);
        when(mockConstructionReportMapper.convertToEntity(new ConstructionReportRequest()))
                .thenReturn(constructionReport);

        when(mockUserDao.findById(0L)).thenReturn(Optional.of(User.builder().build()));

        // Configure ProjectRepository.findById(...).
        final Project project2 = new Project();
        project2.setId(0L);
        project2.setName("name");
        project2.setBuldingFacility("buldingFacility");
        project2.setStartedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project2.setDeadline(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project2.setState(ProjectState.FINISHED);
        project2.setProjectManager(User.builder().build());
        final ExternalistInProject externalistInProject = new ExternalistInProject();
        externalistInProject.setId(0L);
        externalistInProject.setRoles(List.of(ExternalistRole.CLIENT));
        externalistInProject.setExternalist(User.builder().build());
        project2.setExternalWorkers(List.of(externalistInProject));
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
        final Document document1 = new Document();
        document1.setId(0L);
        document1.setLink("link");
        document1.setConstructionReports(Set.of(new ConstructionReport()));
        document1.setEmployeeDocuments(Set.of(User.builder().build()));
        document1.setType(MultimediaType.PHOTO);
        constructionReport2.setConstructionDocumentLinks(Set.of(document1));
        constructionReport2.setExecutor(User.builder().build());
        final Project project3 = new Project();
        project3.setId(0L);
        project3.setName("name");
        constructionReport2.setProject(project3);
        when(mockConstructionReportDao.save(new ConstructionReport())).thenReturn(constructionReport2);

        // Configure ConstructionReportMapper.convertToDto(...).
        final ConstructionReportDTO constructionReportDTO = new ConstructionReportDTO();
        constructionReportDTO.setId(0L);
        constructionReportDTO.setTaskName("taskName");
        constructionReportDTO.setNote("note");
        constructionReportDTO.setWeather("weather");
        constructionReportDTO.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO executor1 = new UserDTO();
        executor1.setId(0L);
        executor1.setUsername("username");
        executor1.setEmail("email");
        executor1.setPhone("phone");
        executor1.setRole("role");
        executor1.setTitleBeforeName("titleBeforeName");
        executor1.setFirstname("firstname");
        executor1.setLastname("lastname");
        executor1.setTitleAfterName("titleAfterName");
        constructionReportDTO.setExecutor(executor1);
        when(mockConstructionReportMapper.convertToDto(new ConstructionReport())).thenReturn(constructionReportDTO);

        // Run the test
        final ConstructionReportDTO result = constructionReportImplUnderTest.create(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testCreate_ConstructionReportMapperConvertToEntityThrowsParseException() throws Exception {
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

        when(mockConstructionReportMapper.convertToEntity(new ConstructionReportRequest()))
                .thenThrow(ParseException.class);

        // Run the test
        assertThatThrownBy(() -> constructionReportImplUnderTest.create(request)).isInstanceOf(ParseException.class);
    }

    @Test
    void testCreate_UserRepositoryFindByIdReturnsAbsent() throws Exception {
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
        final Document document = new Document();
        document.setId(0L);
        document.setLink("link");
        document.setConstructionReports(Set.of(new ConstructionReport()));
        document.setEmployeeDocuments(Set.of(User.builder().build()));
        document.setType(MultimediaType.PHOTO);
        constructionReport.setConstructionDocumentLinks(Set.of(document));
        constructionReport.setExecutor(User.builder().build());
        final Project project = new Project();
        project.setId(0L);
        project.setName("name");
        constructionReport.setProject(project);
        when(mockConstructionReportMapper.convertToEntity(new ConstructionReportRequest()))
                .thenReturn(constructionReport);

        when(mockUserDao.findById(0L)).thenReturn(Optional.empty());
        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);
        when(mockUserDao.findByEmail("email")).thenReturn(Optional.of(User.builder().build()));

        // Configure ProjectRepository.findById(...).
        final Project project2 = new Project();
        project2.setId(0L);
        project2.setName("name");
        project2.setBuldingFacility("buldingFacility");
        project2.setStartedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project2.setDeadline(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project2.setState(ProjectState.FINISHED);
        project2.setProjectManager(User.builder().build());
        final ExternalistInProject externalistInProject = new ExternalistInProject();
        externalistInProject.setId(0L);
        externalistInProject.setRoles(List.of(ExternalistRole.CLIENT));
        externalistInProject.setExternalist(User.builder().build());
        project2.setExternalWorkers(List.of(externalistInProject));
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
        final Document document1 = new Document();
        document1.setId(0L);
        document1.setLink("link");
        document1.setConstructionReports(Set.of(new ConstructionReport()));
        document1.setEmployeeDocuments(Set.of(User.builder().build()));
        document1.setType(MultimediaType.PHOTO);
        constructionReport2.setConstructionDocumentLinks(Set.of(document1));
        constructionReport2.setExecutor(User.builder().build());
        final Project project3 = new Project();
        project3.setId(0L);
        project3.setName("name");
        constructionReport2.setProject(project3);
        when(mockConstructionReportDao.save(new ConstructionReport())).thenReturn(constructionReport2);

        // Configure ConstructionReportMapper.convertToDto(...).
        final ConstructionReportDTO constructionReportDTO = new ConstructionReportDTO();
        constructionReportDTO.setId(0L);
        constructionReportDTO.setTaskName("taskName");
        constructionReportDTO.setNote("note");
        constructionReportDTO.setWeather("weather");
        constructionReportDTO.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO executor1 = new UserDTO();
        executor1.setId(0L);
        executor1.setUsername("username");
        executor1.setEmail("email");
        executor1.setPhone("phone");
        executor1.setRole("role");
        executor1.setTitleBeforeName("titleBeforeName");
        executor1.setFirstname("firstname");
        executor1.setLastname("lastname");
        executor1.setTitleAfterName("titleAfterName");
        constructionReportDTO.setExecutor(executor1);
        when(mockConstructionReportMapper.convertToDto(new ConstructionReport())).thenReturn(constructionReportDTO);

        // Run the test
        final ConstructionReportDTO result = constructionReportImplUnderTest.create(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testCreate_UserRepositoryFindByEmailReturnsAbsent() throws Exception {
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
        final Document document = new Document();
        document.setId(0L);
        document.setLink("link");
        document.setConstructionReports(Set.of(new ConstructionReport()));
        document.setEmployeeDocuments(Set.of(User.builder().build()));
        document.setType(MultimediaType.PHOTO);
        constructionReport.setConstructionDocumentLinks(Set.of(document));
        constructionReport.setExecutor(User.builder().build());
        final Project project = new Project();
        project.setId(0L);
        project.setName("name");
        constructionReport.setProject(project);
        when(mockConstructionReportMapper.convertToEntity(new ConstructionReportRequest()))
                .thenReturn(constructionReport);

        when(mockUserDao.findById(0L)).thenReturn(Optional.empty());
        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);
        when(mockUserDao.findByEmail("email")).thenReturn(Optional.empty());

        // Configure ProjectRepository.findById(...).
        final Project project2 = new Project();
        project2.setId(0L);
        project2.setName("name");
        project2.setBuldingFacility("buldingFacility");
        project2.setStartedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project2.setDeadline(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project2.setState(ProjectState.FINISHED);
        project2.setProjectManager(User.builder().build());
        final ExternalistInProject externalistInProject = new ExternalistInProject();
        externalistInProject.setId(0L);
        externalistInProject.setRoles(List.of(ExternalistRole.CLIENT));
        externalistInProject.setExternalist(User.builder().build());
        project2.setExternalWorkers(List.of(externalistInProject));
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
        final Document document1 = new Document();
        document1.setId(0L);
        document1.setLink("link");
        document1.setConstructionReports(Set.of(new ConstructionReport()));
        document1.setEmployeeDocuments(Set.of(User.builder().build()));
        document1.setType(MultimediaType.PHOTO);
        constructionReport2.setConstructionDocumentLinks(Set.of(document1));
        constructionReport2.setExecutor(User.builder().build());
        final Project project3 = new Project();
        project3.setId(0L);
        project3.setName("name");
        constructionReport2.setProject(project3);
        when(mockConstructionReportDao.save(new ConstructionReport())).thenReturn(constructionReport2);

        // Configure ConstructionReportMapper.convertToDto(...).
        final ConstructionReportDTO constructionReportDTO = new ConstructionReportDTO();
        constructionReportDTO.setId(0L);
        constructionReportDTO.setTaskName("taskName");
        constructionReportDTO.setNote("note");
        constructionReportDTO.setWeather("weather");
        constructionReportDTO.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO executor1 = new UserDTO();
        executor1.setId(0L);
        executor1.setUsername("username");
        executor1.setEmail("email");
        executor1.setPhone("phone");
        executor1.setRole("role");
        executor1.setTitleBeforeName("titleBeforeName");
        executor1.setFirstname("firstname");
        executor1.setLastname("lastname");
        executor1.setTitleAfterName("titleAfterName");
        constructionReportDTO.setExecutor(executor1);
        when(mockConstructionReportMapper.convertToDto(new ConstructionReport())).thenReturn(constructionReportDTO);

        // Run the test
        final ConstructionReportDTO result = constructionReportImplUnderTest.create(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testCreate_ProjectRepositoryReturnsAbsent() throws Exception {
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
        final Document document = new Document();
        document.setId(0L);
        document.setLink("link");
        document.setConstructionReports(Set.of(new ConstructionReport()));
        document.setEmployeeDocuments(Set.of(User.builder().build()));
        document.setType(MultimediaType.PHOTO);
        constructionReport.setConstructionDocumentLinks(Set.of(document));
        constructionReport.setExecutor(User.builder().build());
        final Project project = new Project();
        project.setId(0L);
        project.setName("name");
        constructionReport.setProject(project);
        when(mockConstructionReportMapper.convertToEntity(new ConstructionReportRequest()))
                .thenReturn(constructionReport);

        when(mockUserDao.findById(0L)).thenReturn(Optional.of(User.builder().build()));
        when(mockProjectDao.findById(0L)).thenReturn(Optional.empty());

        // Configure ConstructionReportRepository.save(...).
        final ConstructionReport constructionReport1 = new ConstructionReport();
        constructionReport1.setId(0L);
        constructionReport1.setTaskName("taskName");
        constructionReport1.setNote("note");
        constructionReport1.setWeather("weather");
        constructionReport1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final Document document1 = new Document();
        document1.setId(0L);
        document1.setLink("link");
        document1.setConstructionReports(Set.of(new ConstructionReport()));
        document1.setEmployeeDocuments(Set.of(User.builder().build()));
        document1.setType(MultimediaType.PHOTO);
        constructionReport1.setConstructionDocumentLinks(Set.of(document1));
        constructionReport1.setExecutor(User.builder().build());
        final Project project1 = new Project();
        project1.setId(0L);
        project1.setName("name");
        constructionReport1.setProject(project1);
        when(mockConstructionReportDao.save(new ConstructionReport())).thenReturn(constructionReport1);

        // Configure ConstructionReportMapper.convertToDto(...).
        final ConstructionReportDTO constructionReportDTO = new ConstructionReportDTO();
        constructionReportDTO.setId(0L);
        constructionReportDTO.setTaskName("taskName");
        constructionReportDTO.setNote("note");
        constructionReportDTO.setWeather("weather");
        constructionReportDTO.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO executor1 = new UserDTO();
        executor1.setId(0L);
        executor1.setUsername("username");
        executor1.setEmail("email");
        executor1.setPhone("phone");
        executor1.setRole("role");
        executor1.setTitleBeforeName("titleBeforeName");
        executor1.setFirstname("firstname");
        executor1.setLastname("lastname");
        executor1.setTitleAfterName("titleAfterName");
        constructionReportDTO.setExecutor(executor1);
        when(mockConstructionReportMapper.convertToDto(new ConstructionReport())).thenReturn(constructionReportDTO);

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
        final Document document = new Document();
        document.setId(0L);
        document.setLink("link");
        document.setConstructionReports(Set.of(new ConstructionReport()));
        document.setEmployeeDocuments(Set.of(User.builder().build()));
        document.setType(MultimediaType.PHOTO);
        constructionReport1.setConstructionDocumentLinks(Set.of(document));
        constructionReport1.setExecutor(User.builder().build());
        final Project project = new Project();
        project.setId(0L);
        project.setName("name");
        constructionReport1.setProject(project);
        final Optional<ConstructionReport> constructionReport = Optional.of(constructionReport1);
        when(mockConstructionReportDao.findById(0L)).thenReturn(constructionReport);

        // Configure ConstructionReportMapper.convertToDto(...).
        final ConstructionReportDTO constructionReportDTO = new ConstructionReportDTO();
        constructionReportDTO.setId(0L);
        constructionReportDTO.setTaskName("taskName");
        constructionReportDTO.setNote("note");
        constructionReportDTO.setWeather("weather");
        constructionReportDTO.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO executor1 = new UserDTO();
        executor1.setId(0L);
        executor1.setUsername("username");
        executor1.setEmail("email");
        executor1.setPhone("phone");
        executor1.setRole("role");
        executor1.setTitleBeforeName("titleBeforeName");
        executor1.setFirstname("firstname");
        executor1.setLastname("lastname");
        executor1.setTitleAfterName("titleAfterName");
        constructionReportDTO.setExecutor(executor1);
        when(mockConstructionReportMapper.convertToDto(new ConstructionReport())).thenReturn(constructionReportDTO);

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
        final Document document = new Document();
        document.setId(0L);
        document.setLink("link");
        document.setConstructionReports(Set.of(new ConstructionReport()));
        document.setEmployeeDocuments(Set.of(User.builder().build()));
        document.setType(MultimediaType.PHOTO);
        constructionReport.setConstructionDocumentLinks(Set.of(document));
        constructionReport.setExecutor(User.builder().build());
        final Project project = new Project();
        project.setId(0L);
        project.setName("name");
        constructionReport.setProject(project);
        final List<ConstructionReport> constructionReports = List.of(constructionReport);
        when(mockConstructionReportDao.findAll(Sort.by("properties"))).thenReturn(constructionReports);

        // Configure ConstructionReportMapper.convertToDto(...).
        final ConstructionReportDTO constructionReportDTO1 = new ConstructionReportDTO();
        constructionReportDTO1.setId(0L);
        constructionReportDTO1.setTaskName("taskName");
        constructionReportDTO1.setNote("note");
        constructionReportDTO1.setWeather("weather");
        constructionReportDTO1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO executor1 = new UserDTO();
        executor1.setId(0L);
        executor1.setUsername("username");
        executor1.setEmail("email");
        executor1.setPhone("phone");
        executor1.setRole("role");
        executor1.setTitleBeforeName("titleBeforeName");
        executor1.setFirstname("firstname");
        executor1.setLastname("lastname");
        executor1.setTitleAfterName("titleAfterName");
        constructionReportDTO1.setExecutor(executor1);
        when(mockConstructionReportMapper.convertToDto(new ConstructionReport())).thenReturn(constructionReportDTO1);

        // Run the test
        final List<ConstructionReportDTO> result = constructionReportImplUnderTest.getConstructionReports();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetConstructionReports_ConstructionReportRepositoryReturnsNoItems() {
        // Setup
        when(mockConstructionReportDao.findAll(Sort.by("properties"))).thenReturn(Collections.emptyList());

        // Run the test
        final List<ConstructionReportDTO> result = constructionReportImplUnderTest.getConstructionReports();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
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
        final Document document = new Document();
        document.setId(0L);
        document.setLink("link");
        document.setConstructionReports(Set.of(new ConstructionReport()));
        document.setEmployeeDocuments(Set.of(User.builder().build()));
        document.setType(MultimediaType.PHOTO);
        constructionReport.setConstructionDocumentLinks(Set.of(document));
        constructionReport.setExecutor(User.builder().build());
        final Project project = new Project();
        project.setId(0L);
        project.setName("name");
        constructionReport.setProject(project);
        final List<ConstructionReport> constructionReports = List.of(constructionReport);
        when(mockConstructionReportDao.findAllByProjectId(0L, Sort.by("properties"))).thenReturn(constructionReports);

        // Configure ConstructionReportMapper.convertToDto(...).
        final ConstructionReportDTO constructionReportDTO1 = new ConstructionReportDTO();
        constructionReportDTO1.setId(0L);
        constructionReportDTO1.setTaskName("taskName");
        constructionReportDTO1.setNote("note");
        constructionReportDTO1.setWeather("weather");
        constructionReportDTO1.setDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        final UserDTO executor1 = new UserDTO();
        executor1.setId(0L);
        executor1.setUsername("username");
        executor1.setEmail("email");
        executor1.setPhone("phone");
        executor1.setRole("role");
        executor1.setTitleBeforeName("titleBeforeName");
        executor1.setFirstname("firstname");
        executor1.setLastname("lastname");
        executor1.setTitleAfterName("titleAfterName");
        constructionReportDTO1.setExecutor(executor1);
        when(mockConstructionReportMapper.convertToDto(new ConstructionReport())).thenReturn(constructionReportDTO1);

        // Run the test
        final List<ConstructionReportDTO> result = constructionReportImplUnderTest.getConstructionReportsByProjectId(
                0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetConstructionReportsByProjectId_ConstructionReportRepositoryReturnsNoItems() {
        // Setup
        when(mockConstructionReportDao.findAllByProjectId(0L, Sort.by("properties")))
                .thenReturn(Collections.emptyList());

        // Run the test
        final List<ConstructionReportDTO> result = constructionReportImplUnderTest.getConstructionReportsByProjectId(
                0L);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
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
        assertThat(constructionReportImplUnderTest.update(new ConstructionReportRequest())).isNull();
    }
}
