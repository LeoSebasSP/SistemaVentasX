package com.ventasx.SistemaVentas.Persistence.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name"), @UniqueConstraint(columnNames = "code") })
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{product.name.notBlank}")
    @Length(message = "{product.name.length}")
    @Column(nullable = false, length = 300)
    private String name;

    @NotBlank(message = "{product.code.notBlank}")
    @Length(message = "{product.code.length}")
    @Column(nullable = false, length = 50)
    private String code;

    @Length(message = "{product.codeAux.length}")
    @Column(length = 50)
    private String codeAux;

    @Length(message = "{product.description.length}")
    @Column(length = 500)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_product_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_product_groupProduct"))
    @NotNull(message = "{product.groupProduct.notNull}")
    private GroupProduct groupProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_product_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_product_categoryProduct"))
    @NotNull(message = "{product.categoryProduct.notNull}")
    private CategoryProduct categoryProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_product_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_product_typeProduct"))
    @NotNull(message = "{product.typeProduct.notNull}")
    private TypeProduct typeProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_product_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_product_brandProduct"))
    @NotNull(message = "{product.brandProduct.notNull}")
    private BrandProduct brandProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "measure_unit_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_product_measureUnit"))
    @NotNull(message = "{product.measureUnit.notNull}")
    private MeasureUnit measureUnit;

    private Long parentProductId;

    private Float minimumStock;

    private Float sellingPriceSoles;
    private Float previousSellingPriceSoles;

    private Float sellingPriceDollars;
    private Float previousSellingPriceDollars;

    private String sunatType;

    @CreatedDate
    @Column(
            nullable = false,
            updatable = false
    )
    private LocalDateTime creationDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updateDate;

    @CreatedBy
    @Column(
            nullable = false,
            updatable = false
    )
    private Long userCreatorId;

    @LastModifiedBy
    @Column(insertable = false)
    private Long userUpdaterId;

    @Column(nullable = false)
    private Boolean isEnabled;
}
