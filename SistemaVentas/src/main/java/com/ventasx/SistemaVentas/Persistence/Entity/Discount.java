package com.ventasx.SistemaVentas.Persistence.Entity;

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

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name")})
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Float amount;

    @Enumerated(EnumType.STRING)
    private TypeDiscount typeDiscountId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_discount_branch"))
    private Branch branchId;

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
