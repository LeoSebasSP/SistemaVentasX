package com.ventasx.SistemaVentas.Persistence.Repository;

import com.ventasx.SistemaVentas.Persistence.Entity.CategoryProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.GroupProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICategoryProductRepository extends IGenericRepository<CategoryProduct, Long>{

    List<CategoryProduct> findAllByGroupProductAndIsEnabledTrue(GroupProduct groupProduct);

    @Query(value = "select * from category_product where group_product_id = :id and (\n" +
            "lower(name) like lower(:name)\n" +
            "or translate(lower(name), 'áéíóú', 'aeiou') = translate(lower(:name), 'áéíóú', 'aeiou')) limit 1;", nativeQuery = true)
    Optional<CategoryProduct> findAllByGroupProductAndName(@Param("name") String name, @Param("id") Long id);

}
