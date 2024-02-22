package com.ventasx.SistemaVentas.Controller.Dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private Long id;

    @NotBlank(message = "{product.name.notBlank}")
    @Length(message = "{product.name.length}")
    private String name;

    @NotBlank(message = "{product.code.notBlank}")
    @Length(message = "{product.code.length}")
    private String code;

    @Length(message = "{product.codeAux.length}")
    private String codeAux;

    @Length(message = "{product.description.length}")
    private String description;

    @NotNull(message = "{product.groupProduct.notNull}")
    private GroupProductDto groupProduct;

    @NotNull(message = "{product.categoryProduct.notNull}")
    private CategoryProductDto categoryProduct;

    @NotNull(message = "{product.typeProduct.notNull}")
    private TypeProductDto typeProduct;

    @NotNull(message = "{product.brandProduct.notNull}")
    private BrandProductDto brandProduct;

    @NotNull(message = "{product.measureUnit.notNull}")
    private BrandProductDto measureUnit;

    private Long parentProduct;
    private Float minimumStock;
    private Float sellingPriceSoles;
    private Float previousSellingPriceSoles;
    private Float sellingPriceDollars;
    private Float previousSellingPriceDollars;
    private String sunatType;
    private Boolean isEnabled;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;
    private Long userCreatorId;
    private Long userUpdaterId;
}
