package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.WorkReportRequest;
import cz.cvut.fel.constructa.dto.response.WorkReportDTO;
import cz.cvut.fel.constructa.model.report.WorkReport;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * The type Work report mapper.
 */
@Component
@RequiredArgsConstructor
public class WorkReportMapper {
    /**
     * The Model mapper.
     */
    private final ModelMapper modelMapper;

    /**
     * Convert to dto work report dto.
     *
     * @param report the report
     * @return the work report dto
     */
    public WorkReportDTO convertToDto(WorkReport report) {
        return modelMapper.map(report, WorkReportDTO.class);
    }

    /**
     * Convert to entity work report.
     *
     * @param request the request
     * @return the work report
     * @throws ParseException the parse exception
     */
    public WorkReport convertToEntity(WorkReportRequest request) throws ParseException {
        return modelMapper.map(request, WorkReport.class);
    }
}
