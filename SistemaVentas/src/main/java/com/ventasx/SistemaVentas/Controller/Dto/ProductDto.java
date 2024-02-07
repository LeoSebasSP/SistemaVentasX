package com.ventasx.SistemaVentas.Controller.Dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    private String name;
    private String code;
    private String codeAux;
    private String description;
    private GroupProductDto groupProduct;
    private CategoryProductDto categoryProduct;
    private TypeProductDto typeProduct;
    private BrandProductDto brandProduct;
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
