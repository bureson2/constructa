package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.WorkReportController;
import cz.cvut.fel.constructa.dto.request.WorkReportRequest;
import cz.cvut.fel.constructa.dto.response.WorkReportDTO;
import cz.cvut.fel.constructa.mapper.WorkReportMapper;
import cz.cvut.fel.constructa.model.report.WorkReport;
import cz.cvut.fel.constructa.service.interfaces.WorkReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/work-reports")
@RequiredArgsConstructor
public class WorkReportControllerImpl implements WorkReportController {
    private final WorkReportService workReportService;
    private final WorkReportMapper workReportMapper;

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WorkReportDTO>> getWorkReports() {
        return ResponseEntity.ok().body(
                workReportService.getWorkReports()
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value="/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WorkReportDTO>> getUsersWorkReports(@PathVariable Long userId) {
        return ResponseEntity.ok().body(
                workReportService.getWorkReportsByReportingEmployeeId(userId)
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{workReportId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkReportDTO> getWorkReport(@PathVariable Long workReportId) {
        Optional<WorkReport> taskToReturn = workReportService.getWorkReportById(workReportId);
        return taskToReturn.map(report -> ResponseEntity.ok().body(
                workReportMapper.convertToDto(report)
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkReportDTO> createWorkReport(@RequestBody WorkReportRequest request) throws ParseException {
        WorkReport report = workReportService.create(request);
        return ResponseEntity.ok().body(
                workReportMapper.convertToDto(report)
        );
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkReportDTO> updateeWorkReport(@RequestBody WorkReportRequest request) throws ParseException {
//        WorkReport report = workReportService.update(request);
//        return ResponseEntity.ok().body(
//                workReportMapper.convertToDto(report)
//        );
//        todo
        return null;
    }

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{workReportId}")
    public ResponseEntity<Void> deleteWorkReport(@PathVariable Long workReportId) {
        workReportService.delete(workReportId);
        return ResponseEntity.noContent().build();
    }
}
