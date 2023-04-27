package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.VehicleRequest;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleInputDTO;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Vehicle service.
 */
public interface VehicleService {
    /**
     * Create vehicle dto.
     *
     * @param request the request
     * @return the vehicle dto
     * @throws ParseException the parse exception
     */
    VehicleDTO create(VehicleRequest request) throws ParseException;

    /**
     * Gets vehicle by id.
     *
     * @param id the id
     * @return the vehicle by id
     */
    VehicleDTO getVehicleById(Long id);

    /**
     * Gets vehicles.
     *
     * @return the vehicles
     */
    List<VehicleDTO> getVehicles();

    /**
     * Gets input vehicles.
     *
     * @return the input vehicles
     */
    List<VehicleInputDTO> getInputVehicles();

    /**
     * Delete vehicle.
     *
     * @param id the id
     */
    void deleteVehicle(Long id);

    /**
     * Update vehicle dto.
     *
     * @param request the request
     * @return the vehicle dto
     * @throws ParseException the parse exception
     */
    VehicleDTO update(VehicleRequest request) throws ParseException;
}
