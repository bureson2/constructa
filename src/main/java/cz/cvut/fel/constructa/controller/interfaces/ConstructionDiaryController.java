package cz.cvut.fel.constructa.controller.interfaces;

import cz.cvut.fel.constructa.dto.request.ConstructionDiaryRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionDiaryDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

public interface ConstructionDiaryController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ConstructionDiaryDTO>> getConstructionDiaries();
    @GetMapping(value = "/{constructionDiaryId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ConstructionDiaryDTO> getConstructionDiary(@PathVariable Long constructionDiaryId);
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ConstructionDiaryDTO> createConstructionDiary(@RequestBody ConstructionDiaryRequest request) throws ParseException;
    @DeleteMapping(value = "/{constructionDiaryId}")
    ResponseEntity<Void> deleteConstructionDiary(@PathVariable Long constructionDiaryId);
}
