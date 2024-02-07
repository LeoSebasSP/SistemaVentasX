package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.Discount;
import com.ventasx.SistemaVentas.Persistence.Repository.IDiscountRepository;
import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Service.IDiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl extends CrudServiceImpl<Discount, Long> implements IDiscountService {

    private final IDiscountRepository repository;

    @Override
    protected IGenericRepository<Discount, Long> getRepo() {
        return repository;
    }
}
