package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.Address;
import com.ventasx.SistemaVentas.Persistence.Repository.IAddressRepository;
import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Service.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl extends CrudServiceImpl<Address, Long> implements IAddressService {

    private final IAddressRepository repository;

    @Override
    protected IGenericRepository<Address, Long> getRepo() {
        return repository;
    }
}
