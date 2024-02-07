package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.Branch;
import com.ventasx.SistemaVentas.Persistence.Repository.IBranchRepository;
import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Service.IBranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl extends CrudServiceImpl<Branch, Long> implements IBranchService {

    private final IBranchRepository repository;

    @Override
    protected IGenericRepository<Branch, Long> getRepo() {
        return repository;
    }
}
