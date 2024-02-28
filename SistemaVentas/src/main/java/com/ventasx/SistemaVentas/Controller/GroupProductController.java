package com.ventasx.SistemaVentas.Controller;

import com.ventasx.SistemaVentas.Controller.Dto.GroupProductDto;
import com.ventasx.SistemaVentas.Controller.Dto.SuccessMessageDto;
import com.ventasx.SistemaVentas.Controller.Mapper.MapperBetweenDtoAndEntity;
import com.ventasx.SistemaVentas.Exception.ResourceNotFound;
import com.ventasx.SistemaVentas.Persistence.Entity.CategoryProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.GroupProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.TypeProduct;
import com.ventasx.SistemaVentas.Service.ICategoryProductService;
import com.ventasx.SistemaVentas.Service.IGroupProductService;
import com.ventasx.SistemaVentas.Service.ITypeProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("ventasx/v1/groups-product")
@RequiredArgsConstructor
public class GroupProductController extends MapperBetweenDtoAndEntity<GroupProductDto, GroupProduct> {

    private final IGroupProductService service;
    private final ICategoryProductService categoryProductService;
    private final ITypeProductService typeProductService;

    @Override
    protected Class<GroupProduct> getTClass() {
        return GroupProduct.class;
    }

    @Override
    protected Class<GroupProductDto> getDTOClass() {
        return GroupProductDto.class;
    }

    @PostMapping
    public ResponseEntity<GroupProductDto> createData(@RequestBody GroupProductDto dto) throws Exception{
        GroupProductDto dataDto = mapFromEntityToDto(service.create(mapFromDtoRequestToEntity(dto)));
        return new ResponseEntity<>(dataDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GroupProductDto>> listAllData() throws Exception{
        List<GroupProductDto> listDataDto =  service.findAllByIsEnabledTrueOrderByCreationDateDesc().stream().map(this::mapFromEntityToDto).toList();
        return new ResponseEntity<>(listDataDto, HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<GroupProductDto>> listAllDataPagination(@PageableDefault(sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) throws Exception {
        Page<GroupProductDto> dataPagination = service.getAllPagination(pageable).map(this::mapFromEntityToDto);
        return new ResponseEntity<>(dataPagination, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupProductDto> listDataById(@PathVariable("id") Long id) throws Exception{
        GroupProduct data = service.getById(id);
        if (data == null) {
            throw new ResourceNotFound("Grupo Producto", "id", String.valueOf(id));
        }
        return new ResponseEntity<>(mapFromEntityToDto(data), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<GroupProductDto> updateData(@RequestBody GroupProductDto dto) throws Exception {
        if (service.getById(dto.getId()) == null) {
            throw new ResourceNotFound("Grupo Producto", "id", String.valueOf(dto.getId()));
        }
        return new ResponseEntity<>(mapFromEntityToDto(service.update(mapFromDtoRequestToEntity(dto))), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessMessageDto> deleteData(@PathVariable(value = "id") Long id) throws Exception {
        if (service.getById(id) == null) {
            throw new ResourceNotFound("Grupo Producto", "id", String.valueOf(id));
        }
        service.delete(id);
        return new ResponseEntity<>(SuccessMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(LocalDateTime.now())
                .message("Registro eliminado exitosamente.").build(), HttpStatus.OK);
    }
}
