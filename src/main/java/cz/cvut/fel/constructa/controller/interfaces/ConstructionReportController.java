package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.ConstructionReportRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionReportDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Construction report controller.
 */
@RequestMapping("/api/v1/construction-reports")
public interface ConstructionReportController {
    /**
     * Gets construction reports.
     *
     * @return the construction reports
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ConstructionReportDTO>> getConstructionReports();

    /**
     * Gets construction reports by project id.
     *
     * @param projectId the project id
     * @return the construction reports by project id
     */
    @GetMapping(value="project/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ConstructionReportDTO>> getConstructionReportsByProjectId(@PathVariable Long projectId);

    /**
     * Gets construction report.
     *
     * @param constructionReportId the construction report id
     * @return the construction report
     */
    @GetMapping(value = "/{constructionReportId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ConstructionReportDTO> getConstructionReport(@PathVariable Long constructionReportId);

    /**
     * Create construction report response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ConstructionReportDTO> createConstructionReport(@RequestBody ConstructionReportRequest request) throws ParseException;

    /**
     * Delete construction report response entity.
     *
     * @param constructionReportId the construction report id
     * @return the response entity
     */
    @DeleteMapping(value = "/{constructionReportId}")
    ResponseEntity<Void> deleteConstructionReport(@PathVariable Long constructionReportId);

    }
