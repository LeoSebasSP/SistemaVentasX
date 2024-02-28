package com.ventasx.SistemaVentas.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class DataErrorNullImport extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    private String resourceName;
    private String fieldName;
    private int rowExcelError;
    private HttpStatus status;

    public DataErrorNullImport(String resourceName, String fieldName, int rowExcelError) {
        super(String.format("%s del %s no debe ser VACÍO - Fila Error: %s", fieldName, resourceName, rowExcelError));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.rowExcelError = rowExcelError;
        this.status = HttpStatus.BAD_REQUEST;
    }
}
