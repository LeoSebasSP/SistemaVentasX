package com.ventasx.SistemaVentas.Persistence.Repository;

import com.ventasx.SistemaVentas.Persistence.Entity.MeasureUnit;

import java.util.List;

public interface IMeasureUnitRepository extends IGenericRepository<MeasureUnit, Integer>{

    List<MeasureUnit> findAllByIsEnabledTrue();
}
