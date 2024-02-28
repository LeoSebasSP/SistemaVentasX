package com.ventasx.SistemaVentas.Service;

import com.ventasx.SistemaVentas.Persistence.Entity.CategoryProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.GroupProduct;

import java.util.List;

public interface ICategoryProductService extends ICrudService<CategoryProduct, Long>{

    List<CategoryProduct> findAllByGroupProductAndIsEnabledTrue(GroupProduct groupProduct);
}
