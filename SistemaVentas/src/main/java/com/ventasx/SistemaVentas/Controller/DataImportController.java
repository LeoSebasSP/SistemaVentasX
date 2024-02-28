package com.ventasx.SistemaVentas.Controller;

import com.ventasx.SistemaVentas.Controller.Dto.SuccessMessageDto;
import com.ventasx.SistemaVentas.Service.IDataImportService;
import com.ventasx.SistemaVentas.Service.Impl.DataImportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("ventasx/v1/data-import")
@RequiredArgsConstructor
public class DataImportController {

    private final IDataImportService service;

    @PostMapping("/products")
    public ResponseEntity<SuccessMessageDto> createDataFromExcel(@RequestParam("file") MultipartFile file) throws IOException {
        service.createProductsFromExcel(file);
        return new ResponseEntity<>(SuccessMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(LocalDateTime.now())
                .message("Registros importados exitosamente.").build(), HttpStatus.OK);
    }
}
