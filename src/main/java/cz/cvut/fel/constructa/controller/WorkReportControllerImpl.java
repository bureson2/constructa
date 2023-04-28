package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.WorkReportController;
import cz.cvut.fel.constructa.dto.request.AttendanceRequest;
import cz.cvut.fel.constructa.dto.request.IllnessRequest;
import cz.cvut.fel.constructa.dto.request.StopAttendanceRequest;
import cz.cvut.fel.constructa.dto.request.WorkReportRequest;
import cz.cvut.fel.constructa.dto.response.LocationDTO;
import cz.cvut.fel.constructa.dto.response.WorkReportDTO;
import cz.cvut.fel.constructa.service.interfaces.WorkReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

/**
 * The type Work report controller.
 */
@CrossOrigin
@RestController
    @RequestMapping("/api/v1/work-reports")
@RequiredArgsConstructor
public class WorkReportControllerImpl implements WorkReportController {
    /**
     * The Work report service.
     */
    private final WorkReportService workReportService;

    /**
     * Gets work reports.
     *
     * @return the work reports
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WorkReportDTO>> getWorkReports() {
        return ResponseEntity.ok().body(
                workReportService.getWorkReports()
        );
    }

    /**
     * Gets my work reports.
     *
     * @return the my work reports
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/my", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WorkReportDTO>> getMyWorkReports() {
        return ResponseEntity.ok().body(
                workReportService.getMyWorkReports()
        );
    }

    /**
     * Gets users work reports.
     *
     * @param userId the user id
     * @return the users work reports
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WorkReportDTO>> getUsersWorkReports(@PathVariable Long userId) {
        return ResponseEntity.ok().body(
                workReportService.getWorkReportsByReportingEmployeeId(userId)
        );
    }

    /**
     * Gets work report.
     *
     * @param workReportId the work report id
     * @return the work report
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{workReportId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkReportDTO> getWorkReport(@PathVariable Long workReportId) {
        WorkReportDTO report = workReportService.getWorkReportById(workReportId);
        if (report != null) {
            return ResponseEntity.ok().body(
                    report
            );
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Gets work location.
     *
     * @param locationId the location id
     * @return the work location
     */
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/location/{locationId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LocationDTO> getWorkLocation(@PathVariable Long locationId) {
        LocationDTO location = workReportService.getWorklocationById(locationId);
        if (location != null) {
            return ResponseEntity.ok().body(
                    location
            );
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Create work report response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkReportDTO> createWorkReport(@RequestBody WorkReportRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                workReportService.create(request)
        );
    }

    /**
     * Record illness response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(value = "/illness", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> recordIllness(@RequestBody IllnessRequest request) {
        workReportService.recordIllness(request);
        return ResponseEntity.noContent().build();
    }

    /**
     * Record attendance response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(value = "/attendance", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkReportDTO> recordAttendance(@RequestBody AttendanceRequest request) {
        return ResponseEntity.ok().body(
                workReportService.recordWorkReport(request)
        );
    }

    /**
     * Stop record attendance response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(value = "/end-work", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> stopRecordAttendance(@RequestBody StopAttendanceRequest request) {
        workReportService.stopWorkReportRecord(request);
        return ResponseEntity.noContent().build();
    }


    /**
     * Update work report response entity.
     *
     * @param request the request
     * @return the response entity
     * @throws ParseException the parse exception
     */
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkReportDTO> updateWorkReport(@RequestBody WorkReportRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                workReportService.update(request)
        );
    }

    /**
     * Delete work report response entity.
     *
     * @param workReportId the work report id
     * @return the response entity
     */
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{workReportId}")
    public ResponseEntity<Void> deleteWorkReport(@PathVariable Long workReportId) {
        workReportService.delete(workReportId);
        return ResponseEntity.noContent().build();
    }
}
