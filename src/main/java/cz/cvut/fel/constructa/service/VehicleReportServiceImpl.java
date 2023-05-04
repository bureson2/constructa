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
import org.springframework.data.domain.Sort;

/**
 * The type Vehicle report service.
 */
@Service
@RequiredArgsConstructor
public class VehicleReportServiceImpl implements VehicleReportService {
    /**
     * The Vehicle report dao.
     */
    private final VehicleReportRepository vehicleReportDao;
    /**
     * The User dao.
     */
    private final UserRepository userDao;
    /**
     * The Vehicle dao.
     */
    private final VehicleRepository vehicleDao;
    /**
     * The Vehicle report mapper.
     */
    private final VehicleReportMapper vehicleReportMapper;
    /**
     * The Authentication facade.
     */
    private final AuthenticationFacade authenticationFacade;

    /**
     * Create vehicle report dto.
     *
     * @param request the request
     * @return the vehicle report dto
     * @throws ParseException the parse exception
     */
    @Override
    public VehicleReportDTO create(VehicleReportRequest request) throws ParseException {
        VehicleReport createdReport = vehicleReportMapper.convertToEntity(request);

        Optional<User> driver = Optional.empty();

        if(request.getDriver() != null){
            driver = userDao.findById(request.getDriver());
        }
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

    /**
     * Gets vehicle report by id.
     *
     * @param id the id
     * @return the vehicle report by id
     */
    @Override
    public VehicleReportDTO getVehicleReportById(Long id) {
        Optional<VehicleReport> vehicleReport = vehicleReportDao.findAll().stream().filter(it -> it.getId().equals(id)).findFirst();
        return vehicleReport.map(vehicleReportMapper::convertToDto).orElse(null);
    }

    /**
     * Gets vehicle reports by vehicle id.
     *
     * @param id the id
     * @return the vehicle reports by vehicle id
     */
    @Override
    public List<VehicleReportDTO> getVehicleReportsByVehicleId(Long id) {
        Sort sortByDate = Sort.by(Sort.Direction.DESC, "timeTo");
        List<VehicleReport> vehicleReports = vehicleReportDao.findVehicleReportByVehicleId(id, sortByDate);
        return vehicleReports.stream()
                .map(vehicleReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Gets vehicle reports.
     *
     * @return the vehicle reports
     */
    @Override
    public List<VehicleReportDTO> getVehicleReports() {
        List<VehicleReport> vehicleReports = vehicleReportDao.findAll();
        return vehicleReports.stream()
                .map(vehicleReportMapper::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Delete vehicle report.
     *
     * @param id the id
     */
    @Override
    public void deleteVehicleReport(Long id) {
        vehicleReportDao.deleteById(id);
    }

    /**
     * Update vehicle report dto.
     *
     * @param request the request
     * @return the vehicle report dto
     * @throws ParseException the parse exception
     */
    @Override
    public VehicleReportDTO update(VehicleReportRequest request) throws ParseException {

        Optional<VehicleReport> report = vehicleReportDao.findById(request.getId());
        User driver = null;
        Vehicle vehicle = null;

        if (report.isPresent()) {
            driver = report.get().getDriver();
            vehicle = report.get().getVehicle();
        }
        VehicleReport updatedVehicleReport = vehicleReportMapper.convertToEntity(request);
        updatedVehicleReport.setVehicle(vehicle);
        updatedVehicleReport.setDriver(driver);
        updatedVehicleReport = vehicleReportDao.save(updatedVehicleReport);

        return vehicleReportMapper.convertToDto(updatedVehicleReport);

    }
}
