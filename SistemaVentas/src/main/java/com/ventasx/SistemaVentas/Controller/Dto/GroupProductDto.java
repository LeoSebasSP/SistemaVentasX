package com.ventasx.SistemaVentas.Controller.Dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupProductDto {

    private Long id;

    @NotBlank(message = "{groupProduct.name.notBlank}")
    @Length(message = "{groupProduct.name.length}")
    private String name;

    @Length(message = "{groupProduct.description.length}")
    private String description;

    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Long userCreatorId;
    private Long userUpdaterId;

    private Boolean isEnabled;
}
