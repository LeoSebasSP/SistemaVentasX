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
public class BrandProductDto {

    private Long id;

    @NotBlank(message = "{brandProduct.name.notBlank}")
    @Length(message = "{brandProduct.name.length}")
    @Column(nullable = false, length = 100)
    private String name;

    @Length(message = "{brandProduct.description.length}")
    @Column(length = 200)
    private String description;

    @Column(nullable = false)
    private Boolean isEnabled;

    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Long userCreatorId;
    private Long userUpdaterId;
}
