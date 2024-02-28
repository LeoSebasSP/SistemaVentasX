package com.ventasx.SistemaVentas.Controller;

import com.ventasx.SistemaVentas.Controller.Dto.BrandProductDto;
import com.ventasx.SistemaVentas.Controller.Dto.SuccessMessageDto;
import com.ventasx.SistemaVentas.Controller.Mapper.MapperBetweenDtoAndEntity;
import com.ventasx.SistemaVentas.Exception.ResourceNotFound;
import com.ventasx.SistemaVentas.Persistence.Entity.BrandProduct;
import com.ventasx.SistemaVentas.Service.IBrandProductService;
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
@RequestMapping("ventasx/v1/brands-product")
@RequiredArgsConstructor
public class BrandProductController extends MapperBetweenDtoAndEntity<BrandProductDto, BrandProduct> {

    private final IBrandProductService service;

    @Override
    protected Class<BrandProduct> getTClass() {
        return BrandProduct.class;
    }

    @Override
    protected Class<BrandProductDto> getDTOClass() {
        return BrandProductDto.class;
    }

    @PostMapping
    public ResponseEntity<BrandProductDto> createData(@RequestBody BrandProductDto dto) throws Exception{
        BrandProductDto dataDto = mapFromEntityToDto(service.create(mapFromDtoRequestToEntity(dto)));
        return new ResponseEntity<>(dataDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BrandProductDto>> listAllData() throws Exception{
        List<BrandProductDto> listDataDto =  service.findAllByIsEnabledTrueOrderByCreationDateDesc().stream().map(this::mapFromEntityToDto).toList();
        return new ResponseEntity<>(listDataDto, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<BrandProductDto>> listAllDataPagination(@PageableDefault(sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
        Page<BrandProductDto> dataPagination = service.getAllPagination(pageable).map(this::mapFromEntityToDto);
        return new ResponseEntity<>(dataPagination, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandProductDto> listDataById(@PathVariable("id") Long id) throws Exception{
        BrandProduct data = service.getById(id);
        if (data == null) {
            throw new ResourceNotFound("Marca Producto", "id", String.valueOf(id));
        }
        return new ResponseEntity<>(mapFromEntityToDto(data), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<BrandProductDto> updateData(@RequestBody BrandProductDto dto) throws Exception {
        if (service.getById(dto.getId()) == null) {
            throw new ResourceNotFound("Marca Producto", "id", String.valueOf(dto.getId()));
        }
        return new ResponseEntity<>(mapFromEntityToDto(service.update(mapFromDtoRequestToEntity(dto))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessMessageDto> deleteData(@PathVariable(value = "id") Long id) throws Exception {
        if (service.getById(id) == null) {
            throw new ResourceNotFound("Marca", "id", String.valueOf(id));
        }
        service.delete(id);
        return new ResponseEntity<>(SuccessMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(LocalDateTime.now())
                .message("Registro eliminado exitosamente.").build(), HttpStatus.OK);
    }
}
