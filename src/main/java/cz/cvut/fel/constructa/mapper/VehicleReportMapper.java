package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * The type Vehicle report mapper.
 */
@Component
@RequiredArgsConstructor
public class VehicleReportMapper {
    /**
     * The Model mapper.
     */
    private final ModelMapper modelMapper;

    /**
     * Convert to dto vehicle report dto.
     *
     * @param report the report
     * @return the vehicle report dto
     */
    public VehicleReportDTO convertToDto(VehicleReport report) {
        return modelMapper.map(report, VehicleReportDTO.class);
    }

    /**
     * Convert to entity vehicle report.
     *
     * @param request the request
     * @return the vehicle report
     * @throws ParseException the parse exception
     */
    public VehicleReport convertToEntity(VehicleReportRequest request) throws ParseException {
        return VehicleReport.builder()
                .originalConditionMotorcycleWatch(request.getOriginalConditionMotorcycleWatch())
                .afterworkConditionMotorcycleWatch(request.getAfterworkConditionMotorcycleWatch())
                .cargoMass(request.getCargoMass())
                .cargoType(request.getCargoType())
                .distance(request.getDistance())
                .purchaseOfFuelLitres(request.getPurchaseOfFuelLitres())
                .timeFrom(request.getTimeFrom())
                .timeTo(request.getTimeTo())
                .description(request.getDescription())
                .build();
//        return modelMapper.map(request, VehicleReport.class);
    }
}
