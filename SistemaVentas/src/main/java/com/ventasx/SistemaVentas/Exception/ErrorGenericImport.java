package com.ventasx.SistemaVentas.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class ErrorGenericImport extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    private String resourceName;
    private String errorGeneric;
    private int rowExcelError;

    public ErrorGenericImport(String resourceName, String errorGeneric, int rowExcelError) {
        this.resourceName = resourceName;
        this.errorGeneric = errorGeneric;
        this.rowExcelError = rowExcelError;
    }
}
