package com.ventasx.SistemaVentas.Persistence.Repository;

import com.ventasx.SistemaVentas.Persistence.Entity.BrandProduct;

import java.util.List;

public interface IBrandProductRepository extends IGenericRepository<BrandProduct, Long>{

    List<BrandProduct> findAllByIsEnabledTrue();
}
