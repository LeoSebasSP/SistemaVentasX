package com.ventasx.SistemaVentas.Service.Impl;

import com.ventasx.SistemaVentas.Persistence.Entity.Menu;
import com.ventasx.SistemaVentas.Persistence.Entity.SubMenu;
import com.ventasx.SistemaVentas.Persistence.Entity.User;
import com.ventasx.SistemaVentas.Persistence.Repository.IGenericRepository;
import com.ventasx.SistemaVentas.Persistence.Repository.IUserRepository;
import com.ventasx.SistemaVentas.Service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends CrudServiceImpl<User, Long> implements IUserService {

    private final IUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    protected IGenericRepository<User, Long> getRepo() {
        return repository;
    }

    @Override
    public User createUserPasswordEncoder(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public List<Menu> listMenuByUser(Long user_id) {
        return repository.listMenuByUser(user_id);
    }

    @Override
    public List<SubMenu> listSubmenuByUser(Long user_id) {
        return repository.listSubmenuByUser(user_id);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Integer insertSubmenuUser(Long user_id, Integer submenu_id) {
        return repository.insertSubmenuUser(user_id, submenu_id);
    }
}
