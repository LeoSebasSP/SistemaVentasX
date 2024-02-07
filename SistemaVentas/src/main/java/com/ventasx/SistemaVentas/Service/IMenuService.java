package com.ventasx.SistemaVentas.Service;

import com.ventasx.SistemaVentas.Persistence.Entity.Menu;

import java.util.List;

public interface IMenuService {

    Menu create(Menu t) throws Exception;

    List<Menu> getAll() throws Exception;
}
