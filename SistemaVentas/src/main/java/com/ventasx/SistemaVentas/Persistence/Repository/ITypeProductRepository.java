package com.ventasx.SistemaVentas.Persistence.Repository;

import com.ventasx.SistemaVentas.Persistence.Entity.CategoryProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.GroupProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.TypeProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ITypeProductRepository extends IGenericRepository<TypeProduct, Long>{

    List<TypeProduct> findAllByCategoryProductAndIsEnabledTrue(CategoryProduct categoryProduct);

    @Query(value = "select * from type_product where category_product_id = :id and (\n" +
            "lower(name) like lower(:name)\n" +
            "or translate(lower(name), 'áéíóú', 'aeiou') = translate(lower(:name), 'áéíóú', 'aeiou')) limit 1;", nativeQuery = true)
    Optional<TypeProduct> findAllByCategoryProductAndName(@Param("name") String name, @Param("id") Long id);
}
