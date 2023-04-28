package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.response.LocationDTO;
import cz.cvut.fel.constructa.model.Location;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * The type Location mapper.
 */
@Component
@RequiredArgsConstructor
public class LocationMapper {
    /**
     * The Model mapper.
     */
    private final ModelMapper modelMapper;

    /**
     * Convert to dto location dto.
     *
     * @param location the location
     * @return the location dto
     */
    public LocationDTO convertToDto(Location location) {
        return modelMapper.map(location, LocationDTO.class);
    }
}
