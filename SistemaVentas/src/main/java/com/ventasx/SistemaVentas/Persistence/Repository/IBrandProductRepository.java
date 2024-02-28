package com.ventasx.SistemaVentas.Persistence.Repository;

import com.ventasx.SistemaVentas.Persistence.Entity.BrandProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.GroupProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IBrandProductRepository extends IGenericRepository<BrandProduct, Long>{

    @Query(value = "select * from brand_product where lower(name) like lower(:name)\n" +
            "or translate(lower(name), 'áéíóú', 'aeiou') = translate(lower(:name), 'áéíóú', 'aeiou') limit 1;", nativeQuery = true)
    Optional<BrandProduct> findAllByName(@Param("name") String name);

}
