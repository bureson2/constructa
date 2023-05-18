package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.VehicleRequest;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleInputDTO;
import cz.cvut.fel.constructa.enums.VehicleType;
import cz.cvut.fel.constructa.mapper.VehicleMapper;
import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.repository.VehicleRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {

    @Mock
    private VehicleRepository mockVehicleDao;
    @Mock
    private VehicleMapper mockVehicleMapper;

    private VehicleServiceImpl vehicleServiceImplUnderTest;

    @BeforeEach
    void setUp() {
        vehicleServiceImplUnderTest = new VehicleServiceImpl(mockVehicleDao, mockVehicleMapper);
    }

    @Test
    void testCreate() throws Exception {
        // Setup
        final VehicleRequest request = VehicleRequest.builder().build();

        // Configure VehicleMapper.convertToEntity(...).
        final Vehicle vehicle = new Vehicle();
        vehicle.setId(0L);
        vehicle.setFactory("factory");
        vehicle.setName("name");
        vehicle.setVinCode("vinCode");
        vehicle.setRegistrationNumber("registrationNumber");
        vehicle.setConditionMotorcycleWatch(0.0);
        vehicle.setMileage(0.0);
        vehicle.setBoughtAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicle.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicle.setVehicleReports(List.of(VehicleReport.builder().build()));
        vehicle.setType(VehicleType.CAR);
        when(mockVehicleMapper.convertToEntity(VehicleRequest.builder().build())).thenReturn(vehicle);

        // Configure VehicleRepository.save(...).
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
        vehicle1.setVehicleReports(List.of(VehicleReport.builder().build()));
        vehicle1.setType(VehicleType.CAR);
        when(mockVehicleDao.save(vehicle)).thenReturn(vehicle1);

        // Configure VehicleMapper.convertToDto(...).
        final VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(0L);
        vehicleDTO.setFactory("factory");
        vehicleDTO.setName("name");
        vehicleDTO.setRegistrationNumber("registrationNumber");
        vehicleDTO.setConditionMotorcycleWatch(0.0);
        vehicleDTO.setMileage(0.0);
        vehicleDTO.setType("type");
        vehicleDTO.setBoughtAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleDTO.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleDTO.setQrCode("qrCode");
        vehicleDTO.setVinCode("vinCode");
        when(mockVehicleMapper.convertToDto(vehicle1)).thenReturn(vehicleDTO);

        // Run the test
        final VehicleDTO result = vehicleServiceImplUnderTest.create(request);

        // Verify the results
        assertThat(result).isEqualTo(vehicleDTO);
        verify(mockVehicleDao).save(vehicle);
        verify(mockVehicleMapper).convertToDto(vehicle1);
    }


    @Test
    void testCreate_VehicleMapperConvertToEntityThrowsParseException() throws Exception {
        // Setup
        final VehicleRequest request = VehicleRequest.builder().build();
        when(mockVehicleMapper.convertToEntity(VehicleRequest.builder().build())).thenThrow(ParseException.class);

        // Run the test
        assertThatThrownBy(() -> vehicleServiceImplUnderTest.create(request)).isInstanceOf(ParseException.class);
    }

    @Test
    void testGetVehicleById() {
        // Setup
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
        vehicle1.setVehicleReports(List.of(VehicleReport.builder().build()));
        vehicle1.setType(VehicleType.CAR);
        final Optional<Vehicle> vehicle = Optional.of(vehicle1);
        when(mockVehicleDao.findById(0L)).thenReturn(vehicle);

        // Configure VehicleMapper.convertToDto(...).
        final VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(0L);
        vehicleDTO.setFactory("factory");
        vehicleDTO.setName("name");
        vehicleDTO.setRegistrationNumber("registrationNumber");
        vehicleDTO.setConditionMotorcycleWatch(0.0);
        vehicleDTO.setMileage(0.0);
        vehicleDTO.setType("CAR");
        vehicleDTO.setBoughtAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleDTO.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleDTO.setQrCode("qrCode");
        vehicleDTO.setVinCode("vinCode");
        when(mockVehicleMapper.convertToDto(any(Vehicle.class))).thenReturn(vehicleDTO);

        // Run the test
        final VehicleDTO result = vehicleServiceImplUnderTest.getVehicleById(0L);

        // Verify the results
        verify(mockVehicleDao).findById(0L);
        verify(mockVehicleMapper).convertToDto(any(Vehicle.class));
    }

    @Test
    void testGetVehicles_VehicleRepositoryReturnsNoItems() {
        // Setup
        when(mockVehicleDao.findAll(Sort.by("registrationNumber"))).thenReturn(Collections.emptyList());

        // Run the test
        final List<VehicleDTO> result = vehicleServiceImplUnderTest.getVehicles();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }


    @Test
    void testGetVehicleById_VehicleRepositoryReturnsAbsent() {
        // Setup
        when(mockVehicleDao.findById(0L)).thenReturn(Optional.empty());

        // Run the test
        final VehicleDTO result = vehicleServiceImplUnderTest.getVehicleById(0L);

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    void testGetVehicles() {
        // Setup
        // Configure VehicleRepository.findAll(...).
        final Vehicle vehicle = new Vehicle();
        vehicle.setId(0L);
        vehicle.setFactory("factory");
        vehicle.setName("name");
        vehicle.setVinCode("vinCode");
        vehicle.setRegistrationNumber("registrationNumber");
        vehicle.setConditionMotorcycleWatch(0.0);
        vehicle.setMileage(0.0);
        vehicle.setBoughtAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicle.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicle.setVehicleReports(List.of(VehicleReport.builder().build()));
        vehicle.setType(VehicleType.CAR);
        final List<Vehicle> vehicles = List.of(vehicle);
        when(mockVehicleDao.findAll(any(Sort.class))).thenReturn(vehicles);

        // Configure VehicleMapper.convertToDto(...).
        final VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(0L);
        vehicleDTO.setFactory("factory");
        vehicleDTO.setName("name");
        vehicleDTO.setRegistrationNumber("registrationNumber");
        vehicleDTO.setConditionMotorcycleWatch(0.0);
        vehicleDTO.setMileage(0.0);
        vehicleDTO.setType("type");
        vehicleDTO.setBoughtAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleDTO.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleDTO.setQrCode("qrCode");
        vehicleDTO.setVinCode("vinCode");
        when(mockVehicleMapper.convertToDto(any(Vehicle.class))).thenReturn(vehicleDTO);

        // Run the test
        final List<VehicleDTO> result = vehicleServiceImplUnderTest.getVehicles();

        // Verify the results
        verify(mockVehicleDao).findAll(any(Sort.class));
        verify(mockVehicleMapper).convertToDto(any(Vehicle.class));
    }

    @Test
    void testGetInputVehicles() {
        // Setup
        // Configure VehicleRepository.findAll(...).
        final Vehicle vehicle = new Vehicle();
        vehicle.setId(0L);
        vehicle.setFactory("factory");
        vehicle.setName("name");
        vehicle.setVinCode("vinCode");
        vehicle.setRegistrationNumber("registrationNumber");
        vehicle.setConditionMotorcycleWatch(0.0);
        vehicle.setMileage(0.0);
        vehicle.setBoughtAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicle.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicle.setVehicleReports(List.of(VehicleReport.builder().build()));
        vehicle.setType(VehicleType.CAR);
        final List<Vehicle> vehicles = List.of(vehicle);
        when(mockVehicleDao.findAll()).thenReturn(vehicles);

        // Configure VehicleMapper.convertToInputDto(...).
        final VehicleInputDTO vehicleInputDTO = new VehicleInputDTO();
        vehicleInputDTO.setId(0L);
        vehicleInputDTO.setFactory("factory");
        vehicleInputDTO.setName("name");
        vehicleInputDTO.setRegistrationNumber("registrationNumber");
        vehicleInputDTO.setType(VehicleType.CAR);
        when(mockVehicleMapper.convertToInputDto(eq(vehicle))).thenReturn(vehicleInputDTO);

        // Run the test
        final List<VehicleInputDTO> result = vehicleServiceImplUnderTest.getInputVehicles();

        // Verify that the findAll() method of mockVehicleDao is called exactly once
        verify(mockVehicleDao, times(1)).findAll();

        // Verify that the convertToInputDto() method of mockVehicleMapper is called exactly once
        verify(mockVehicleMapper, times(1)).convertToInputDto(any(Vehicle.class));

    }



    @Test
    void testGetInputVehicles_VehicleRepositoryReturnsNoItems() {
        // Setup
        when(mockVehicleDao.findAll()).thenReturn(Collections.emptyList());

        // Run the test
        final List<VehicleInputDTO> result = vehicleServiceImplUnderTest.getInputVehicles();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testUpdate() throws Exception {
        // Setup
        final VehicleRequest request = VehicleRequest.builder().build();

        // Configure VehicleMapper.convertToEntity(...).
        final Vehicle vehicle = new Vehicle();
        vehicle.setId(0L);
        vehicle.setFactory("factory");
        vehicle.setName("name");
        vehicle.setVinCode("vinCode");
        vehicle.setRegistrationNumber("registrationNumber");
        vehicle.setConditionMotorcycleWatch(0.0);
        vehicle.setMileage(0.0);
        vehicle.setBoughtAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicle.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicle.setVehicleReports(List.of(VehicleReport.builder().build()));
        vehicle.setType(VehicleType.CAR);
        when(mockVehicleMapper.convertToEntity(any(VehicleRequest.class))).thenReturn(vehicle);

        // Configure VehicleRepository.save(...).
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
        vehicle1.setVehicleReports(List.of(VehicleReport.builder().build()));
        vehicle1.setType(VehicleType.CAR);
        when(mockVehicleDao.save(any(Vehicle.class))).thenReturn(vehicle1);

        // Configure VehicleMapper.convertToDto(...).
        final VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(0L);
        vehicleDTO.setFactory("factory");
        vehicleDTO.setName("name");
        vehicleDTO.setRegistrationNumber("registrationNumber");
        vehicleDTO.setConditionMotorcycleWatch(0.0);
        vehicleDTO.setMileage(0.0);
        vehicleDTO.setType("type");
        vehicleDTO.setBoughtAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleDTO.setCreatedAt(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        vehicleDTO.setQrCode("qrCode");
        vehicleDTO.setVinCode("vinCode");
        when(mockVehicleMapper.convertToDto(any(Vehicle.class))).thenReturn(vehicleDTO);

        // Run the test
        final VehicleDTO result = vehicleServiceImplUnderTest.update(request);

        // Verify the results
        verify(mockVehicleDao, times(1)).save(any(Vehicle.class));
        verify(mockVehicleMapper, times(1)).convertToDto(any(Vehicle.class));
    }


    @Test
    void testUpdate_VehicleMapperConvertToEntityThrowsParseException() throws Exception {
        // Setup
        final VehicleRequest request = VehicleRequest.builder().build();
        when(mockVehicleMapper.convertToEntity(VehicleRequest.builder().build())).thenThrow(ParseException.class);

        // Run the test
        assertThatThrownBy(() -> vehicleServiceImplUnderTest.update(request)).isInstanceOf(ParseException.class);
    }

    @Test
    void testDeleteVehicle() {
        // Setup
        // Run the test
        vehicleServiceImplUnderTest.deleteVehicle(0L);

        // Verify the results
        verify(mockVehicleDao).deleteById(0L);
    }
}
