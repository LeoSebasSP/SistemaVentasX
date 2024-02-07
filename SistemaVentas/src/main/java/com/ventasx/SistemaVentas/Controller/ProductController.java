package com.ventasx.SistemaVentas.Controller;

import com.ventasx.SistemaVentas.Controller.Dto.ProductDto;
import com.ventasx.SistemaVentas.Controller.Mapper.MapperBetweenDtoAndEntity;
import com.ventasx.SistemaVentas.Persistence.Entity.Product;
import com.ventasx.SistemaVentas.Service.IProductService;
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
    public ResponseEntity<ProductDto> createData(@RequestBody ProductDto dto) throws Exception{
        ProductDto dataDto = mapFromEntityToDto(service.create(mapFromDtoRequestToEntity(dto)));
        return new ResponseEntity<>(dataDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> listAllData() throws Exception{
        List<ProductDto> listDataDto =  service.getAll().stream().map(this::mapFromEntityToDto).toList();
        return new ResponseEntity<>(listDataDto, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<ProductDto>> listAllDataPagination(@PageableDefault(sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
        Page<ProductDto> dataPagination = service.getAllPagination(pageable).map(this::mapFromEntityToDto);
        return new ResponseEntity<>(dataPagination, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> listDataById(@PathVariable("id") Long id) throws Exception{
        Product data = service.getById(id);
//        if (data == null) {
//            throw new ResourceNotFound("Paciente", "id", pacienteDto.getPaciente_id());
//        }
        return new ResponseEntity<>(mapFromEntityToDto(data), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<ProductDto> updateData(@RequestBody ProductDto dto) throws Exception {
        Product data = service.getById(dto.getId());
//        if (paciente == null) {
//            throw new ResourceNotFound("Paciente", "id", pacienteDto.getPaciente_id());
//        }
        return new ResponseEntity<>(mapFromEntityToDto(service.update(data)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable(value = "id") Long id) throws Exception {
        Product data = service.getById(id);
//        if (paciente == null) {
//            throw new ResourceNotFound("Paciente", "id", paciente_id);
//        }
        service.delete(id);
    }
}
