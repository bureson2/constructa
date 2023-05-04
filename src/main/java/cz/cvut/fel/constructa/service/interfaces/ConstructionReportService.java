package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.ConstructionReportRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionReportDTO;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Construction report service.
 */
public interface ConstructionReportService {
    /**
     * Creates a new construction report based on the provided request.
     *
     * @param request the construction report request
     * @return the created construction report DTO
     * @throws ParseException if there was an error parsing a date in the request
     */
    ConstructionReportDTO create(ConstructionReportRequest request) throws ParseException;

    /**
     * Retrieves the construction report with the specified ID.
     *
     * @param id the ID of the construction report to retrieve
     * @return the construction report DTO, or null if no such report exists
     */
    ConstructionReportDTO getConstructionReporttById(Long id);

    /**
     * Retrieves all construction reports.
     *
     * @return a list of all construction report DTOs
     */
    List<ConstructionReportDTO> getConstructionReports();

     /**
     * Retrieves all construction reports for the project with the specified ID.
     *
     * @param projectId the ID of the project for which to retrieve reports
     * @return a list of all construction report DTOs for the specified project
     */
    List<ConstructionReportDTO> getConstructionReportsByProjectId(Long projectId);

    /**
     * Deletes the construction report with the specified ID.
     *
     * @param id the ID of the construction report to delete
     */
    void delete(Long id);

    /**
     * Updates the specified construction report.
     *
     * @param constructionReport the construction report to update
     * @return the updated construction report
     */
    ConstructionReportDTO update(ConstructionReportRequest constructionReport);
}
