package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.CompanyRequest;
import cz.cvut.fel.constructa.dto.request.VehicleReportRequest;
import cz.cvut.fel.constructa.dto.response.VehicleReportDTO;
import cz.cvut.fel.constructa.model.Company;
import cz.cvut.fel.constructa.model.report.VehicleReport;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class VehicleReportMapper {
    @Autowired
    private ModelMapper modelMapper;

    public VehicleReportDTO convertToDto(VehicleReport report) {
        return modelMapper.map(report, VehicleReportDTO.class);
    }
    public VehicleReport convertToEntity(VehicleReportRequest request) throws ParseException {
        return modelMapper.map(request, VehicleReport.class);
    }
}
