package com.ventasx.SistemaVentas.Service;

import com.ventasx.SistemaVentas.Persistence.Entity.Menu;
import com.ventasx.SistemaVentas.Persistence.Entity.SubMenu;
import com.ventasx.SistemaVentas.Persistence.Entity.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserService extends ICrudService<User, Long>{

    User createUserPasswordEncoder(User user);
    List<Menu> listMenuByUser(@Param("user_id") Long user_id);
    List<SubMenu> listSubmenuByUser(Long user_id);
    Optional<User> findByUsername(String username);
    Integer insertSubmenuUser(Long user_id, Integer submenu_id);
}
