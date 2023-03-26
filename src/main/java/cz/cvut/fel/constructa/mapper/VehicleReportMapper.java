package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class VehicleReportMapper {
    private final ModelMapper modelMapper;

    public VehicleReportDTO convertToDto(VehicleReport report) {
        return modelMapper.map(report, VehicleReportDTO.class);
    }
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
                .build();
//        return modelMapper.map(request, VehicleReport.class);
    }
}
