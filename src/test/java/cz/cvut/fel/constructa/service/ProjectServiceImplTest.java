package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.ProjectRequest;
import cz.cvut.fel.constructa.dto.response.ProjectDTO;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.enums.ExternalistRole;
import cz.cvut.fel.constructa.enums.ProjectState;
import cz.cvut.fel.constructa.mapper.ProjectMapper;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.Project;
import cz.cvut.fel.constructa.model.report.ConstructionReport;
import cz.cvut.fel.constructa.model.role.ExternalistInProject;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.LocationRepository;
import cz.cvut.fel.constructa.repository.ProjectRepository;
import cz.cvut.fel.constructa.repository.UserRepository;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceImplTest {

    @Mock
    private ProjectRepository mockProjectDao;
    @Mock
    private UserRepository mockUserDao;
    @Mock
    private LocationRepository mockLocationDao;
    @Mock
    private ProjectMapper mockProjectMapper;

    private ProjectServiceImpl projectServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        projectServiceImplUnderTest = new ProjectServiceImpl(mockProjectDao, mockUserDao, mockLocationDao,
                mockProjectMapper);
    }

    @Test
    void testCreate() throws Exception {
        // Setup
        final ProjectRequest request = ProjectRequest.builder()
                .id(0L)
                .userId(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build();

        // Configure ProjectMapper.convertToEntity(...).
        final Project project = new Project();
        project.setId(0L);
        project.setName("name");
        project.setBuldingFacility("buldingFacility");
        project.setStartedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project.setDeadline(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project.setState(ProjectState.FINISHED);
        project.setProjectManager(User.builder().build());
        final ExternalistInProject externalistInProject = new ExternalistInProject();
        externalistInProject.setId(0L);
        externalistInProject.setRoles(List.of(ExternalistRole.CLIENT));
        externalistInProject.setExternalist(User.builder().build());
        project.setExternalWorkers(List.of(externalistInProject));
        project.setProjectAddress(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
        final ConstructionReport constructionReport = new ConstructionReport();
        constructionReport.setId(0L);
        constructionReport.setTaskName("taskName");
        project.setConstructionReports(List.of(constructionReport));
        when(mockProjectMapper.convertToEntity(any(ProjectRequest.class))).thenReturn(project);

        when(mockUserDao.findById(0L)).thenReturn(Optional.of(User.builder().build()));

        // Configure LocationRepository.save(...).
        final Location location = Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build();
        when(mockLocationDao.save(any(Location.class))).thenReturn(location);

        // Configure ProjectRepository.save(...).
        final Project project1 = new Project();
        project1.setId(0L);
        project1.setName("name");
        project1.setBuldingFacility("buldingFacility");
        project1.setStartedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project1.setDeadline(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project1.setState(ProjectState.FINISHED);
        project1.setProjectManager(User.builder().build());
        final ExternalistInProject externalistInProject1 = new ExternalistInProject();
        externalistInProject1.setId(0L);
        externalistInProject1.setRoles(List.of(ExternalistRole.CLIENT));
        externalistInProject1.setExternalist(User.builder().build());
        project1.setExternalWorkers(List.of(externalistInProject1));
        project1.setProjectAddress(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
        final ConstructionReport constructionReport1 = new ConstructionReport();
        constructionReport1.setId(0L);
        constructionReport1.setTaskName("taskName");
        project1.setConstructionReports(List.of(constructionReport1));
        when(mockProjectDao.save(project)).thenReturn(project1);
        final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(0L);
        projectDTO.setName("name");
        projectDTO.setBuldingFacility("buldingFacility");
        projectDTO.setState("state");
        final UserDTO projectManager = new UserDTO();
        projectManager.setId(0L);
        projectManager.setUsername("username");
        projectManager.setEmail("email");
        projectManager.setPhone("phone");
        projectManager.setRole("role");
        projectManager.setTitleBeforeName("titleBeforeName");
        projectManager.setFirstname("firstname");
        projectManager.setLastname("lastname");
        projectManager.setTitleAfterName("titleAfterName");
        projectManager.setBankAccount("bankAccount");
        projectDTO.setProjectManager(projectManager);
        when(mockProjectMapper.convertToDto(any(Project.class))).thenReturn(projectDTO);

      // Run the test
        final ProjectDTO result = projectServiceImplUnderTest.create(request);

     // Verify the results
        verify(mockLocationDao).save(any(Location.class));
        verify(mockProjectDao).save(any(Project.class));
    }


    @Test
    void testCreate_ProjectMapperConvertToEntityThrowsParseException() throws Exception {
        // Setup
        final ProjectRequest request = ProjectRequest.builder()
                .id(0L)
                .userId(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build();
        when(mockProjectMapper.convertToEntity(ProjectRequest.builder()
                .id(0L)
                .userId(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build())).thenThrow(ParseException.class);

        // Run the test
        assertThatThrownBy(() -> projectServiceImplUnderTest.create(request)).isInstanceOf(ParseException.class);
    }

    @Test
    void testGetProjectById() {
        // Setup
        // Configure ProjectRepository.findById(...).
        final Project project1 = new Project();
        project1.setId(0L);
        project1.setName("name");
        project1.setBuldingFacility("buldingFacility");
        project1.setStartedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project1.setDeadline(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project1.setState(ProjectState.FINISHED);
        project1.setProjectManager(User.builder().build());
        final ExternalistInProject externalistInProject = new ExternalistInProject();
        externalistInProject.setId(0L);
        externalistInProject.setRoles(List.of(ExternalistRole.CLIENT));
        externalistInProject.setExternalist(User.builder().build());
        project1.setExternalWorkers(List.of(externalistInProject));
        project1.setProjectAddress(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
        final ConstructionReport constructionReport = new ConstructionReport();
        constructionReport.setId(0L);
        constructionReport.setTaskName("taskName");
        project1.setConstructionReports(List.of(constructionReport));
        final Optional<Project> project = Optional.of(project1);
        when(mockProjectDao.findById(0L)).thenReturn(project);

        // Configure ProjectMapper.convertToDto(...).
        final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(0L);
        projectDTO.setName("name");
        projectDTO.setBuldingFacility("buldingFacility");
        projectDTO.setState("state");
        final UserDTO projectManager = new UserDTO();
        projectManager.setId(0L);
        projectManager.setUsername("username");
        projectManager.setEmail("email");
        projectManager.setPhone("phone");
        projectManager.setRole("role");
        projectManager.setTitleBeforeName("titleBeforeName");
        projectManager.setFirstname("firstname");
        projectManager.setLastname("lastname");
        projectManager.setTitleAfterName("titleAfterName");
        projectManager.setBankAccount("bankAccount");
        projectDTO.setProjectManager(projectManager);
        when(mockProjectMapper.convertToDto(eq(project1))).thenReturn(projectDTO);

        // Run the test
        final ProjectDTO result = projectServiceImplUnderTest.getProjectById(0L);

        // Verify the results
        verify(mockProjectDao).findById(0L);

    }


    @Test
    void testGetProjectById_ProjectRepositoryReturnsAbsent() {
        // Setup
        when(mockProjectDao.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final ProjectDTO result = projectServiceImplUnderTest.getProjectById(0L);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetProjects() {
        // Setup
        // Configure ProjectRepository.findAll(...).
        final Project project = new Project();
        project.setId(0L);
        project.setName("name");
        project.setBuldingFacility("buldingFacility");
        project.setStartedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project.setDeadline(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project.setState(ProjectState.FINISHED);
        project.setProjectManager(User.builder().build());
        final ExternalistInProject externalistInProject = new ExternalistInProject();
        externalistInProject.setId(0L);
        externalistInProject.setRoles(List.of(ExternalistRole.CLIENT));
        externalistInProject.setExternalist(User.builder().build());
        project.setExternalWorkers(List.of(externalistInProject));
        project.setProjectAddress(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
        final ConstructionReport constructionReport = new ConstructionReport();
        constructionReport.setId(0L);
        constructionReport.setTaskName("taskName");
        project.setConstructionReports(List.of(constructionReport));
        final List<Project> projects = List.of(project);
        when(mockProjectDao.findAll(Sort.by("name").ascending())).thenReturn(projects);

        // Configure ProjectMapper.convertToDto(...).
        final ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(0L);
        projectDTO.setName("name");
        projectDTO.setBuldingFacility("buldingFacility");
        projectDTO.setState("state");
        final UserDTO projectManager = new UserDTO();
        projectManager.setId(0L);
        projectManager.setUsername("username");
        projectManager.setEmail("email");
        projectManager.setPhone("phone");
        projectManager.setRole("role");
        projectManager.setTitleBeforeName("titleBeforeName");
        projectManager.setFirstname("firstname");
        projectManager.setLastname("lastname");
        projectManager.setTitleAfterName("titleAfterName");
        projectManager.setBankAccount("bankAccount");
        projectDTO.setProjectManager(projectManager);
        when(mockProjectMapper.convertToDto(eq(project))).thenReturn(projectDTO);

        // Run the test
        final List<ProjectDTO> result = projectServiceImplUnderTest.getProjects();

        // Verify the results
        verify(mockProjectDao).findAll(Sort.by("name").ascending());
        verify(mockProjectMapper).convertToDto(eq(project));
        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        assertThat(result.get(0)).isEqualTo(projectDTO);
    }


    @Test
    void testGetProjects_ProjectRepositoryReturnsNoItems() {
        // Setup
        when(mockProjectDao.findAll(Sort.by("name").ascending())).thenReturn(Collections.emptyList());

        // Run the test
        final List<ProjectDTO> result = projectServiceImplUnderTest.getProjects();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testUpdate_ProjectMapperConvertToEntityThrowsParseException() throws Exception {
        // Setup
        final ProjectRequest request = ProjectRequest.builder()
                .id(0L)
                .userId(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build();

        // Configure ProjectRepository.findById(...).
        final Project project1 = new Project();
        project1.setId(0L);
        project1.setName("name");
        project1.setBuldingFacility("buldingFacility");
        project1.setStartedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project1.setDeadline(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        project1.setState(ProjectState.FINISHED);
        project1.setProjectManager(User.builder().build());
        final ExternalistInProject externalistInProject = new ExternalistInProject();
        externalistInProject.setId(0L);
        externalistInProject.setRoles(List.of(ExternalistRole.CLIENT));
        externalistInProject.setExternalist(User.builder().build());
        project1.setExternalWorkers(List.of(externalistInProject));
        project1.setProjectAddress(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
        final ConstructionReport constructionReport = new ConstructionReport();
        constructionReport.setId(0L);
        constructionReport.setTaskName("taskName");
        project1.setConstructionReports(List.of(constructionReport));
        final Optional<Project> project = Optional.of(project1);
        when(mockProjectDao.findById(0L)).thenReturn(project);

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

        when(mockProjectMapper.convertToEntity(ProjectRequest.builder()
                .id(0L)
                .userId(0L)
                .country("country")
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .postCode("postCode")
                .build())).thenThrow(ParseException.class);

        // Run the test
        assertThatThrownBy(() -> projectServiceImplUnderTest.update(request)).isInstanceOf(ParseException.class);
        verify(mockLocationDao).save(Location.builder()
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .build());
    }

    @Test
    void testDelete() {
        // Setup
        // Run the test
        projectServiceImplUnderTest.delete(0L);

        // Verify the results
        verify(mockProjectDao).deleteById(0L);
    }
}
