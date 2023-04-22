package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;
import cz.cvut.fel.constructa.mapper.VehicleReportMapper;
import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.repository.VehicleReportRepository;
import cz.cvut.fel.constructa.repository.VehicleRepository;
import cz.cvut.fel.constructa.security.AuthenticationFacade;
import cz.cvut.fel.constructa.service.interfaces.VehicleReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleReportServiceImpl implements VehicleReportService {
    private final VehicleReportRepository vehicleReportDao;
    private final UserRepository userDao;
    private final VehicleRepository vehicleDao;
    private final VehicleReportMapper vehicleReportMapper;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public VehicleReportDTO create(VehicleReportRequest request) throws ParseException {
        VehicleReport createdReport = vehicleReportMapper.convertToEntity(request);

        Optional<User> driver = userDao.findById(request.getDriver());
        if(driver.isPresent()){
            createdReport.setDriver(driver.get());
        } else {
            String authorEmail = authenticationFacade.getAuthentication().getName();
            Optional<User> author = userDao.findByEmail(authorEmail);
            author.ifPresent(createdReport::setDriver);
        }

        Optional<Vehicle> vehicle = vehicleDao.findById(request.getVehicle());
        vehicle.ifPresent(createdReport::setVehicle);

        if(vehicle.isPresent()){
            vehicle.get().setConditionMotorcycleWatch(request.getAfterworkConditionMotorcycleWatch());
            vehicleDao.save(vehicle.get());
        }

        createdReport = vehicleReportDao.save(createdReport);
        return vehicleReportMapper.convertToDto(createdReport);
    }

    @Override
    public VehicleReportDTO getVehicleReportById(Long id) {
        Optional<VehicleReport> vehicleReport = vehicleReportDao.findAll().stream().filter(it -> it.getId().equals(id)).findFirst();
        return vehicleReport.map(vehicleReportMapper::convertToDto).orElse(null);
    }

    @Override
    public List<VehicleReportDTO> getVehicleReportsByVehicleId(Long id) {
        List<VehicleReport> vehicleReports = vehicleReportDao.findVehicleReportByVehicleId(id);
        return vehicleReports.stream()
                .map(vehicleReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleReportDTO> getVehicleReports() {
        List<VehicleReport> vehicleReports = vehicleReportDao.findAll();
        return vehicleReports.stream()
                .map(vehicleReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteVehicleReport(Long id) {
        vehicleReportDao.deleteById(id);
    }

    @Override
    public VehicleReportDTO update(VehicleReportRequest request) throws ParseException {

        Optional<VehicleReport> report = vehicleReportDao.findById(request.getId());
        User driver = null;
        Vehicle vehicle = null;

        if (report.isPresent()) {
            driver = report.get().getDriver();
            if (!Objects.equals(driver.getId(), request.getDriver())) {
                report.get().setDriver(userDao.findById(request.getDriver()).get());
            }
            vehicle = report.get().getVehicle();
            if (!Objects.equals(vehicle.getId(), request.getVehicle())) {
                report.get().setVehicle(vehicleDao.findById(request.getVehicle()).get());
            }
        }
        VehicleReport updatedVehicleReport = vehicleReportMapper.convertToEntity(request);
        updatedVehicleReport.setVehicle(vehicle);
        updatedVehicleReport.setDriver(driver);
        updatedVehicleReport = vehicleReportDao.save(updatedVehicleReport);

        return vehicleReportMapper.convertToDto(updatedVehicleReport);

    }
}
