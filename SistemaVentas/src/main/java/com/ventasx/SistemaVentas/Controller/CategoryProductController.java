package com.ventasx.SistemaVentas.Controller;

import com.ventasx.SistemaVentas.Controller.Dto.CategoryProductDto;
import com.ventasx.SistemaVentas.Controller.Mapper.MapperBetweenDtoAndEntity;
import com.ventasx.SistemaVentas.Persistence.Entity.CategoryProduct;
import com.ventasx.SistemaVentas.Service.ICategoryProductService;
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
@RequestMapping("ventasx/v1/categories-product")
@RequiredArgsConstructor
public class CategoryProductController extends MapperBetweenDtoAndEntity<CategoryProductDto, CategoryProduct> {

    private final ICategoryProductService service;

    @Override
    protected Class<CategoryProduct> getTClass() {
        return CategoryProduct.class;
    }

    @Override
    protected Class<CategoryProductDto> getDTOClass() {
        return CategoryProductDto.class;
    }

    @PostMapping
    public ResponseEntity<CategoryProductDto> createData(@RequestBody CategoryProductDto dto) throws Exception{
        CategoryProductDto dataDto = mapFromEntityToDto(service.create(mapFromDtoRequestToEntity(dto)));
        return new ResponseEntity<>(dataDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryProductDto>> listAllData() throws Exception{
        List<CategoryProductDto> listDataDto =  service.getAll().stream().map(this::mapFromEntityToDto).toList();
        return new ResponseEntity<>(listDataDto, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<CategoryProductDto>> listAllDataPagination(@PageableDefault(sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
        Page<CategoryProductDto> dataPagination = service.getAllPagination(pageable).map(this::mapFromEntityToDto);
        return new ResponseEntity<>(dataPagination, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryProductDto> listDataById(@PathVariable("id") Long id) throws Exception{
        CategoryProduct data = service.getById(id);
//        if (data == null) {
//            throw new ResourceNotFound("Paciente", "id", pacienteDto.getPaciente_id());
//        }
        return new ResponseEntity<>(mapFromEntityToDto(data), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<CategoryProductDto> updateData(@RequestBody CategoryProductDto dto) throws Exception {
        CategoryProduct data = service.getById(dto.getId());
//        if (paciente == null) {
//            throw new ResourceNotFound("Paciente", "id", pacienteDto.getPaciente_id());
//        }
        return new ResponseEntity<>(mapFromEntityToDto(service.update(data)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable(value = "id") Long id) throws Exception {
        CategoryProduct data = service.getById(id);
//        if (paciente == null) {
//            throw new ResourceNotFound("Paciente", "id", paciente_id);
//        }
        service.delete(id);
    }
}
