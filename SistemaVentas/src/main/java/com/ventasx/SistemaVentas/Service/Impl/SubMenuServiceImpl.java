package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.SubMenu;
import com.ventasx.SistemaVentas.Persistence.Repository.ISubMenuRepository;
import com.ventasx.SistemaVentas.Service.ISubMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubMenuServiceImpl implements ISubMenuService {

    private final ISubMenuRepository repository;

    @Override
    public SubMenu create(SubMenu t) throws Exception {
        return repository.save(t);
    }

    @Override
    public List<SubMenu> getAll() throws Exception {
        return repository.findAll();
    }
}
