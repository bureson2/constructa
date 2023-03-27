package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.ConstructionReportController;
import cz.cvut.fel.constructa.dto.request.ConstructionDiaryRequest;
import cz.cvut.fel.constructa.dto.request.ConstructionReportRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionDiaryDTO;
import cz.cvut.fel.constructa.dto.response.ConstructionReportDTO;
import cz.cvut.fel.constructa.service.interfaces.ConstructionReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/construction-reports")
@RequiredArgsConstructor
public class ConstructionReportControllerImpl implements ConstructionReportController {
    private final ConstructionReportService constructionReportService;
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConstructionReportDTO>> getConstructionReports() {
        return ResponseEntity.ok().body(
                constructionReportService.getConstructionReports()
        );
    }
    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{constructionReportId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConstructionReportDTO> getConstructionReport(@PathVariable Long constructionReportId) {
        ConstructionReportDTO constructionReport = constructionReportService.getConstructionReporttById(constructionReportId);
        if(constructionReport != null){
            return ResponseEntity.ok().body(
                    constructionReport
            );
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConstructionReportDTO> createConstructionReport(@RequestBody ConstructionReportRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                constructionReportService.create(request)
        );
    }

    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{constructionReportId}")
    public ResponseEntity<Void> deleteConstructionReport(@PathVariable Long constructionReportId) {
        constructionReportService.delete(constructionReportId);
        return ResponseEntity.noContent().build();
    }

}
