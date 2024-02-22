package com.ventasx.SistemaVentas.Controller.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class MapperBetweenDtoAndEntity<DTO, T> {

    @Autowired
    protected ModelMapper mapper;

    protected abstract Class<T> getTClass();
    protected abstract Class<DTO> getDTOClass();

    // DtoRequest a Entity
    protected T mapFromDtoRequestToEntity(DTO dto) {
        return mapper.map(dto, getTClass());
    }

    // Entity a DtoRequest
    protected DTO mapFromEntityToDto(T t) {
        return mapper.map(t, getDTOClass());
    }
}
