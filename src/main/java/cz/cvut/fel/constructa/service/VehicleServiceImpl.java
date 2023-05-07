package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.VehicleRequest;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleInputDTO;
import cz.cvut.fel.constructa.mapper.VehicleMapper;
import cz.cvut.fel.constructa.model.Vehicle;
import cz.cvut.fel.constructa.repository.VehicleRepository;
import cz.cvut.fel.constructa.service.interfaces.VehicleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Vehicle service.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    /**
     * The Vehicle dao.
     */
    private final VehicleRepository vehicleDao;

    /**
     * The Vehicle mapper.
     */
    private final VehicleMapper vehicleMapper;

    /**
     * Create vehicle dto.
     *
     * @param request the request
     * @return the vehicle dto
     * @throws ParseException the parse exception
     */
    @Override
    public VehicleDTO create(VehicleRequest request) throws ParseException {
        Vehicle vehicle = vehicleMapper.convertToEntity(request);
        vehicle = vehicleDao.save(vehicle);

        log.info("New vehicle with id {} was added.", vehicle.getId());

        return vehicleMapper.convertToDto(vehicle);
    }

    /**
     * Gets vehicle by id.
     *
     * @param id the id
     * @return the vehicle by id
     */
    @Override
    public VehicleDTO getVehicleById(Long id) {
        Optional<Vehicle> vehicle = vehicleDao.findById(id);
        return vehicle.map(vehicleMapper::convertToDto).orElse(null);
    }

    /**
     * Gets vehicles.
     *
     * @return the vehicles
     */
    @Override
    public List<VehicleDTO> getVehicles() {
        Sort sortByName = Sort.by(Sort.Direction.ASC, "registrationNumber");
        List<Vehicle> vehicles = vehicleDao.findAll(sortByName);
        return vehicles.stream()
                .map(vehicleMapper::convertToDto)
                .collect(Collectors.toList());
    }

    /**
     * Gets input vehicles.
     *
     * @return the input vehicles
     */
    @Override
    public List<VehicleInputDTO> getInputVehicles() {
        List<Vehicle> vehicles = vehicleDao.findAll();
        return vehicles.stream()
                .map(vehicleMapper::convertToInputDto)
                .collect(Collectors.toList());
    }

    /**
     * Update vehicle dto.
     *
     * @param request the request
     * @return the vehicle dto
     * @throws ParseException the parse exception
     */
    @Override
    public VehicleDTO update(VehicleRequest request) throws ParseException {
        Vehicle vehicle = vehicleMapper.convertToEntity(request);
        vehicle = vehicleDao.save(vehicle);
        return vehicleMapper.convertToDto(vehicle);
    }

    /**
     * Delete vehicle.
     *
     * @param id the id
     */
    @Override
    public void deleteVehicle(Long id) {
        vehicleDao.deleteById(id);
        log.info("Vehicle with id {} was deleted.", id);
    }
}
