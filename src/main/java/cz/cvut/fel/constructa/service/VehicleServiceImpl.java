package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.repository.VehicleRepository;
import cz.cvut.fel.constructa.service.interfaces.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleDao;
    private final UserRepository userDao;


    @Override
    public Vehicle create(Vehicle newVehicle) {
        return vehicleDao.save(newVehicle);
    }

//    TODO check speed
    @Override
    public Optional<Vehicle> getVehicleById(Long id) {
        return vehicleDao.findById(id);
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleDao.findAll();
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleDao.deleteById(id);
    }

    @Override
    public Vehicle update(Vehicle updatedVehicle) {
        return vehicleDao.save(updatedVehicle);
    }
}
