package com.ventasx.SistemaVentas.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDto {
    private Integer statusCode;
    private Date timestamp;
    private String description;
    private Map<String,String> errors;
}

