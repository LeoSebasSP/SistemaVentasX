package com.ventasx.SistemaVentas.Persistence.Repository;

import com.ventasx.SistemaVentas.Persistence.Entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductRepository extends IGenericRepository<Product, Long>{

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.isEnabled = false WHERE p.id = :id")
    void disableProductById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query("UPDATE Product p SET p.isEnabled = true WHERE p.id = :id")
    void enableProductById(@Param("id") Long id);
}
