package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.ConstructionReportRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionReportDTO;
import cz.cvut.fel.constructa.model.report.ConstructionReport;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Construction report service.
 */
public interface ConstructionReportService {
    /**
     * Create construction report dto.
     *
     * @param request the request
     * @return the construction report dto
     * @throws ParseException the parse exception
     */
    ConstructionReportDTO create(ConstructionReportRequest request) throws ParseException;

    /**
     * Gets construction reportt by id.
     *
     * @param id the id
     * @return the construction reportt by id
     */
    ConstructionReportDTO getConstructionReporttById(Long id);

    /**
     * Gets construction reports.
     *
     * @return the construction reports
     */
    List<ConstructionReportDTO> getConstructionReports();

    /**
     * Gets construction reports by project id.
     *
     * @param projectId the project id
     * @return the construction reports by project id
     */
    List<ConstructionReportDTO> getConstructionReportsByProjectId(Long projectId);

    /**
     * Delete.
     *
     * @param id the id
     */
    void delete(Long id);

    /**
     * Update construction report.
     *
     * @param constructionReport the construction report
     * @return the construction report
     */
    ConstructionReport update(ConstructionReportRequest constructionReport);
}
