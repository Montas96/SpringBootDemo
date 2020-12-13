package com.umbrella.demoSpringBoot.Service.mapper;

import java.util.List;

public interface GenericMapper<E,D> {
    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}
