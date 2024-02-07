package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.Product;
import com.ventasx.SistemaVentas.Persistence.Repository.IProductRepository;
import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends CrudServiceImpl<Product, Long> implements IProductService {

    private final IProductRepository repository;

    @Override
    protected IGenericRepository<Product, Long> getRepo() {
        return repository;
    }
}
