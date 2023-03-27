package cz.cvut.fel.constructa.service;

import cz.cvut.fel.constructa.dto.request.ConstructionDiaryRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionDiaryDTO;
import cz.cvut.fel.constructa.mapper.ConstructionDiaryMapper;
import cz.cvut.fel.constructa.model.ConstructionDiary;
import cz.cvut.fel.constructa.model.report.ConstructionReport;
import cz.cvut.fel.constructa.repository.ConstructionDiaryRepository;
import cz.cvut.fel.constructa.service.interfaces.ConstructionDiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConstructionDiaryServiceImpl implements ConstructionDiaryService {
    private final ConstructionDiaryRepository constructionDiaryDao;
    private final ConstructionDiaryMapper constructionDiaryMapper;
    @Override
    public ConstructionDiary create(ConstructionDiaryRequest request) throws ParseException {
        ConstructionDiary constructiondiary = constructionDiaryMapper.convertToEntity(request);
        return constructionDiaryDao.save(constructiondiary);
    }

    @Override
    public ConstructionDiaryDTO getConstructionDiaryById(Long id) {
        Optional<ConstructionDiary> constructionDiary = constructionDiaryDao.findById(id);
        return constructionDiary.map(constructionDiaryMapper::convertToDto).orElse(null);
    }

    @Override
    public List<ConstructionDiaryDTO> getConstructionDiaries() {
        List<ConstructionDiary> constructionDiaries = constructionDiaryDao.findAll();
        return constructionDiaries.stream()
                .map(constructionDiaryMapper::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        constructionDiaryDao.deleteById(id);
    }

    @Override
    public ConstructionDiary update(ConstructionDiaryRequest constructionDiary) {
        return null;
    }
}
