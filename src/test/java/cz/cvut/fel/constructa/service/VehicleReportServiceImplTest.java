package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;
import cz.cvut.fel.constructa.enums.VehicleType;
import cz.cvut.fel.constructa.mapper.VehicleReportMapper;
import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.repository.VehicleReportRepository;
import cz.cvut.fel.constructa.repository.VehicleRepository;
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
class VehicleReportServiceImplTest {

    @Mock
    private VehicleReportRepository mockVehicleReportDao;
    @Mock
    private UserRepository mockUserDao;
    @Mock
    private VehicleRepository mockVehicleDao;
    @Mock
    private VehicleReportMapper mockVehicleReportMapper;
    @Mock
    private AuthenticationFacade mockAuthenticationFacade;

    private VehicleReportServiceImpl vehicleReportServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        vehicleReportServiceImplUnderTest = new VehicleReportServiceImpl(mockVehicleReportDao, mockUserDao,
                mockVehicleDao, mockVehicleReportMapper, mockAuthenticationFacade);
    }

    @Test
    void testCreate() throws Exception {
        // Setup
        final VehicleReportRequest request = VehicleReportRequest.builder()
                .id(0L)
                .afterworkConditionMotorcycleWatch(0.0)
                .vehicle(0L)
                .driver(0L)
                .build();

        // Configure VehicleReportMapper.convertToEntity(...).
        final VehicleReport vehicleReport = VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build();
        when(mockVehicleReportMapper.convertToEntity(request)).thenReturn(vehicleReport);

        // Configure UserRepository.findById(...).
        final Optional<User> user = Optional.of(User.builder()
                .username("username")
                .build());
        when(mockUserDao.findById(0L)).thenReturn(user);

        // Configure VehicleRepository.findById(...).
        final Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(0L);
        vehicle1.setFactory("factory");
        vehicle1.setName("name");
        vehicle1.setVinCode("vinCode");
        vehicle1.setRegistrationNumber("registrationNumber");
        vehicle1.setConditionMotorcycleWatch(0.0);
        vehicle1.setMileage(0.0);
        vehicle1.setBoughtAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicle1.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicle1.setVehicleReports(List.of(VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build()));
        vehicle1.setType(VehicleType.CAR);
        final Optional<Vehicle> vehicle = Optional.of(vehicle1);
        when(mockVehicleDao.findById(0L)).thenReturn(vehicle);

        // Configure VehicleRepository.save(...).
        final Vehicle vehicle2 = new Vehicle();
        vehicle2.setId(0L);
        vehicle2.setFactory("factory");
        vehicle2.setName("name");
        vehicle2.setVinCode("vinCode");
        vehicle2.setRegistrationNumber("registrationNumber");
        vehicle2.setConditionMotorcycleWatch(0.0);
        vehicle2.setMileage(0.0);
        vehicle2.setBoughtAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicle2.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicle2.setVehicleReports(List.of(VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build()));
        vehicle2.setType(VehicleType.CAR);
        when(mockVehicleDao.save(vehicle2)).thenReturn(vehicle2);

        // Configure VehicleReportRepository.save(...).
        final VehicleReport vehicleReport1 = VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build();
        when(mockVehicleReportDao.save(vehicleReport)).thenReturn(vehicleReport1);

        // Configure VehicleReportMapper.convertToDto(...).
        final VehicleReportDTO vehicleReportDTO = new VehicleReportDTO();
        vehicleReportDTO.setId(0L);
        vehicleReportDTO.setOriginalConditionMotorcycleWatch(0.0);
        vehicleReportDTO.setAfterworkConditionMotorcycleWatch(0.0);
        vehicleReportDTO.setCargoMass(0);
        vehicleReportDTO.setCargoType("cargoType");
        vehicleReportDTO.setDistance(0.0);
        vehicleReportDTO.setPurchaseOfFuelLitres(0.0);
        vehicleReportDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleReportDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleReportDTO.setDescription("description");
        final VehicleDTO vehicle3 = new VehicleDTO();
        vehicle3.setId(0L);
        vehicle3.setFactory("factory");
        vehicle3.setName("name");
        vehicle3.setRegistrationNumber("registrationNumber");
        vehicleReportDTO.setVehicle(vehicle3);
        when(mockVehicleReportMapper.convertToDto(vehicleReport1)).thenReturn(vehicleReportDTO);

        // Run the test
        final VehicleReportDTO result = vehicleReportServiceImplUnderTest.create(request);

        // Verify the results
        verify(mockVehicleDao).save(vehicle2);
    }


    @Test
    void testCreate_VehicleReportMapperConvertToEntityThrowsParseException() throws Exception {
        // Setup
        final VehicleReportRequest request = VehicleReportRequest.builder()
                .id(0L)
                .afterworkConditionMotorcycleWatch(0.0)
                .vehicle(0L)
                .driver(0L)
                .build();
        when(mockVehicleReportMapper.convertToEntity(VehicleReportRequest.builder()
                .id(0L)
                .afterworkConditionMotorcycleWatch(0.0)
                .vehicle(0L)
                .driver(0L)
                .build())).thenThrow(ParseException.class);

        // Run the test
        assertThatThrownBy(() -> vehicleReportServiceImplUnderTest.create(request)).isInstanceOf(ParseException.class);
    }

    @Test
    void testGetVehicleReportById() {
        // Setup
        // Configure VehicleReportRepository.findAll(...).
        final List<VehicleReport> vehicleReports = List.of(VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build());
        when(mockVehicleReportDao.findAll()).thenReturn(vehicleReports);

        // Configure VehicleReportMapper.convertToDto(...).
        final VehicleReportDTO vehicleReportDTO = new VehicleReportDTO();
        vehicleReportDTO.setId(0L);
        vehicleReportDTO.setOriginalConditionMotorcycleWatch(0.0);
        vehicleReportDTO.setAfterworkConditionMotorcycleWatch(0.0);
        vehicleReportDTO.setCargoMass(0);
        vehicleReportDTO.setCargoType("cargoType");
        vehicleReportDTO.setDistance(0.0);
        vehicleReportDTO.setPurchaseOfFuelLitres(0.0);
        vehicleReportDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleReportDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleReportDTO.setDescription("description");
        final VehicleDTO vehicle = new VehicleDTO();
        vehicle.setId(0L);
        vehicle.setFactory("factory");
        vehicle.setName("name");
        vehicle.setRegistrationNumber("registrationNumber");
        vehicleReportDTO.setVehicle(vehicle);
        when(mockVehicleReportMapper.convertToDto(VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build())).thenReturn(vehicleReportDTO);

        // Run the test
        final VehicleReportDTO result = vehicleReportServiceImplUnderTest.getVehicleReportById(0L);

        // Verify the results
    }

    @Test
    void testGetVehicleReportById_VehicleReportRepositoryReturnsNoItems() {
        // Setup
        when(mockVehicleReportDao.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final VehicleReportDTO result = vehicleReportServiceImplUnderTest.getVehicleReportById(0L);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetVehicleReportsByVehicleId() {
        // Setup
        // Configure VehicleReportRepository.findVehicleReportByVehicleId(...).
        final List<VehicleReport> vehicleReports = List.of(VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build());
        when(mockVehicleReportDao.findVehicleReportByVehicleId(0L, Sort.by("timeTo").descending())).thenReturn(vehicleReports);

        // Configure VehicleReportMapper.convertToDto(...).
        final VehicleReportDTO vehicleReportDTO = new VehicleReportDTO();
        vehicleReportDTO.setId(0L);
        vehicleReportDTO.setOriginalConditionMotorcycleWatch(0.0);
        vehicleReportDTO.setAfterworkConditionMotorcycleWatch(0.0);
        vehicleReportDTO.setCargoMass(0);
        vehicleReportDTO.setCargoType("cargoType");
        vehicleReportDTO.setDistance(0.0);
        vehicleReportDTO.setPurchaseOfFuelLitres(0.0);
        vehicleReportDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleReportDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleReportDTO.setDescription("description");
        final VehicleDTO vehicle = new VehicleDTO();
        vehicle.setId(0L);
        vehicle.setFactory("factory");
        vehicle.setName("name");
        vehicle.setRegistrationNumber("registrationNumber");
        vehicleReportDTO.setVehicle(vehicle);
        when(mockVehicleReportMapper.convertToDto(VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build())).thenReturn(vehicleReportDTO);

        // Run the test
        final List<VehicleReportDTO> result = vehicleReportServiceImplUnderTest.getVehicleReportsByVehicleId(0L);

        // Verify the results
        assertThat(result).containsExactly(vehicleReportDTO);
        verify(mockVehicleReportDao).findVehicleReportByVehicleId(0L, Sort.by("timeTo").descending());
        verify(mockVehicleReportMapper).convertToDto(VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build());
    }


    @Test
    void testGetVehicleReports() {
        // Setup
        // Configure VehicleReportRepository.findAll(...).
        final List<VehicleReport> vehicleReports = List.of(VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build());
        when(mockVehicleReportDao.findAll()).thenReturn(vehicleReports);

        // Configure VehicleReportMapper.convertToDto(...).
        final VehicleReportDTO vehicleReportDTO = new VehicleReportDTO();
        vehicleReportDTO.setId(0L);
        vehicleReportDTO.setOriginalConditionMotorcycleWatch(0.0);
        vehicleReportDTO.setAfterworkConditionMotorcycleWatch(0.0);
        vehicleReportDTO.setCargoMass(0);
        vehicleReportDTO.setCargoType("cargoType");
        vehicleReportDTO.setDistance(0.0);
        vehicleReportDTO.setPurchaseOfFuelLitres(0.0);
        vehicleReportDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleReportDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleReportDTO.setDescription("description");
        final VehicleDTO vehicle = new VehicleDTO();
        vehicle.setId(0L);
        vehicle.setFactory("factory");
        vehicle.setName("name");
        vehicle.setRegistrationNumber("registrationNumber");
        vehicleReportDTO.setVehicle(vehicle);
        when(mockVehicleReportMapper.convertToDto(VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build())).thenReturn(vehicleReportDTO);

        // Run the test
        final List<VehicleReportDTO> result = vehicleReportServiceImplUnderTest.getVehicleReports();

        // Verify the results
    }

    @Test
    void testGetVehicleReports_VehicleReportRepositoryReturnsNoItems() {
        // Setup
        when(mockVehicleReportDao.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<VehicleReportDTO> result = vehicleReportServiceImplUnderTest.getVehicleReports();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testDeleteVehicleReport() {
        // Setup
        // Run the test
        vehicleReportServiceImplUnderTest.deleteVehicleReport(0L);

        // Verify the results
        verify(mockVehicleReportDao).deleteById(0L);
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        final VehicleReportRequest request = VehicleReportRequest.builder()
                .id(0L)
                .afterworkConditionMotorcycleWatch(0.0)
                .vehicle(0L)
                .driver(0L)
                .build();

        // Configure VehicleReportRepository.findById(...).
        final Optional<VehicleReport> vehicleReport = Optional.of(VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build());
        when(mockVehicleReportDao.findById(0L)).thenReturn(vehicleReport);

        // Configure VehicleReportMapper.convertToEntity(...).
        final VehicleReport vehicleReport1 = VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build();
        when(mockVehicleReportMapper.convertToEntity(VehicleReportRequest.builder()
                .id(0L)
                .afterworkConditionMotorcycleWatch(0.0)
                .vehicle(0L)
                .driver(0L)
                .build())).thenReturn(vehicleReport1);

        // Configure VehicleReportRepository.save(...).
        final VehicleReport vehicleReport2 = VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build();
        when(mockVehicleReportDao.save(VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build())).thenReturn(vehicleReport2);

        // Configure VehicleReportMapper.convertToDto(...).
        final VehicleReportDTO vehicleReportDTO = new VehicleReportDTO();
        vehicleReportDTO.setId(0L);
        vehicleReportDTO.setOriginalConditionMotorcycleWatch(0.0);
        vehicleReportDTO.setAfterworkConditionMotorcycleWatch(0.0);
        vehicleReportDTO.setCargoMass(0);
        vehicleReportDTO.setCargoType("cargoType");
        vehicleReportDTO.setDistance(0.0);
        vehicleReportDTO.setPurchaseOfFuelLitres(0.0);
        vehicleReportDTO.setTimeFrom(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleReportDTO.setTimeTo(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleReportDTO.setDescription("description");
        final VehicleDTO vehicle = new VehicleDTO();
        vehicle.setId(0L);
        vehicle.setFactory("factory");
        vehicle.setName("name");
        vehicle.setRegistrationNumber("registrationNumber");
        vehicleReportDTO.setVehicle(vehicle);
        when(mockVehicleReportMapper.convertToDto(VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build())).thenReturn(vehicleReportDTO);

        // Run the test
        final VehicleReportDTO result = vehicleReportServiceImplUnderTest.update(request);

        // Verify the results
    }

    @Test
    void testUpdate_VehicleReportMapperConvertToEntityThrowsParseException() throws Exception {
        // Setup
        final VehicleReportRequest request = VehicleReportRequest.builder()
                .id(0L)
                .afterworkConditionMotorcycleWatch(0.0)
                .vehicle(0L)
                .driver(0L)
                .build();

        // Configure VehicleReportRepository.findById(...).
        final Optional<VehicleReport> vehicleReport = Optional.of(VehicleReport.builder()
                .id(0L)
                .vehicle(new Vehicle())
                .driver(User.builder()
                        .username("username")
                        .build())
                .build());
        when(mockVehicleReportDao.findById(0L)).thenReturn(vehicleReport);

        when(mockVehicleReportMapper.convertToEntity(VehicleReportRequest.builder()
                .id(0L)
                .afterworkConditionMotorcycleWatch(0.0)
                .vehicle(0L)
                .driver(0L)
                .build())).thenThrow(ParseException.class);

        // Run the test
        assertThatThrownBy(() -> vehicleReportServiceImplUnderTest.update(request)).isInstanceOf(ParseException.class);
    }
}
