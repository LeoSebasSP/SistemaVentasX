package com.ventasx.SistemaVentas.Persistence.Repository;

import com.ventasx.SistemaVentas.Persistence.Entity.CategoryProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.GroupProduct;

import java.util.List;

public interface ICategoryProductRepository extends IGenericRepository<CategoryProduct, Long>{

    List<CategoryProduct> findAllByIsEnabledTrue();

    List<CategoryProduct> findAllByGroupProductAndIsEnabledTrue(GroupProduct groupProduct);
}
