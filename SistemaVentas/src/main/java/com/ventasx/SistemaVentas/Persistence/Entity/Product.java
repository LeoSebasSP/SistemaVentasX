package com.ventasx.SistemaVentas.Persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    private String codeAux;

    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_product_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_product_groupProduct"))
    private GroupProduct groupProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_product_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_product_categoryProduct"))
    private CategoryProduct categoryProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_product_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_product_typeProduct"))
    private TypeProduct typeProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_product_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_product_brandProduct"))
    private BrandProduct brandProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "measure_unit_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_product_measureUnit"))
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
