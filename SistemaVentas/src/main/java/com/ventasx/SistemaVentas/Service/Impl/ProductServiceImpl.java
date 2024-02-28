package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.*;
import com.ventasx.SistemaVentas.Persistence.Repository.*;
import com.ventasx.SistemaVentas.Service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends CrudServiceImpl<Product, Long> implements IProductService {

    private final IProductRepository repository;

    @Override
    protected IGenericRepository<Product, Long> getRepo() {
        return repository;
    }

    @Override
    public void disableProductsById(Long id) {
        repository.disableProductById(id);
    }

    @Override
    public void enableProductById(Long id) {
        repository.enableProductById(id);
    }
}
