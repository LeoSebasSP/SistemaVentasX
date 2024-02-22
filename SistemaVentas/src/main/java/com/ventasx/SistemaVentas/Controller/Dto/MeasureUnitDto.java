package com.ventasx.SistemaVentas.Controller.Dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MeasureUnitDto {

    private Integer id;

    @NotBlank(message = "{measureUnit.name.notBlank}")
    @Length(message = "{measureUnit.name.length}")
    @Column(nullable = false, length = 100)
    private String name;

    @Length(message = "{measureUnit.description.length}")
    @Column(length = 200)
    private String description;

    @Column(nullable = false)
    private Boolean isEnabled;

    private Integer baseMeasureUnit;
    private Float conversionFactor;

    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Long userCreatorId;
    private Long userUpdaterId;

}
