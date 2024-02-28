package com.ventasx.SistemaVentas.Persistence.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "external_entity_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_address_externalEntity"))
    private ExternalEntity externalEntity;

    @NotNull
    @NotEmpty
    @Column(nullable = false)
    private String completeAddress;

    private String addressReference;

    @Column(nullable = false)
    private String city;

    private String province;

    private String district;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ubigeo_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_address_ubigeo"))
    private Ubigeo ubigeo;

    public Boolean isEnabled = true;

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
}
