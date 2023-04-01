package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.VehicleRequest;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleInputDTO;
import cz.cvut.fel.constructa.mapper.VehicleMapper;
import cz.cvut.fel.constructa.model.Company;
import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.repository.UserRepository;
import cz.cvut.fel.constructa.repository.VehicleRepository;
import cz.cvut.fel.constructa.service.interfaces.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleDao;
    private final UserRepository userDao;
    private final VehicleMapper vehicleMapper;


    @Override
    public VehicleDTO create(VehicleRequest request) throws ParseException {
        Vehicle vehicle = vehicleMapper.convertToEntity(request);
        vehicle = vehicleDao.save(vehicle);
        return vehicleMapper.convertToDto(vehicle);
    }

    @Override
    public VehicleDTO getVehicleById(Long id) {
        Optional<Vehicle> vehicle = vehicleDao.findById(id);
        return vehicle.map(vehicleMapper::convertToDto).orElse(null);
    }

    @Override
    public List<VehicleDTO> getVehicles() {
        List<Vehicle> vehicles = vehicleDao.findAll();
        return vehicles.stream()
                .map(vehicleMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleInputDTO> getInputVehicles() {
        List<Vehicle> vehicles = vehicleDao.findAll();
        return vehicles.stream()
                .map(vehicleMapper::convertToInputDto)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleDTO update(VehicleRequest request) throws ParseException {
        Vehicle vehicle = vehicleMapper.convertToEntity(request);
        vehicle = vehicleDao.save(vehicle);
        return vehicleMapper.convertToDto(vehicle);
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleDao.deleteById(id);
    }
}
