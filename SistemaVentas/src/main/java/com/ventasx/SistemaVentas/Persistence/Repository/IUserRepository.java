package com.ventasx.SistemaVentas.Persistence.Repository;

import com.ventasx.SistemaVentas.Persistence.Entity.Menu;
import com.ventasx.SistemaVentas.Persistence.Entity.SubMenu;
import com.ventasx.SistemaVentas.Persistence.Entity.User;
import jakarta.persistence.ConstructorResult;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends IGenericRepository<User, Long>{
    Optional<User> findByUsername(String username);

//    @Query(value = "select u.submenus.menuId from User u where u.id = :user_id")
//    List<Menu> listMenuByUser(@Param("user_id") Long user_id);

    @Query("SELECT DISTINCT m FROM User u  " +
            "JOIN u.submenus s " +
            "JOIN s.menu m " +
            "WHERE u.id = :user_id")
    List<Menu> listMenuByUser(@Param("user_id") Long user_id);

    @Query(value = "select u.submenus from User u where u.id = :user_id")
    List<SubMenu> listSubmenuByUser(@Param("user_id") Long user_id);

    @Transactional
    @Modifying
    @Query(value = "insert into user_submenu(user_id, submenu_id) values (:user_id,:submenu_id)", nativeQuery = true)
    Integer insertSubmenuUser(@Param("user_id") Long user_id, @Param("submenu_id") Integer submenu_id);
}
