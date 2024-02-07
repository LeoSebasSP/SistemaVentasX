package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.MeasureUnit;
import com.ventasx.SistemaVentas.Persistence.Repository.IMeasureUnitRepository;
import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Service.IMeasureUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeasureUnitServiceImpl extends CrudServiceImpl<MeasureUnit, Integer> implements IMeasureUnitService {

    private final IMeasureUnitRepository repository;

    @Override
    protected IGenericRepository<MeasureUnit, Integer> getRepo() {
        return repository;
    }
}
