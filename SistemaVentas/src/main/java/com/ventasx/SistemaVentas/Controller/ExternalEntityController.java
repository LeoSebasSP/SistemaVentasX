package com.ventasx.SistemaVentas.Controller;

import com.ventasx.SistemaVentas.Controller.Dto.ExternalEntityDto;
import com.ventasx.SistemaVentas.Controller.Mapper.MapperBetweenDtoAndEntity;
import com.ventasx.SistemaVentas.Exception.ResourceNotFound;
import com.ventasx.SistemaVentas.Persistence.Entity.ExternalEntity;
import com.ventasx.SistemaVentas.Service.IExternalEntityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ventasx/v1/customers")
@RequiredArgsConstructor
public class ExternalEntityController extends MapperBetweenDtoAndEntity<ExternalEntityDto, ExternalEntity> {

    private final IExternalEntityService service;

    @Override
    protected Class<ExternalEntity> getTClass() {
        return ExternalEntity.class;
    }
    @Override
    protected Class<ExternalEntityDto> getDTOClass() {
        return ExternalEntityDto.class;
    }

    @GetMapping
    public ResponseEntity<List<ExternalEntityDto>> listAll() throws Exception {
        List<ExternalEntityDto> list = service.getAll().stream().map(this::mapFromEntityToDto).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExternalEntityDto> createData(@RequestBody @Valid ExternalEntityDto dto) throws Exception {
        ExternalEntity data = service.create(mapFromDtoRequestToEntity(dto));
        return new ResponseEntity<>(mapFromEntityToDto(data), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ExternalEntityDto> update(@RequestBody @Valid ExternalEntityDto dto) throws Exception {
        if (service.getById(dto.getId()) == null){
            throw new ResourceNotFound("Tercero", "id", String.valueOf(dto.getId()));
        }
        return new ResponseEntity<>(mapFromEntityToDto(service.update(mapFromDtoRequestToEntity(dto))), HttpStatus.OK);
    }


}
