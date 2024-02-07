package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.TypeProduct;
import com.ventasx.SistemaVentas.Persistence.Repository.ITypeProductRepository;
import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Service.ITypeProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeProductServiceImpl extends CrudServiceImpl<TypeProduct, Long> implements ITypeProductService {

    private final ITypeProductRepository repository;

    @Override
    protected IGenericRepository<TypeProduct, Long> getRepo() {
        return repository;
    }
}
