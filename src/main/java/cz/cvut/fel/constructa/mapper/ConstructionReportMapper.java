package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.ConstructionReportRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionReportDTO;
import cz.cvut.fel.constructa.model.report.ConstructionReport;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class ConstructionReportMapper {
    private final ModelMapper modelMapper;
    public ConstructionReportDTO convertToDto(ConstructionReport constructionReport) {
        return modelMapper.map(constructionReport, ConstructionReportDTO.class);
    }

    public ConstructionReport convertToEntity(ConstructionReportRequest request) throws ParseException {
        return modelMapper.map(request, ConstructionReport.class);
    }
}
