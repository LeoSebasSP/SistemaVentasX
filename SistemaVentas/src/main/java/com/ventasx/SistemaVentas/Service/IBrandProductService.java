package com.ventasx.SistemaVentas.Service;

import com.ventasx.SistemaVentas.Persistence.Entity.BrandProduct;

import java.util.List;

public interface IBrandProductService extends ICrudService<BrandProduct, Long>{
    List<BrandProduct> findAllByIsEnabledTrue();
}
