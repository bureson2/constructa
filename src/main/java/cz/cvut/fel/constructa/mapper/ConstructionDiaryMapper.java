package cz.cvut.fel.constructa.mapper;

import cz.cvut.fel.constructa.dto.request.ConstructionDiaryRequest;
import cz.cvut.fel.constructa.dto.response.ConstructionDiaryDTO;
import cz.cvut.fel.constructa.model.ConstructionDiary;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class ConstructionDiaryMapper {
    private final ModelMapper modelMapper;
    public ConstructionDiaryDTO convertToDto(ConstructionDiary constructionDiary) {
        return modelMapper.map(constructionDiary, ConstructionDiaryDTO.class);
    }

    public ConstructionDiary convertToEntity(ConstructionDiaryRequest request) throws ParseException {
        return modelMapper.map(request, ConstructionDiary.class);
    }
}
