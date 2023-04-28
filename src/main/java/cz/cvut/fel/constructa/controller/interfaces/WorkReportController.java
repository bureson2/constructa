package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.IllnessRequest;
import cz.cvut.fel.constructa.dto.request.StopAttendanceRequest;
import cz.cvut.fel.constructa.dto.request.WorkReportRequest;
import cz.cvut.fel.constructa.dto.response.WorkReportDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * The interface Work report controller.
 */
@RequestMapping("/api/v1/work-reports")
public interface WorkReportController {
    /**
     * Gets work reports.
     *
     * @return the work reports
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<WorkReportDTO>> getWorkReports();

    /**
     * Gets users work reports.
     *
     * @param userId the user id
     * @return the users work reports
     */
    @GetMapping(value="/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<WorkReportDTO>> getUsersWorkReports(@PathVariable Long userId);

    /**
     * Gets my work reports.
     *
     * @return my work reports
     */
    @GetMapping(value="/my", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<WorkReportDTO>> getMyWorkReports();

    /**
     * Gets work report.
     *
     * @param workReportId the work report id
     * @return the work report
     */
    @GetMapping(value = "/{workReportId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WorkReportDTO> getWorkReport(@PathVariable Long workReportId);

    /**
     * Create work report response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WorkReportDTO> createWorkReport(@RequestBody WorkReportRequest request) throws ParseException;

    /**
     * Record illness response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping(value = "/illness", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> recordIllness(@RequestBody IllnessRequest request);

    /**
     * Stop record attendance response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping(value = "/end-work", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> stopRecordAttendance(@RequestBody StopAttendanceRequest request);

    /**
     * Update work report response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WorkReportDTO> updateWorkReport(@RequestBody WorkReportRequest request) throws ParseException;

    /**
     * Delete work report response entity.
     *
     * @param workReportId the work report id
     * @return the response entity
     */
    @DeleteMapping(value = "/{workReportId}")
    ResponseEntity<Void> deleteWorkReport(@PathVariable Long workReportId);
}
