package cz.cvut.fel.constructa.controller;

import cz.cvut.fel.constructa.controller.interfaces.ConstructionDiaryController;
import cz.cvut.fel.constructa.dto.request.ConstructionDiaryRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionDiaryDTO;
import cz.cvut.fel.constructa.service.interfaces.ConstructionDiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/construction-diaries")
@RequiredArgsConstructor
public class ConstructionDiaryControllerImpl implements ConstructionDiaryController {
    private final ConstructionDiaryService constructionDiaryService;

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ConstructionDiaryDTO>> getConstructionDiaries() {
        return ResponseEntity.ok().body(
                constructionDiaryService.getConstructionDiaries()
        );
    }

    @Override
    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @GetMapping(value = "/{constructionDiaryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConstructionDiaryDTO> getConstructionDiary(@PathVariable Long constructionDiaryId) {
        ConstructionDiaryDTO constructionDiary = constructionDiaryService.getConstructionDiaryById(constructionDiaryId);
        if(constructionDiary != null){
            return ResponseEntity.ok().body(
                    constructionDiary
            );
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    @ResponseBody
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ConstructionDiaryDTO> createConstructionDiary(@RequestBody ConstructionDiaryRequest request) throws ParseException {
        return ResponseEntity.ok().body(
                constructionDiaryService.create(request)
        );
    }

    @Override
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{constructionDiaryId}")
    public ResponseEntity<Void> deleteConstructionDiary(@PathVariable Long constructionDiaryId) {
        constructionDiaryService.delete(constructionDiaryId);
        return ResponseEntity.noContent().build();
    }
}
