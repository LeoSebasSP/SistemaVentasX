package com.ventasx.SistemaVentas.Controller.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandProductDto {

    private Long id;
    private String name;
    private String description;
    private Boolean isEnabled;
}
