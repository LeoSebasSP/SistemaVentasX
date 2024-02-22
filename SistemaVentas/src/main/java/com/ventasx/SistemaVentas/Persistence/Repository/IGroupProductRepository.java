package com.ventasx.SistemaVentas.Persistence.Repository;

import com.ventasx.SistemaVentas.Persistence.Entity.GroupProduct;

import java.util.List;

public interface IGroupProductRepository extends IGenericRepository<GroupProduct, Long>{

    List<GroupProduct> findAllByIsEnabledTrue();
}
