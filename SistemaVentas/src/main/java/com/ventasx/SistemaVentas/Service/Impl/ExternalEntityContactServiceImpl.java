package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.ExternalEntityContact;
import com.ventasx.SistemaVentas.Persistence.Repository.IExternalEntityContactRepository;
import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Service.IExternalEntityContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExternalEntityContactServiceImpl extends CrudServiceImpl<ExternalEntityContact, Long> implements IExternalEntityContactService {

    private final IExternalEntityContactRepository repository;

    @Override
    protected IGenericRepository<ExternalEntityContact, Long> getRepo() {
        return repository;
    }
}
