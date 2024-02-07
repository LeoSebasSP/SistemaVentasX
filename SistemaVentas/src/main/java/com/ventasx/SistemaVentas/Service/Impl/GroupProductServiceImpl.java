package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.GroupProduct;
import com.ventasx.SistemaVentas.Persistence.Repository.IGroupProductRepository;
import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Service.IGroupProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupProductServiceImpl extends CrudServiceImpl<GroupProduct, Long> implements IGroupProductService {

    private final IGroupProductRepository repository;

    @Override
    protected IGenericRepository<GroupProduct, Long> getRepo() {
        return repository;
    }
}
