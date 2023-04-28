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
import cz.cvut.fel.constructa.model.report.FinanceReport;
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
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
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
        final FinanceReport financeReport = new FinanceReport();
        financeReport.setId(0L);
        financeReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setWage(0);
        financeReport.setAttendance(List.of(new WorkReport()));
        financeReport.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport.setFinanceReport(financeReport);
        workReport.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportMapper.convertToEntity(new WorkReportRequest())).thenReturn(workReport);

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
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
        final FinanceReport financeReport1 = new FinanceReport();
        financeReport1.setId(0L);
        financeReport1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport1.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport1.setWage(0);
        financeReport1.setAttendance(List.of(new WorkReport()));
        financeReport1.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport1.setFinanceReport(financeReport1);
        workReport1.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportDao.save(new WorkReport())).thenReturn(workReport1);

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
        when(mockWorkReportMapper.convertToDto(new WorkReport())).thenReturn(workReportDTO);

        // Run the test
        final WorkReportDTO result = workReportServiceImplUnderTest.create(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testCreate_WorkReportMapperConvertToEntityThrowsParseException() throws Exception {
        // Setup
        final WorkReportRequest request = new WorkReportRequest();
        request.setId(0L);
        request.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setMinutes(0);
        request.setType(WorkReportType.WORK_REPORT);
        request.setUserId(0L);

        when(mockWorkReportMapper.convertToEntity(new WorkReportRequest())).thenThrow(ParseException.class);

        // Run the test
        assertThatThrownBy(() -> workReportServiceImplUnderTest.create(request)).isInstanceOf(ParseException.class);
    }

    @Test
    void testCreate_UserRepositoryReturnsAbsent() throws Exception {
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
        final FinanceReport financeReport = new FinanceReport();
        financeReport.setId(0L);
        financeReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setWage(0);
        financeReport.setAttendance(List.of(new WorkReport()));
        financeReport.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport.setFinanceReport(financeReport);
        workReport.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportMapper.convertToEntity(new WorkReportRequest())).thenReturn(workReport);

        when(mockUserDao.findById(0L)).thenReturn(Optional.empty());

        // Configure WorkReportRepository.save(...).
        final WorkReport workReport1 = new WorkReport();
        workReport1.setId(0L);
        workReport1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport1.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport1.setMinutes(0);
        workReport1.setReportingEmployee(User.builder()
                .id(0L)
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
        final FinanceReport financeReport1 = new FinanceReport();
        financeReport1.setId(0L);
        financeReport1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport1.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport1.setWage(0);
        financeReport1.setAttendance(List.of(new WorkReport()));
        financeReport1.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport1.setFinanceReport(financeReport1);
        workReport1.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportDao.save(new WorkReport())).thenReturn(workReport1);

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
        when(mockWorkReportMapper.convertToDto(new WorkReport())).thenReturn(workReportDTO);

        // Run the test
        final WorkReportDTO result = workReportServiceImplUnderTest.create(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testRecordIllness() {
        // Setup
        final IllnessRequest request = new IllnessRequest();
        request.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);

        // Configure UserRepository.findByEmail(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
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
        when(mockLocationDao.save(Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build())).thenReturn(location);

        // Configure WorkReportRepository.save(...).
        final WorkReport workReport = new WorkReport();
        workReport.setId(0L);
        workReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setMinutes(0);
        workReport.setReportingEmployee(User.builder()
                .id(0L)
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
        final FinanceReport financeReport = new FinanceReport();
        financeReport.setId(0L);
        financeReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setWage(0);
        financeReport.setAttendance(List.of(new WorkReport()));
        financeReport.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport.setFinanceReport(financeReport);
        workReport.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportDao.save(new WorkReport())).thenReturn(workReport);

        // Run the test
        workReportServiceImplUnderTest.recordIllness(request);

        // Verify the results
        verify(mockLocationDao).save(Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build());
        verify(mockWorkReportDao).save(new WorkReport());
    }

    @Test
    void testRecordIllness_UserRepositoryReturnsAbsent() {
        // Setup
        final IllnessRequest request = new IllnessRequest();
        request.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());

        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);
        when(mockUserDao.findByEmail("email")).thenReturn(Optional.empty());

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
        when(mockLocationDao.save(Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build())).thenReturn(location);

        // Configure WorkReportRepository.save(...).
        final WorkReport workReport = new WorkReport();
        workReport.setId(0L);
        workReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setMinutes(0);
        workReport.setReportingEmployee(User.builder()
                .id(0L)
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
        final FinanceReport financeReport = new FinanceReport();
        financeReport.setId(0L);
        financeReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setWage(0);
        financeReport.setAttendance(List.of(new WorkReport()));
        financeReport.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport.setFinanceReport(financeReport);
        workReport.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportDao.save(new WorkReport())).thenReturn(workReport);

        // Run the test
        workReportServiceImplUnderTest.recordIllness(request);

        // Verify the results
        verify(mockLocationDao).save(Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build());
        verify(mockWorkReportDao).save(new WorkReport());
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

        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);

        // Configure UserRepository.findByEmail(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
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
        final FinanceReport financeReport = new FinanceReport();
        financeReport.setId(0L);
        financeReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setWage(0);
        financeReport.setAttendance(List.of(new WorkReport()));
        financeReport.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport.setFinanceReport(financeReport);
        workReport.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportDao.save(new WorkReport())).thenReturn(workReport);

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
        when(mockWorkReportMapper.convertToDto(new WorkReport())).thenReturn(workReportDTO);

        // Run the test
        final WorkReportDTO result = workReportServiceImplUnderTest.recordWorkReport(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testRecordWorkReport_UserRepositoryReturnsAbsent() {
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

        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);
        when(mockUserDao.findByEmail("email")).thenReturn(Optional.empty());

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
        when(mockWorkReportMapper.convertToDto(new WorkReport())).thenReturn(workReportDTO);

        // Run the test
        final WorkReportDTO result = workReportServiceImplUnderTest.recordWorkReport(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testRecordWorkReport_LocationRepositoryReturnsAbsent() {
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

        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);

        // Configure UserRepository.findByEmail(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .build());
        when(mockUserDao.findByEmail("email")).thenReturn(user);

        when(mockLocationDao.findById(0L)).thenReturn(Optional.empty());

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
        when(mockWorkReportMapper.convertToDto(new WorkReport())).thenReturn(workReportDTO);

        // Run the test
        final WorkReportDTO result = workReportServiceImplUnderTest.recordWorkReport(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testStopWorkReportRecord() {
        // Setup
        final StopAttendanceRequest request = new StopAttendanceRequest();
        request.setTime(0);

        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);

        // Configure UserRepository.findByEmail(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .build());
        when(mockUserDao.findByEmail("email")).thenReturn(user);

        // Configure WorkReportRepository.findTodayAttendance(...).
        final WorkReport workReport = new WorkReport();
        workReport.setId(0L);
        workReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setMinutes(0);
        workReport.setReportingEmployee(User.builder()
                .id(0L)
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
        final FinanceReport financeReport = new FinanceReport();
        financeReport.setId(0L);
        financeReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setWage(0);
        financeReport.setAttendance(List.of(new WorkReport()));
        financeReport.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport.setFinanceReport(financeReport);
        workReport.setType(WorkReportType.WORK_REPORT);
        final List<WorkReport> workReports = List.of(workReport);
        when(mockWorkReportDao.findTodayAttendance(0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(workReports);

        // Configure WorkReportRepository.save(...).
        final WorkReport workReport1 = new WorkReport();
        workReport1.setId(0L);
        workReport1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport1.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport1.setMinutes(0);
        workReport1.setReportingEmployee(User.builder()
                .id(0L)
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
        final FinanceReport financeReport1 = new FinanceReport();
        financeReport1.setId(0L);
        financeReport1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport1.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport1.setWage(0);
        financeReport1.setAttendance(List.of(new WorkReport()));
        financeReport1.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport1.setFinanceReport(financeReport1);
        workReport1.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportDao.save(new WorkReport())).thenReturn(workReport1);

        // Run the test
        workReportServiceImplUnderTest.stopWorkReportRecord(request);

        // Verify the results
        verify(mockWorkReportDao).save(new WorkReport());
    }

    @Test
    void testStopWorkReportRecord_UserRepositoryReturnsAbsent() {
        // Setup
        final StopAttendanceRequest request = new StopAttendanceRequest();
        request.setTime(0);

        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);
        when(mockUserDao.findByEmail("email")).thenReturn(Optional.empty());

        // Run the test
        workReportServiceImplUnderTest.stopWorkReportRecord(request);

        // Verify the results
    }

    @Test
    void testStopWorkReportRecord_WorkReportRepositoryFindTodayAttendanceReturnsNoItems() {
        // Setup
        final StopAttendanceRequest request = new StopAttendanceRequest();
        request.setTime(0);

        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);

        // Configure UserRepository.findByEmail(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .build());
        when(mockUserDao.findByEmail("email")).thenReturn(user);

        when(mockWorkReportDao.findTodayAttendance(0L,
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime())).thenReturn(Collections.emptyList());

        // Configure WorkReportRepository.save(...).
        final WorkReport workReport = new WorkReport();
        workReport.setId(0L);
        workReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setMinutes(0);
        workReport.setReportingEmployee(User.builder()
                .id(0L)
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
        final FinanceReport financeReport = new FinanceReport();
        financeReport.setId(0L);
        financeReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setWage(0);
        financeReport.setAttendance(List.of(new WorkReport()));
        financeReport.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport.setFinanceReport(financeReport);
        workReport.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportDao.save(new WorkReport())).thenReturn(workReport);

        // Run the test
        workReportServiceImplUnderTest.stopWorkReportRecord(request);

        // Verify the results
        verify(mockWorkReportDao).save(new WorkReport());
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
        final FinanceReport financeReport = new FinanceReport();
        financeReport.setId(0L);
        financeReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setWage(0);
        financeReport.setAttendance(List.of(new WorkReport()));
        financeReport.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport.setFinanceReport(financeReport);
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
        when(mockWorkReportMapper.convertToDto(new WorkReport())).thenReturn(workReportDTO);

        // Run the test
        final WorkReportDTO result = workReportServiceImplUnderTest.getWorkReportById(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
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
    void testGetWorkReports() {
        // Setup
        final WorkReportDTO workReportDTO = new WorkReportDTO();
        workReportDTO.setId(0L);
        workReportDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO.setMinutes(0);
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
        workReportDTO.setReportingEmployee(reportingEmployee);
        final List<WorkReportDTO> expectedResult = List.of(workReportDTO);

        // Configure WorkReportRepository.findAll(...).
        final WorkReport workReport = new WorkReport();
        workReport.setId(0L);
        workReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setMinutes(0);
        workReport.setReportingEmployee(User.builder()
                .id(0L)
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
        final FinanceReport financeReport = new FinanceReport();
        financeReport.setId(0L);
        financeReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setWage(0);
        financeReport.setAttendance(List.of(new WorkReport()));
        financeReport.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport.setFinanceReport(financeReport);
        workReport.setType(WorkReportType.WORK_REPORT);
        final List<WorkReport> workReports = List.of(workReport);
        when(mockWorkReportDao.findAll()).thenReturn(workReports);

        // Configure WorkReportMapper.convertToDto(...).
        final WorkReportDTO workReportDTO1 = new WorkReportDTO();
        workReportDTO1.setId(0L);
        workReportDTO1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO1.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO1.setMinutes(0);
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
        workReportDTO1.setReportingEmployee(reportingEmployee1);
        when(mockWorkReportMapper.convertToDto(new WorkReport())).thenReturn(workReportDTO1);

        // Run the test
        final List<WorkReportDTO> result = workReportServiceImplUnderTest.getWorkReports();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
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
    void testGetMyWorkReports() {
        // Setup
        final WorkReportDTO workReportDTO = new WorkReportDTO();
        workReportDTO.setId(0L);
        workReportDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO.setMinutes(0);
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
        workReportDTO.setReportingEmployee(reportingEmployee);
        final List<WorkReportDTO> expectedResult = List.of(workReportDTO);
        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);

        // Configure UserRepository.findByEmail(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .build());
        when(mockUserDao.findByEmail("email")).thenReturn(user);

        // Configure WorkReportRepository.findWorkReportsByReportingEmployeeId(...).
        final WorkReport workReport = new WorkReport();
        workReport.setId(0L);
        workReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setMinutes(0);
        workReport.setReportingEmployee(User.builder()
                .id(0L)
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
        final FinanceReport financeReport = new FinanceReport();
        financeReport.setId(0L);
        financeReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setWage(0);
        financeReport.setAttendance(List.of(new WorkReport()));
        financeReport.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport.setFinanceReport(financeReport);
        workReport.setType(WorkReportType.WORK_REPORT);
        final List<WorkReport> workReports = List.of(workReport);
        when(mockWorkReportDao.findWorkReportsByReportingEmployeeId(0L)).thenReturn(workReports);

        // Configure WorkReportMapper.convertToDto(...).
        final WorkReportDTO workReportDTO1 = new WorkReportDTO();
        workReportDTO1.setId(0L);
        workReportDTO1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO1.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO1.setMinutes(0);
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
        workReportDTO1.setReportingEmployee(reportingEmployee1);
        when(mockWorkReportMapper.convertToDto(new WorkReport())).thenReturn(workReportDTO1);

        // Run the test
        final List<WorkReportDTO> result = workReportServiceImplUnderTest.getMyWorkReports();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetMyWorkReports_UserRepositoryReturnsAbsent() {
        // Setup
        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);
        when(mockUserDao.findByEmail("email")).thenReturn(Optional.empty());

        // Run the test
        final List<WorkReportDTO> result = workReportServiceImplUnderTest.getMyWorkReports();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetMyWorkReports_WorkReportRepositoryReturnsNoItems() {
        // Setup
        when(mockAuthenticationFacade.getAuthentication()).thenReturn(null);

        // Configure UserRepository.findByEmail(...).
        final Optional<User> user = Optional.of(User.builder()
                .id(0L)
                .build());
        when(mockUserDao.findByEmail("email")).thenReturn(user);

        when(mockWorkReportDao.findWorkReportsByReportingEmployeeId(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<WorkReportDTO> result = workReportServiceImplUnderTest.getMyWorkReports();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetWorkReportsByReportingEmployeeId() {
        // Setup
        final WorkReportDTO workReportDTO = new WorkReportDTO();
        workReportDTO.setId(0L);
        workReportDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO.setMinutes(0);
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
        workReportDTO.setReportingEmployee(reportingEmployee);
        final List<WorkReportDTO> expectedResult = List.of(workReportDTO);

        // Configure WorkReportRepository.findWorkReportsByReportingEmployeeId(...).
        final WorkReport workReport = new WorkReport();
        workReport.setId(0L);
        workReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setMinutes(0);
        workReport.setReportingEmployee(User.builder()
                .id(0L)
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
        final FinanceReport financeReport = new FinanceReport();
        financeReport.setId(0L);
        financeReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setWage(0);
        financeReport.setAttendance(List.of(new WorkReport()));
        financeReport.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport.setFinanceReport(financeReport);
        workReport.setType(WorkReportType.WORK_REPORT);
        final List<WorkReport> workReports = List.of(workReport);
        when(mockWorkReportDao.findWorkReportsByReportingEmployeeId(0L)).thenReturn(workReports);

        // Configure WorkReportMapper.convertToDto(...).
        final WorkReportDTO workReportDTO1 = new WorkReportDTO();
        workReportDTO1.setId(0L);
        workReportDTO1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO1.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReportDTO1.setMinutes(0);
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
        workReportDTO1.setReportingEmployee(reportingEmployee1);
        when(mockWorkReportMapper.convertToDto(new WorkReport())).thenReturn(workReportDTO1);

        // Run the test
        final List<WorkReportDTO> result = workReportServiceImplUnderTest.getWorkReportsByReportingEmployeeId(0L);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetWorkReportsByReportingEmployeeId_WorkReportRepositoryReturnsNoItems() {
        // Setup
        when(mockWorkReportDao.findWorkReportsByReportingEmployeeId(0L)).thenReturn(Collections.emptyList());

        // Run the test
        final List<WorkReportDTO> result = workReportServiceImplUnderTest.getWorkReportsByReportingEmployeeId(0L);

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

    @Test
    void testUpdate() throws Exception {
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

        // Configure WorkReportRepository.findById(...).
        final WorkReport workReport1 = new WorkReport();
        workReport1.setId(0L);
        workReport1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport1.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport1.setMinutes(0);
        workReport1.setReportingEmployee(User.builder()
                .id(0L)
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
        final FinanceReport financeReport = new FinanceReport();
        financeReport.setId(0L);
        financeReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setWage(0);
        financeReport.setAttendance(List.of(new WorkReport()));
        financeReport.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport1.setFinanceReport(financeReport);
        workReport1.setType(WorkReportType.WORK_REPORT);
        final Optional<WorkReport> workReport = Optional.of(workReport1);
        when(mockWorkReportDao.findById(0L)).thenReturn(workReport);

        // Configure WorkReportMapper.convertToEntity(...).
        final WorkReport workReport2 = new WorkReport();
        workReport2.setId(0L);
        workReport2.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport2.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport2.setMinutes(0);
        workReport2.setReportingEmployee(User.builder()
                .id(0L)
                .build());
        workReport2.setLocation(Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build());
        final FinanceReport financeReport1 = new FinanceReport();
        financeReport1.setId(0L);
        financeReport1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport1.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport1.setWage(0);
        financeReport1.setAttendance(List.of(new WorkReport()));
        financeReport1.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport2.setFinanceReport(financeReport1);
        workReport2.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportMapper.convertToEntity(new WorkReportRequest())).thenReturn(workReport2);

        // Configure WorkReportRepository.save(...).
        final WorkReport workReport3 = new WorkReport();
        workReport3.setId(0L);
        workReport3.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport3.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport3.setMinutes(0);
        workReport3.setReportingEmployee(User.builder()
                .id(0L)
                .build());
        workReport3.setLocation(Location.builder()
                .id(0L)
                .city("city")
                .street("street")
                .descriptiveNumber("descriptiveNumber")
                .country("country")
                .postCode("postCode")
                .latitude(0.0)
                .longitude(0.0)
                .build());
        final FinanceReport financeReport2 = new FinanceReport();
        financeReport2.setId(0L);
        financeReport2.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport2.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport2.setWage(0);
        financeReport2.setAttendance(List.of(new WorkReport()));
        financeReport2.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport3.setFinanceReport(financeReport2);
        workReport3.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportDao.save(new WorkReport())).thenReturn(workReport3);

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
        when(mockWorkReportMapper.convertToDto(new WorkReport())).thenReturn(workReportDTO);

        // Run the test
        final WorkReportDTO result = workReportServiceImplUnderTest.update(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate_WorkReportRepositoryFindByIdReturnsAbsent() throws Exception {
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

        when(mockWorkReportDao.findById(0L)).thenReturn(Optional.empty());

        // Configure WorkReportMapper.convertToEntity(...).
        final WorkReport workReport = new WorkReport();
        workReport.setId(0L);
        workReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport.setMinutes(0);
        workReport.setReportingEmployee(User.builder()
                .id(0L)
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
        final FinanceReport financeReport = new FinanceReport();
        financeReport.setId(0L);
        financeReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setWage(0);
        financeReport.setAttendance(List.of(new WorkReport()));
        financeReport.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport.setFinanceReport(financeReport);
        workReport.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportMapper.convertToEntity(new WorkReportRequest())).thenReturn(workReport);

        // Configure WorkReportRepository.save(...).
        final WorkReport workReport1 = new WorkReport();
        workReport1.setId(0L);
        workReport1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport1.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport1.setMinutes(0);
        workReport1.setReportingEmployee(User.builder()
                .id(0L)
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
        final FinanceReport financeReport1 = new FinanceReport();
        financeReport1.setId(0L);
        financeReport1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport1.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport1.setWage(0);
        financeReport1.setAttendance(List.of(new WorkReport()));
        financeReport1.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport1.setFinanceReport(financeReport1);
        workReport1.setType(WorkReportType.WORK_REPORT);
        when(mockWorkReportDao.save(new WorkReport())).thenReturn(workReport1);

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
        when(mockWorkReportMapper.convertToDto(new WorkReport())).thenReturn(workReportDTO);

        // Run the test
        final WorkReportDTO result = workReportServiceImplUnderTest.update(request);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testUpdate_WorkReportMapperConvertToEntityThrowsParseException() throws Exception {
        // Setup
        final WorkReportRequest request = new WorkReportRequest();
        request.setId(0L);
        request.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        request.setMinutes(0);
        request.setType(WorkReportType.WORK_REPORT);
        request.setUserId(0L);

        // Configure WorkReportRepository.findById(...).
        final WorkReport workReport1 = new WorkReport();
        workReport1.setId(0L);
        workReport1.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport1.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        workReport1.setMinutes(0);
        workReport1.setReportingEmployee(User.builder()
                .id(0L)
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
        final FinanceReport financeReport = new FinanceReport();
        financeReport.setId(0L);
        financeReport.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        financeReport.setWage(0);
        financeReport.setAttendance(List.of(new WorkReport()));
        financeReport.setSalariedEmployee(User.builder()
                .id(0L)
                .build());
        workReport1.setFinanceReport(financeReport);
        workReport1.setType(WorkReportType.WORK_REPORT);
        final Optional<WorkReport> workReport = Optional.of(workReport1);
        when(mockWorkReportDao.findById(0L)).thenReturn(workReport);

        when(mockWorkReportMapper.convertToEntity(new WorkReportRequest())).thenThrow(ParseException.class);

        // Run the test
        assertThatThrownBy(() -> workReportServiceImplUnderTest.update(request)).isInstanceOf(ParseException.class);
    }
}
