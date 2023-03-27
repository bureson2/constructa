package cz.cvut.fel.constructa.service.interfaces;

import cz.cvut.fel.constructa.dto.request.ConstructionDiaryRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionDiaryDTO;
import cz.cvut.fel.constructa.model.ConstructionDiary;

import java.text.ParseException;
import java.util.List;

public interface ConstructionDiaryService {
    ConstructionDiary create(ConstructionDiaryRequest request) throws ParseException;
    ConstructionDiaryDTO getConstructionDiaryById(Long id);
    List<ConstructionDiaryDTO> getConstructionDiaries();
    void delete(Long id);
    ConstructionDiary update(ConstructionDiaryRequest constructionDiary);
}
