package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.VehicleRequest;
import cz.cvut.fel.constructa.dto.response.VehicleDTO;
import cz.cvut.fel.constructa.dto.response.VehicleInputDTO;
import cz.cvut.fel.constructa.model.Vehicle;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * The type Vehicle mapper.
 */
@Component
@RequiredArgsConstructor
public class VehicleMapper {
    /**
     * The Model mapper.
     */
    private final ModelMapper modelMapper;

    /**
     * Convert to dto vehicle dto.
     *
     * @param vehicle the vehicle
     * @return the vehicle dto
     */
    public VehicleDTO convertToDto(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleDTO.class);
    }

    /**
     * Convert to input dto vehicle input dto.
     *
     * @param vehicle the vehicle
     * @return the vehicle input dto
     */
    public VehicleInputDTO convertToInputDto(Vehicle vehicle) {
        return modelMapper.map(vehicle, VehicleInputDTO.class);
    }

    /**
     * Convert to entity vehicle.
     *
     * @param request the request
     * @return the vehicle
     * @throws ParseException the parse exception
     */
    public Vehicle convertToEntity(VehicleRequest request) throws ParseException {
        return modelMapper.map(request, Vehicle.class);
    }
}
