package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.WorkReportRequest;
import cz.cvut.fel.constructa.dto.response.WorkReportDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

public interface WorkReportController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<WorkReportDTO>> getWorkReports();

    @GetMapping(value="/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<WorkReportDTO>> getUsersWorkReports(@PathVariable Long userId);

    @GetMapping(value = "/{workReportId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WorkReportDTO> getWorkReport(@PathVariable Long workReportId);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WorkReportDTO> createWorkReport(@RequestBody WorkReportRequest request) throws ParseException;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<WorkReportDTO> updateeWorkReport(@RequestBody WorkReportRequest request) throws ParseException;

    @DeleteMapping(value = "/{workReportId}")
    ResponseEntity<Void> deleteWorkReport(@PathVariable Long workReportId);
}
