package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.CategoryProduct;
import com.ventasx.SistemaVentas.Persistence.Entity.GroupProduct;
import com.ventasx.SistemaVentas.Persistence.Repository.ICategoryProductRepository;
import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Service.ICategoryProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryProductServiceImpl extends CrudServiceImpl<CategoryProduct, Long> implements ICategoryProductService {

    private final ICategoryProductRepository repository;

    @Override
    protected IGenericRepository<CategoryProduct, Long> getRepo() {
        return repository;
    }

    @Override
    public List<CategoryProduct> findAllByGroupProductAndIsEnabledTrue(GroupProduct groupProduct) {
        return repository.findAllByGroupProductAndIsEnabledTrue(groupProduct);
    }
}
