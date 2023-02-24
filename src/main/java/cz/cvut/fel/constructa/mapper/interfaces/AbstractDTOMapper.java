package cz.cvut.fel.constructa.mapper.interfaces;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractDTOMapper<Entity, Dto> {

    public abstract Dto convertToDto(Entity entity);

    public abstract Entity convertToEntity(Dto dto);

    public List<Dto> convertToDtoList(List<Entity> entityList) {
        return entityList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<Entity> convertToEntityList(List<Dto> dtoList) {
        return dtoList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
    }
}
