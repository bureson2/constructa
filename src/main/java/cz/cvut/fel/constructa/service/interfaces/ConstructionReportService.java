package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.ConstructionReportRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionReportDTO;
import cz.cvut.fel.constructa.model.report.ConstructionReport;

import java.text.ParseException;
import java.util.List;

public interface ConstructionReportService {
    ConstructionReportDTO create(ConstructionReportRequest request) throws ParseException;
    ConstructionReportDTO getConstructionReporttById(Long id);
    List<ConstructionReportDTO> getConstructionReports();
    List<ConstructionReportDTO> getConstructionReportsByProjectId(Long projectId);
    void delete(Long id);
    ConstructionReport update(ConstructionReportRequest constructionReport);
}
