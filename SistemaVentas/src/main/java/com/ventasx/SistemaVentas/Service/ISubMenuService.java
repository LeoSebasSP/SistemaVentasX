package com.ventasx.SistemaVentas.Service;

import com.ventasx.SistemaVentas.Persistence.Entity.SubMenu;

import java.util.List;

public interface ISubMenuService {
    SubMenu create(SubMenu t) throws Exception;

    List<SubMenu> getAll() throws Exception;
}
