package com.ventasx.SistemaVentas.Controller;

import com.ventasx.SistemaVentas.Controller.Dto.CategoryProductDto;
import com.ventasx.SistemaVentas.Controller.Dto.SuccessMessageDto;
import com.ventasx.SistemaVentas.Controller.Dto.TypeProductDto;
import com.ventasx.SistemaVentas.Controller.Mapper.MapperBetweenDtoAndEntity;
import com.ventasx.SistemaVentas.Exception.ResourceNotFound;
import com.ventasx.SistemaVentas.Persistence.Entity.CategoryProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.TypeProduct;
import com.ventasx.SistemaVentas.Service.ITypeProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("ventasx/v1/types-product")
@RequiredArgsConstructor
public class TypeProductController extends MapperBetweenDtoAndEntity<TypeProductDto, TypeProduct> {

    private final ITypeProductService service;

    @Override
    protected Class<TypeProduct> getTClass() {
        return TypeProduct.class;
    }

    @Override
    protected Class<TypeProductDto> getDTOClass() {
        return TypeProductDto.class;
    }

    @PostMapping
    public ResponseEntity<TypeProductDto> createData(@RequestBody TypeProductDto dto) throws Exception{
        TypeProductDto dataDto = mapFromEntityToDto(service.create(mapFromDtoRequestToEntity(dto)));
        return new ResponseEntity<>(dataDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TypeProductDto>> listAllData() throws Exception{
        List<TypeProductDto> listDataDto =  service.findAllByIsEnabledTrueOrderByCreationDateDesc().stream().map(this::mapFromEntityToDto).toList();
        return new ResponseEntity<>(listDataDto, HttpStatus.OK);
    }

    @PostMapping("/by-group-product")
    public ResponseEntity<List<TypeProductDto>> listAllDataByGroupProduct(@RequestBody @Valid CategoryProductDto dto) throws Exception{
        List<TypeProductDto> listDataDto =  service.findAllByCategoryProductAndIsEnabledTrue(mapper.map(dto, CategoryProduct.class)).stream().map(this::mapFromEntityToDto).toList();
        return new ResponseEntity<>(listDataDto, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<TypeProductDto>> listAllDataPagination(@PageableDefault(sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
        Page<TypeProductDto> dataPagination = service.getAllPagination(pageable).map(this::mapFromEntityToDto);
        return new ResponseEntity<>(dataPagination, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeProductDto> listDataById(@PathVariable("id") Long id) throws Exception{
        TypeProduct data = service.getById(id);
        if (data == null) {
            throw new ResourceNotFound("Tipo Producto", "id", String.valueOf(id));
        }
        return new ResponseEntity<>(mapFromEntityToDto(data), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<TypeProductDto> updateData(@RequestBody TypeProductDto dto) throws Exception {
        if (service.getById(dto.getId()) == null) {
            throw new ResourceNotFound("Tipo Producto", "id", String.valueOf(dto.getId()));
        }
        return new ResponseEntity<>(mapFromEntityToDto(service.update(mapFromDtoRequestToEntity(dto))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessMessageDto> deleteData(@PathVariable(value = "id") Long id) throws Exception {
        if (service.getById(id) == null) {
            throw new ResourceNotFound("Tipo Producto", "id", String.valueOf(id));
        }
        service.delete(id);
        return new ResponseEntity<>(SuccessMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(LocalDateTime.now())
                .message("Registro eliminado exitosamente.").build(), HttpStatus.OK);
    }
}
