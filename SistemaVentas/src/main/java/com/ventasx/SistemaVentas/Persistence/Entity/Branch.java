package com.ventasx.SistemaVentas.Persistence.Entity;

import jakarta.persistence.*;
import lombok.*;
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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name"), @UniqueConstraint(columnNames = "codeAnnex")})
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private String address;

    private String phone;

    /*

    private String boletaFormat;

    private String facturaFormat;

    private String proformaFormat;

    private String quotationFormat;

    private String salesOrderFormat;

    private String purchaseOrderFormat;

    private String creditNoteFormat;

    private String debitNoteFormat;

    private String shippingGuideFormat;

     */

    private String codeAnnex;

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
