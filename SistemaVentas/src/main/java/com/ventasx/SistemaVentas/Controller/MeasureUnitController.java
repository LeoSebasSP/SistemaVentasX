package com.ventasx.SistemaVentas.Controller;

import com.ventasx.SistemaVentas.Controller.Dto.MeasureUnitDto;
import com.ventasx.SistemaVentas.Controller.Mapper.MapperBetweenDtoAndEntity;
import com.ventasx.SistemaVentas.Persistence.Entity.MeasureUnit;
import com.ventasx.SistemaVentas.Service.IMeasureUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("ventasx/v1/measure-units")
@RequiredArgsConstructor
public class MeasureUnitController extends MapperBetweenDtoAndEntity<MeasureUnitDto, MeasureUnit> {

    private final IMeasureUnitService service;

    @Override
    protected Class<MeasureUnit> getTClass() {
        return MeasureUnit.class;
    }

    @Override
    protected Class<MeasureUnitDto> getDTOClass() {
        return MeasureUnitDto.class;
    }

    @PostMapping
    public ResponseEntity<MeasureUnitDto> createData(@RequestBody MeasureUnitDto dto) throws Exception{
        MeasureUnitDto dataDto = mapFromEntityToDto(service.create(mapFromDtoRequestToEntity(dto)));
        return new ResponseEntity<>(dataDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MeasureUnitDto>> listAllData() throws Exception{
        List<MeasureUnitDto> listDataDto =  service.getAll().stream().map(this::mapFromEntityToDto).toList();
        return new ResponseEntity<>(listDataDto, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<MeasureUnitDto>> listAllDataPagination(@PageableDefault(sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
        Page<MeasureUnitDto> dataPagination = service.getAllPagination(pageable).map(this::mapFromEntityToDto);
        return new ResponseEntity<>(dataPagination, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasureUnitDto> listDataById(@PathVariable("id") Integer id) throws Exception{
        MeasureUnit data = service.getById(id);
//        if (data == null) {
//            throw new ResourceNotFound("Paciente", "id", pacienteDto.getPaciente_id());
//        }
        return new ResponseEntity<>(mapFromEntityToDto(data), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<MeasureUnitDto> updateData(@RequestBody MeasureUnitDto dto) throws Exception {
        MeasureUnit data = service.getById(dto.getId());
//        if (paciente == null) {
//            throw new ResourceNotFound("Paciente", "id", pacienteDto.getPaciente_id());
//        }
        return new ResponseEntity<>(mapFromEntityToDto(service.update(data)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable(value = "id") Integer id) throws Exception {
        MeasureUnit data = service.getById(id);
//        if (paciente == null) {
//            throw new ResourceNotFound("Paciente", "id", paciente_id);
//        }
        service.delete(id);
    }
}
