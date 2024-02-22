package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.CategoryProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.TypeProduct;
import com.ventasx.SistemaVentas.Persistence.Repository.ITypeProductRepository;
import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Service.ITypeProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeProductServiceImpl extends CrudServiceImpl<TypeProduct, Long> implements ITypeProductService {

    private final ITypeProductRepository repository;

    @Override
    protected IGenericRepository<TypeProduct, Long> getRepo() {
        return repository;
    }

    @Override
    public List<TypeProduct> findAllByIsEnabledTrue() {
        return repository.findAllByIsEnabledTrue();
    }

    @Override
    public List<TypeProduct> findAllByCategoryProductAndIsEnabledTrue(CategoryProduct categoryProduct) {
        return repository.findAllByCategoryProductAndIsEnabledTrue(categoryProduct);
    }
}
