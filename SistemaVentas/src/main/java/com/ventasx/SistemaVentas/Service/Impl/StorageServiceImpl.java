package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.Storage;
import com.ventasx.SistemaVentas.Persistence.Repository.IStorageRepository;
import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Service.IStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl extends CrudServiceImpl<Storage, Long> implements IStorageService {

    private final IStorageRepository repository;

    @Override
    protected IGenericRepository<Storage, Long> getRepo() {
        return repository;
    }
}
