package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.WorkReportRequest;
import cz.cvut.fel.constructa.dto.response.WorkReportDTO;
import cz.cvut.fel.constructa.model.report.WorkReport;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class WorkReportMapper {
    private final ModelMapper modelMapper;
    public WorkReportDTO convertToDto(WorkReport report) {
        return modelMapper.map(report, WorkReportDTO.class);
    }

    public WorkReport convertToEntity(WorkReportRequest request) throws ParseException {
        return modelMapper.map(request, WorkReport.class);
    }
}
