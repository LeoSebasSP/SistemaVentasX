package com.ventasx.SistemaVentas.Persistence.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name"), @UniqueConstraint(columnNames = "documentNumber")})
public class ExternalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{customer.name.notNull}")
    @NotEmpty(message = "{customer.name.notEmpty}")
    @Size(message = "{customer.name.size}", max = 100)
    @Column(nullable = false, length = 100)
    private String name;

    @NotNull(message = "{customer.documentNumber.notNull}")
    @NotEmpty(message = "{customer.documentNumber.notEmpty}")
    @Size(message = "{customer.documentNumber.size}", max = 20)
    @Column(nullable = false, length = 20)
    private String documentNumber;

    @Size(message = "{customer.phone.size}", max = 20)
    @Column(length = 20)
    private String phone;

    @Email(message = "{customer.email.email}")
    @Size(message = "{customer.email.size}", max = 50)
    @Column(length = 50)
    private String email;

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

    @NotNull(message = "{customer.isSupplier.notNull}")
    @Column(nullable = false)
    private Boolean isSupplier;

    @NotNull(message = "{customer.isCustomer.notNull}")
    @Column(nullable = false)
    private Boolean isCustomer;

    @NotNull(message = "{customer.isEnabled.notNull}")
    @Column(nullable = false)
    private Boolean isEnabled;

    @NotNull(message = "{customer.typeContributor.notNull}")
    @Enumerated(EnumType.STRING)
    private TypeContributor typeContributor;

    @NotNull(message = "{customer.typeDocument.notNull}")
    @Enumerated(EnumType.STRING)
    private TypeDocument typeDocument;
}
