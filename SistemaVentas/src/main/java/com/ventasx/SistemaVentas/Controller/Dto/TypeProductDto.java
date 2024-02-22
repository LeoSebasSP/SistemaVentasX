package com.ventasx.SistemaVentas.Controller.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TypeProductDto {

    private Long id;
    @NotBlank(message = "{typeProduct.name.notBlank}")
    @Length(message = "{typeProduct.name.length}")
    private String name;

    @Length(message = "{typeProduct.description.length}")
    private String description;

    private CategoryProductDto categoryProduct;

    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Long userCreatorId;
    private Long userUpdaterId;

    private Boolean isEnabled;
}
