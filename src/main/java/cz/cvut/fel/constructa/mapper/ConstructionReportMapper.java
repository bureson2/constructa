package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.ConstructionReportRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionReportDTO;
import cz.cvut.fel.constructa.model.report.ConstructionReport;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * The type Construction report mapper.
 */
@Component
@RequiredArgsConstructor
public class ConstructionReportMapper {
    /**
     * The Model mapper.
     */
    private final ModelMapper modelMapper;

    /**
     * Convert to dto construction report dto.
     *
     * @param constructionReport the construction report
     * @return the construction report dto
     */
    public ConstructionReportDTO convertToDto(ConstructionReport constructionReport) {
        return modelMapper.map(constructionReport, ConstructionReportDTO.class);
    }

    /**
     * Convert to entity construction report.
     *
     * @param request the request
     * @return the construction report
     * @throws ParseException the parse exception
     */
    public ConstructionReport convertToEntity(ConstructionReportRequest request) throws ParseException {
        return modelMapper.map(request, ConstructionReport.class);
    }
}
