package com.ventasx.SistemaVentas.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

import java.io.Serial;

@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceNotFound extends RuntimeException{
    @Serial
    private static final long serialVersionUID = 1L;

    private String resourceName;
    private String fieldName;
    private String fieldValue;
    private HttpStatus status;

    public ResourceNotFound(String resourceName, String fieldName, String fieldValue) {
        super(String.format("%s no encontrado con el %s: '%s'", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.status = HttpStatus.NOT_FOUND;
    }
}
