package com.ventasx.SistemaVentas.Persistence.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "codeUbigeo") })
public class Ubigeo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String codeUbigeo;

    @Column(nullable = false)
    private String department;

    @Column(nullable = false)
    private String province;

    @Column(nullable = false)
    private String district;
}
