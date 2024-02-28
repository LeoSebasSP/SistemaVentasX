package com.ventasx.SistemaVentas.Controller;

import com.ventasx.SistemaVentas.Controller.Dto.ProductDto;
import com.ventasx.SistemaVentas.Controller.Dto.SuccessMessageDto;
import com.ventasx.SistemaVentas.Controller.Mapper.MapperBetweenDtoAndEntity;
import com.ventasx.SistemaVentas.Exception.ResourceNotFound;
import com.ventasx.SistemaVentas.Persistence.Entity.Product;
import com.ventasx.SistemaVentas.Service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("ventasx/v1/products")
@RequiredArgsConstructor
public class ProductController extends MapperBetweenDtoAndEntity<ProductDto, Product> {

    private final IProductService service;

    @Override
    protected Class<Product> getTClass() {
        return Product.class;
    }

    @Override
    protected Class<ProductDto> getDTOClass() {
        return ProductDto.class;
    }

    @PostMapping
    public ResponseEntity<ProductDto> createData(@RequestBody @Valid ProductDto dto) throws Exception{
        dto.setName(dto.getName().trim());
        dto.setCode(dto.getCode().trim());
        if (dto.getCodeAux() != null){
            dto.setCodeAux(dto.getCodeAux().trim());
        }
        if (dto.getDescription() != null){
            dto.setDescription(dto.getDescription().trim());
        }
        ProductDto dataDto = mapFromEntityToDto(service.create(mapFromDtoRequestToEntity(dto)));
        return new ResponseEntity<>(dataDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> listAllData() {
        List<ProductDto> listDataDto =  service.findAllByIsEnabledTrueOrderByCreationDateDesc().stream().map(this::mapFromEntityToDto).toList();
        return new ResponseEntity<>(listDataDto, HttpStatus.OK);
    }

    @GetMapping("/disabled")
    public ResponseEntity<List<ProductDto>> listAllDataDisabled() {
        List<ProductDto> listDataDto =  service.findAllByIsEnabledFalseOrderByCreationDateDesc().stream().map(this::mapFromEntityToDto).toList();
        return new ResponseEntity<>(listDataDto, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<ProductDto>> listAllDataEnabledPagination(@PageableDefault(sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProductDto> dataPagination = service.findAllByIsEnabledTrueOrderByCreationDateDescPagination(pageable).map(this::mapFromEntityToDto);
        return new ResponseEntity<>(dataPagination, HttpStatus.OK);
    }

    @GetMapping("/pagination-disabled")
    public ResponseEntity<Page<ProductDto>> listAllDataNotEnabledPagination(@PageableDefault(sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ProductDto> dataPagination = service.findAllByIsEnabledFalseOrderByCreationDateDescPagination(pageable).map(this::mapFromEntityToDto);
        return new ResponseEntity<>(dataPagination, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> listDataById(@PathVariable("id") Long id) throws Exception{
        Product data = service.getById(id);
        if (data == null){
            throw new ResourceNotFound("Producto", "id", String.valueOf(id));
        }
        return new ResponseEntity<>(mapFromEntityToDto(data), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ProductDto> updateData(@RequestBody @Valid ProductDto dto) throws Exception {
        if (service.getById(dto.getId()) == null){
            throw new ResourceNotFound("Producto", "id", String.valueOf(dto.getId()));
        }
        return new ResponseEntity<>(mapFromEntityToDto(service.update(mapFromDtoRequestToEntity(dto))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessMessageDto> deleteData(@PathVariable(value = "id") Long id) throws Exception {
        if (service.getById(id) == null){
            throw new ResourceNotFound("Producto", "id", String.valueOf(id));
        }
        service.delete(id);
        return new ResponseEntity<>(SuccessMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(LocalDateTime.now())
                .message("Registro eliminado exitosamente.").build(), HttpStatus.OK);
    }

    @PutMapping("/disable")
    public ResponseEntity<SuccessMessageDto> disableData(@RequestBody List<Long> listId) throws Exception {
        for (Long id: listId){
            if (service.getById(id) == null){
                throw new ResourceNotFound("Producto", "id", String.valueOf(id));
            }
            service.disableProductsById(id);
        }
        return new ResponseEntity<>(SuccessMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(LocalDateTime.now())
                .message("Registros deshabilitados exitosamente.").build(), HttpStatus.OK);
    }

    @PutMapping("/enable")
    public ResponseEntity<SuccessMessageDto> enableData(@RequestBody List<Long> listId) throws Exception {
        for (Long id: listId){
            if (service.getById(id) == null){
                throw new ResourceNotFound("Producto", "id", String.valueOf(id));
            }
            service.enableProductById(id);
        }
        return new ResponseEntity<>(SuccessMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(LocalDateTime.now())
                .message("Registros habilitados exitosamente.").build(), HttpStatus.OK);
    }
}
