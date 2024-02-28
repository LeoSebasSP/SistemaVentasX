package com.ventasx.SistemaVentas.Service;

import com.ventasx.SistemaVentas.Persistence.Entity.CategoryProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.GroupProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.TypeProduct;

import java.util.List;

public interface ITypeProductService extends ICrudService<TypeProduct, Long> {

    List<TypeProduct> findAllByCategoryProductAndIsEnabledTrue(CategoryProduct categoryProduct);
}
