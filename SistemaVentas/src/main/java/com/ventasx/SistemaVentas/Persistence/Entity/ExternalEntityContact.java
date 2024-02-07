package com.ventasx.SistemaVentas.Persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name") })
public class ExternalEntityContact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String nameReference;

    private String email;

    private String phone;

    private String position;

    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "external_entity_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_supplierContact_externalEntity"))
    private ExternalEntity externalEntity;
}
