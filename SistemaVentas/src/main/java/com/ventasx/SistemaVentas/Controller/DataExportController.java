package com.ventasx.SistemaVentas.Controller;

import com.ventasx.SistemaVentas.Controller.Dto.SuccessMessageDto;
import com.ventasx.SistemaVentas.Service.IDataExportService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;

@RestController
@RequestMapping("ventasx/v1/data-export")
@RequiredArgsConstructor
public class DataExportController {

    private final IDataExportService service;

    @GetMapping("/products")
    public ResponseEntity<SuccessMessageDto> exportProductsToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=Productos Activos.xlsx");

        try (OutputStream outputStream = response.getOutputStream()) {
            service.exportProductsToExcel(outputStream, true);
        }

        return new ResponseEntity<>(SuccessMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(LocalDateTime.now())
                .message("Registros exportados exitosamente.").build(), HttpStatus.OK);
    }

    @GetMapping("/products-disabled")
    public ResponseEntity<SuccessMessageDto> exportProductsDisabledToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=Productos Inactivos.xlsx");

        try (OutputStream outputStream = response.getOutputStream()) {
            service.exportProductsToExcel(outputStream, false);
        }

        return new ResponseEntity<>(SuccessMessageDto.builder().statusCode(HttpStatus.OK.value()).timestamp(LocalDateTime.now())
                .message("Registros exportados exitosamente.").build(), HttpStatus.OK);
    }
}
