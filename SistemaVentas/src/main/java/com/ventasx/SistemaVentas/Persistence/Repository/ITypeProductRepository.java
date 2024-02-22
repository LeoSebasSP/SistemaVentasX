package com.ventasx.SistemaVentas.Persistence.Repository;

import com.ventasx.SistemaVentas.Persistence.Entity.CategoryProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.GroupProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.TypeProduct;

import java.util.List;

public interface ITypeProductRepository extends IGenericRepository<TypeProduct, Long>{

    List<TypeProduct> findAllByIsEnabledTrue();

    List<TypeProduct> findAllByCategoryProductAndIsEnabledTrue(CategoryProduct categoryProduct);
}
