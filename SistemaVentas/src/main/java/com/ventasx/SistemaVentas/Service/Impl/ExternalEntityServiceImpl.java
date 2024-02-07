package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.ExternalEntity;
import com.ventasx.SistemaVentas.Persistence.Repository.IExternalEntityRepository;
import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Service.IExternalEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalEntityServiceImpl extends CrudServiceImpl<ExternalEntity, Long> implements IExternalEntityService {

    private final IExternalEntityRepository repository;

    @Override
    protected IGenericRepository<ExternalEntity, Long> getRepo() {
        return repository;
    }
}
