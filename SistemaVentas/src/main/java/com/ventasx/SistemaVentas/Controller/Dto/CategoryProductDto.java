package com.ventasx.SistemaVentas.Controller.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryProductDto {

    private Long id;

    @NotBlank(message = "{categoryProduct.name.notBlank}")
    @Length(message = "{categoryProduct.name.length}")
    private String name;

    @Length(message = "{categoryProduct.description.length}")
    private String description;

    private GroupProductDto groupProduct;

    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Long userCreatorId;
    private Long userUpdaterId;

    private Boolean isEnabled;
}
