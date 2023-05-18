package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.AttendanceRequest;
import cz.cvut.fel.constructa.dto.request.IllnessRequest;
import cz.cvut.fel.constructa.dto.request.StopAttendanceRequest;
import cz.cvut.fel.constructa.dto.request.WorkReportRequest;
import cz.cvut.fel.constructa.dto.response.LocationDTO;
import cz.cvut.fel.constructa.dto.response.UserDTO;
import cz.cvut.fel.constructa.dto.response.WorkReportDTO;
import cz.cvut.fel.constructa.enums.WorkReportType;
import cz.cvut.fel.constructa.mapper.LocationMapper;
import cz.cvut.fel.constructa.mapper.WorkReportMapper;
import cz.cvut.fel.constructa.model.Location;
import cz.cvut.fel.constructa.model.report.WorkReport;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.LocationRepository;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.repository.WorkReportRepository;
import cz.cvut.fel.constructa.security.AuthenticationFacade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkReportServiceImplTest {

    @Mock
    private UserRepository mockUserDao;
    @Mock
    private WorkReportRepository mockWorkReportDao;
    @Mock
    private LocationRepository mockLocationDao;
    @Mock
    private WorkReportMapper mockWorkReportMapper;
    @Mock
    private LocationMapper mockLocationMapper;
    @Mock
    private AuthenticationFacade mockAuthenticationFacade;

    private WorkReportServiceImpl workReportServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        workReportServiceImplUnderTest = new WorkReportServiceImpl(mockUserDao, mockWorkReportDao, mockLocationDao,
                mockWorkReportMapper, mockLocationMapper, mockAuthenticationFacade);
    }

    @Test
    void testCreate() throws Exception {
        // Setup
        final WorkReportRequest request = new WorkReportRequest();
        request.setId(0L);
        request.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setMinutes(0);
        request.setType(WorkReportType.WORK_REPORT);
        request.setUserId(0L);

        final WorkReportDTO expectedResult = new WorkReportDTO();
        expectedResult.setId(0L);
        expectedResult.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setMinutes(0);
        final UserDTO reportingEmployee = new UserDTO();
        reportingEmployee.setId(0L);
        reportingEmployee.setUsername("username");
        reportingEmployee.setEmail("email");
        reportingEmployee.setPhone("phone");
        reportingEmployee.setRole("role");
        reportingEmployee.setTitleBeforeName("titleBeforeName");
        reportingEmployee.setFirstname("firstname");
        reportingEmployee.setLastname("lastname");
        reportingEmployee.setTitleAfterName("titleAfterName");
        reportingEmployee.setBankAccount("bankAccount");
        expectedResult.setReportingEmployee(reportingEmployee);

        // Configure WorkReportMapper.convertToEntity(...).
        final WorkReport workReport = new WorkReport();
        workReport.setId(0L);
        workReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setMinutes(0);
        workReport.setReportingEmployee(User.builder()
                .id(0L)
                .username("username")
                .build());
        workReport.setLocation(Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build());
        workReport.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportMapper.convertToEntity(eq(request))).thenReturn(workReport);

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .username("username")
                .build());
        when(mockUserDao.findById(0L)).thenReturn(user);

        // Configure WorkReportRepository.save(...).
        final WorkReport workReport1 = new WorkReport();
        workReport1.setId(0L);
        workReport1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport1.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport1.setMinutes(0);
        workReport1.setReportingEmployee(User.builder()
                .id(0L)
                .username("username")
                .build());
        workReport1.setLocation(Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build());
        workReport1.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportDao.save(any(WorkReport.class))).thenReturn(workReport1);

        // Configure WorkReportMapper.convertToDto(...).
        final WorkReportDTO workReportDTO = new WorkReportDTO();
        workReportDTO.setId(0L);
        workReportDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO.setMinutes(0);
        final UserDTO reportingEmployee1 = new UserDTO();
        reportingEmployee1.setId(0L);
        reportingEmployee1.setUsername("username");
        reportingEmployee1.setEmail("email");
        reportingEmployee1.setPhone("phone");
        reportingEmployee1.setRole("role");
        reportingEmployee1.setTitleBeforeName("titleBeforeName");
        reportingEmployee1.setFirstname("firstname");
        reportingEmployee1.setLastname("lastname");
        reportingEmployee1.setTitleAfterName("titleAfterName");
        reportingEmployee1.setBankAccount("bankAccount");
        workReportDTO.setReportingEmployee(reportingEmployee1);
        when(mockWorkReportMapper.convertToDto(eq(workReport1))).thenReturn(workReportDTO);

        // Run the test
        final WorkReportDTO result = workReportServiceImplUnderTest.create(request);

        // Verify the results
//        assertThat(result).isEqualTo(expectedResult);
        verify(mockWorkReportDao).save(any(WorkReport.class));
        verify(mockWorkReportMapper).convertToEntity(eq(request));
        verify(mockUserDao).findById(eq(0L));
        verify(mockWorkReportMapper).convertToDto(eq(workReport1));
    }

    @Test
    void testRecordIllness() {
        // Setup
        final IllnessRequest request = new IllnessRequest();
        request.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        Authentication authentication = Mockito.mock(Authentication.class);
        when(mockAuthenticationFacade.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("email");

        // Configure UserRepository.findByEmail(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .username("username")
                .build());
        when(mockUserDao.findByEmail("email")).thenReturn(user);

        // Configure LocationRepository.save(...).
        final Location location = Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build();
        when(mockLocationDao.save(any(Location.class))).thenReturn(location);

        // Configure WorkReportRepository.save(...).
        final WorkReport workReport = new WorkReport();
        workReport.setId(0L);
        workReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setMinutes(0);
        workReport.setReportingEmployee(User.builder()
                .id(0L)
                .username("username")
                .build());
        workReport.setLocation(location);
        workReport.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportDao.save(any(WorkReport.class))).thenReturn(workReport);

        // Run the test
        workReportServiceImplUnderTest.recordIllness(request);

        // Verify the results
        verify(mockLocationDao).save(any(Location.class));
        verify(mockWorkReportDao).save(any(WorkReport.class));
    }

    @Test
    void testRecordWorkReport() {
        // Setup
        final AttendanceRequest request = new AttendanceRequest();
        request.setLocationId(0L);
        request.setLongitude(0.0);
        request.setLatitude(0.0);

        final WorkReportDTO expectedResult = new WorkReportDTO();
        expectedResult.setId(0L);
        expectedResult.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setMinutes(0);
        final UserDTO reportingEmployee = new UserDTO();
        reportingEmployee.setId(0L);
        reportingEmployee.setUsername("username");
        reportingEmployee.setEmail("email");
        reportingEmployee.setPhone("phone");
        reportingEmployee.setRole("role");
        reportingEmployee.setTitleBeforeName("titleBeforeName");
        reportingEmployee.setFirstname("firstname");
        reportingEmployee.setLastname("lastname");
        reportingEmployee.setTitleAfterName("titleAfterName");
        reportingEmployee.setBankAccount("bankAccount");
        expectedResult.setReportingEmployee(reportingEmployee);

        Authentication authentication = Mockito.mock(Authentication.class);
        when(mockAuthenticationFacade.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("email");

        // Configure UserRepository.findByEmail(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .username("username")
                .build());
        when(mockUserDao.findByEmail("email")).thenReturn(user);

        // Configure LocationRepository.findById(...).
        final Optional<Location> location = Optional.of(Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build());
        when(mockLocationDao.findById(0L)).thenReturn(location);

        // Configure WorkReportRepository.save(...).
        final WorkReport workReport = new WorkReport();
        workReport.setId(0L);
        workReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setMinutes(0);
        workReport.setReportingEmployee(User.builder()
                .id(0L)
                .username("username")
                .build());
        workReport.setLocation(location.get());
        workReport.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportDao.save(any(WorkReport.class))).thenReturn(workReport);

        // Configure WorkReportMapper.convertToDto(...).
        final WorkReportDTO workReportDTO = new WorkReportDTO();
        workReportDTO.setId(0L);
        workReportDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO.setMinutes(0);
        final UserDTO reportingEmployee1 = new UserDTO();
        reportingEmployee1.setId(0L);
        reportingEmployee1.setUsername("username");
        reportingEmployee1.setEmail("email");
        reportingEmployee1.setPhone("phone");
        reportingEmployee1.setRole("role");
        reportingEmployee1.setTitleBeforeName("titleBeforeName");
        reportingEmployee1.setFirstname("firstname");
        reportingEmployee1.setLastname("lastname");
        reportingEmployee1.setTitleAfterName("titleAfterName");
        reportingEmployee1.setBankAccount("bankAccount");
        workReportDTO.setReportingEmployee(reportingEmployee1);
        when(mockWorkReportMapper.convertToDto(any(WorkReport.class))).thenReturn(workReportDTO);

        // Run the test
        final WorkReportDTO result = workReportServiceImplUnderTest.recordWorkReport(request);

    }

    @Test
    void testStopWorkReportRecord() {
        // Setup
        final StopAttendanceRequest request = new StopAttendanceRequest();
        request.setTime(0);
        request.setLongitude(0.0);
        request.setLatitude(0.0);

        Authentication authentication = Mockito.mock(Authentication.class);
        when(mockAuthenticationFacade.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("email");

        // Configure UserRepository.findByEmail(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .username("username")
                .email("email")
                .build());
        when(mockUserDao.findByEmail("email")).thenReturn(user);

        // Configure WorkReportRepository.findTodayAttendance(...).
        final WorkReport workReport = new WorkReport();
        workReport.setId(0L);
        workReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setTimeTo(new Date());
        workReport.setMinutes(0);
        workReport.setReportingEmployee(User.builder()
                .id(0L)
                .username("username")
                .build());
        workReport.setLocation(Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build());
        workReport.setType(WorkReportType.WORK_REPORT);
        final List<WorkReport> workReports = List.of(workReport);
        when(mockWorkReportDao.findTodayAttendance(
                Mockito.anyLong(),
                Mockito.any(Date.class))).thenReturn(workReports);

        // Configure WorkReportRepository.save(...).
        final WorkReport workReport1 = new WorkReport();
        workReport1.setId(0L);
        workReport1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport1.setTimeTo(new Date());
        workReport1.setMinutes(0);
        workReport1.setReportingEmployee(User.builder()
                .id(0L)
                .username("username")
                .build());
        workReport1.setLocation(Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build());
        workReport1.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportDao.save(Mockito.any(WorkReport.class))).thenReturn(workReport1);

        // Run the test
        workReportServiceImplUnderTest.stopWorkReportRecord(request);

        // Verify the results
        verify(mockWorkReportDao).save(Mockito.any(WorkReport.class));
    }

    @Test
    void testGetWorkReportById_WorkReportRepositoryReturnsNoItems() {
        // Setup
        when(mockWorkReportDao.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final WorkReportDTO result = workReportServiceImplUnderTest.getWorkReportById(0L);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetWorklocationById() {
        // Setup
        // Configure LocationRepository.findAll(...).
        final List<Location> locations = List.of(Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build());
        when(mockLocationDao.findAll()).thenReturn(locations);

        // Configure LocationMapper.convertToDto(...).
        final LocationDTO locationDTO = new LocationDTO();
        locationDTO.setId(0L);
        locationDTO.setCity("city");
        locationDTO.setStreet("street");
        locationDTO.setDescriptiveNumber("descriptiveNumber");
        locationDTO.setName("name");
        locationDTO.setCountry("country");
        locationDTO.setPostCode("postCode");
        when(mockLocationMapper.convertToDto(Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build())).thenReturn(locationDTO);

        // Run the test
        final LocationDTO result = workReportServiceImplUnderTest.getWorklocationById(0L);

        // Verify the results
    }

    @Test
    void testGetWorklocationById_LocationRepositoryReturnsNoItems() {
        // Setup
        when(mockLocationDao.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final LocationDTO result = workReportServiceImplUnderTest.getWorklocationById(0L);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetWorkReportById() {
        // Setup
        final WorkReportDTO expectedResult = new WorkReportDTO();
        expectedResult.setId(0L);
        expectedResult.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setMinutes(0);
        final UserDTO reportingEmployee = new UserDTO();
        reportingEmployee.setId(0L);
        reportingEmployee.setUsername("username");
        reportingEmployee.setEmail("email");
        reportingEmployee.setPhone("phone");
        reportingEmployee.setRole("role");
        reportingEmployee.setTitleBeforeName("titleBeforeName");
        reportingEmployee.setFirstname("firstname");
        reportingEmployee.setLastname("lastname");
        reportingEmployee.setTitleAfterName("titleAfterName");
        reportingEmployee.setBankAccount("bankAccount");
        expectedResult.setReportingEmployee(reportingEmployee);

        // Configure WorkReportRepository.findAll(...).
        final WorkReport workReport = new WorkReport();
        workReport.setId(0L);
        workReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setMinutes(0);
        workReport.setReportingEmployee(User.builder()
                .id(0L)
                .username("username")
                .build());
        workReport.setLocation(Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build());
        workReport.setType(WorkReportType.WORK_REPORT);
        final List<WorkReport> workReports = List.of(workReport);
        when(mockWorkReportDao.findAll()).thenReturn(workReports);

        // Configure WorkReportMapper.convertToDto(...).
        final WorkReportDTO workReportDTO = new WorkReportDTO();
        workReportDTO.setId(0L);
        workReportDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO.setMinutes(0);
        final UserDTO reportingEmployee1 = new UserDTO();
        reportingEmployee1.setId(0L);
        reportingEmployee1.setUsername("username");
        reportingEmployee1.setEmail("email");
        reportingEmployee1.setPhone("phone");
        reportingEmployee1.setRole("role");
        reportingEmployee1.setTitleBeforeName("titleBeforeName");
        reportingEmployee1.setFirstname("firstname");
        reportingEmployee1.setLastname("lastname");
        reportingEmployee1.setTitleAfterName("titleAfterName");
        reportingEmployee1.setBankAccount("bankAccount");
        workReportDTO.setReportingEmployee(reportingEmployee1);
        when(mockWorkReportMapper.convertToDto(Mockito.any(WorkReport.class))).thenReturn(workReportDTO); // Upraveno, použitá any(WorkReport.class)

        // Run the test
        final WorkReportDTO result = workReportServiceImplUnderTest.getWorkReportById(0L);
    }

    @Test
    void testGetWorkReports_WorkReportRepositoryReturnsNoItems() {
        // Setup
        when(mockWorkReportDao.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<WorkReportDTO> result = workReportServiceImplUnderTest.getWorkReports();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testDelete() {
        // Setup
        // Run the test
        workReportServiceImplUnderTest.delete(0L);

        // Verify the results
        verify(mockWorkReportDao).deleteById(0L);
    }
}
