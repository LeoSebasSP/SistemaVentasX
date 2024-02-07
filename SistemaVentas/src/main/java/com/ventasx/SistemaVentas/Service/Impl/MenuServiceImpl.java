package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.Menu;
import com.ventasx.SistemaVentas.Persistence.Repository.IMenuRepository;
import com.ventasx.SistemaVentas.Service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuServiceImpl implements IMenuService {

    private final IMenuRepository repository;

    @Override
    public Menu create(Menu t) throws Exception {
        return repository.save(t);
    }

    @Override
    public List<Menu> getAll() throws Exception {
        return repository.findAll();
    }
}
