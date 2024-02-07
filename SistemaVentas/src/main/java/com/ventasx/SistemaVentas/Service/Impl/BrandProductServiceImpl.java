package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.BrandProduct;
import com.ventasx.SistemaVentas.Persistence.Repository.IBrandProductRepository;
import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Service.IBrandProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandProductServiceImpl extends CrudServiceImpl<BrandProduct, Long> implements IBrandProductService {

    private final IBrandProductRepository repository;

    @Override
    protected IGenericRepository<BrandProduct, Long> getRepo() {
        return repository;
    }
}
