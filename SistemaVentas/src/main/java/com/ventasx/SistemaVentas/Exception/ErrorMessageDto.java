package com.ventasx.SistemaVentas.Exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageDto {
    private Integer statusCode;
    private Date timestamp;
    private String message;
    private String description;
}
