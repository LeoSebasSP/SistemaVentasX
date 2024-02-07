package com.ventasx.SistemaVentas.Controller.Dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasureUnitDto {

    private Integer Id;
    private String name;
    private String description;
    private Boolean isEnabled;
    private Integer baseMeasureUnit;
    private Float conversionFactor;
}
