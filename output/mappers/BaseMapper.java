package com.sqldomaingen.mappers;

import org.modelmapper.ModelMapper;
import java.util.List;
import java.util.stream.Collectors;

public interface BaseMapper<E, D> {

    ModelMapper modelMapper = new ModelMapper();

    default D toDTO(E entity) {
        return modelMapper.map(entity, getDtoClass());
    }

    default E toEntity(D dto) {
        return modelMapper.map(dto, getEntityClass());
    }

    default List<D> toDTOList(List<E> entities) {
        return entities.stream().map(this::toDTO).collect(Collectors.toList());
    }

    default List<E> toEntityList(List<D> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }

    Class<D> getDtoClass();

    Class<E> getEntityClass();
}
