package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.ConstructionReportRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionReportDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

public interface ConstructionReportController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ConstructionReportDTO>> getConstructionReports();
    @GetMapping(value = "/{constructionReportId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ConstructionReportDTO> getConstructionReport(@PathVariable Long constructionReportId);
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ConstructionReportDTO> createConstructionReport(@RequestBody ConstructionReportRequest request) throws ParseException;
    @DeleteMapping(value = "/{constructionReportId}")
    ResponseEntity<Void> deleteConstructionReport(@PathVariable Long constructionReportId);

    }
