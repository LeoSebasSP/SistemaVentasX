package com.ventasx.SistemaVentas.Persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float cost;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_productCost_product"))
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_productCost_commercialReceipt"))
    private CommercialReceipt commercialReceipt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id", referencedColumnName = "id", nullable = false, foreignKey = @ForeignKey(name = "FK_productCost_externalEntity"))
    private ExternalEntity externalEntity;
}
