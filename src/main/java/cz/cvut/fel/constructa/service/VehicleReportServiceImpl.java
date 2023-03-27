package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.mapper.VehicleReportMapper;
import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import cz.cvut.fel.constructa.model.role.User;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.repository.VehicleReportRepository;
import cz.cvut.fel.constructa.repository.VehicleRepository;
import cz.cvut.fel.constructa.service.interfaces.VehicleReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleReportServiceImpl implements VehicleReportService {
    private final VehicleReportRepository vehicleReportDao;
    private final UserRepository userDao;
    private final VehicleRepository vehicleDao;
    private final VehicleReportMapper vehicleReportMapper;


    @Override
    public VehicleReport create(VehicleReportRequest request) throws ParseException {
        VehicleReport createdReport = vehicleReportMapper.convertToEntity(request);
        Optional<User> driver = userDao.findById(request.getDriver());
        driver.ifPresent(createdReport::setDriver);
        Optional<Vehicle> vehicle = vehicleDao.findById(request.getVehicle());
        vehicle.ifPresent(createdReport::setVehicle);
        return vehicleReportDao.save(createdReport);
    }

    //    TODO check speed
    @Override
    public Optional<VehicleReport> getVehicleReportById(Long id) {
//        return vehicleReportDao.findById(id);
        return vehicleReportDao.findAll().stream().filter(it->it.getId().equals(id)).findFirst();
    }


    @Override
    public List<VehicleReport> getVehicleReportsByVehicleId(Long id) {
        return vehicleReportDao.findVehicleReportByVehicleId(id);
    }

    @Override
    public List<VehicleReport> getVehicleReports() {
        return vehicleReportDao.findAll();
    }

    @Override
    public void deleteVehicleReport(Long id) {
        vehicleReportDao.deleteById(id);
    }

    @Override
    public VehicleReport update(VehicleReport updatedVehicleReport) {
        return vehicleReportDao.save(updatedVehicleReport);
    }


}
