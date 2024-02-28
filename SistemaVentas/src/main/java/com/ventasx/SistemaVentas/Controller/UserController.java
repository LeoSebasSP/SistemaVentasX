package com.ventasx.SistemaVentas.Controller;

import com.ventasx.SistemaVentas.Controller.Dto.UserDto;
import com.ventasx.SistemaVentas.Controller.Mapper.MapperBetweenDtoAndEntity;
import com.ventasx.SistemaVentas.Exception.ResourceNotFound;
import com.ventasx.SistemaVentas.Persistence.Entity.Menu;
import com.ventasx.SistemaVentas.Persistence.Entity.SubMenu;
import com.ventasx.SistemaVentas.Persistence.Entity.User;
import com.ventasx.SistemaVentas.Service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventasx/v1/users")
@RequiredArgsConstructor
public class UserController extends MapperBetweenDtoAndEntity<UserDto, User> {

    private final IUserService service;

    @Override
    protected Class<User> getTClass() {
        return User.class;
    }

    @Override
    protected Class<UserDto> getDTOClass() {
        return UserDto.class;
    }

    @GetMapping("/by-username/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable(value = "username") String username) throws Exception {
        Optional<User> user = service.findByUsername(username);
        return user.map(value -> new ResponseEntity<>(mapFromEntityToDto(user.get()), HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFound("Usuario", "username", username));
    }

    @GetMapping("/submenus-by-username/{username}")
    public ResponseEntity<List<SubMenu>> listSubmenuByUser(@PathVariable(value = "username") String username) throws Exception {
        Optional<User> user = service.findByUsername(username);
        return user.map(value -> new ResponseEntity<>(service.listSubmenuByUser(value.getId()), HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFound("Usuario", "username", username));
    }

    @GetMapping("/menus-by-username/{username}")
    public ResponseEntity<List<Menu>> listMenuByUser(@PathVariable(value = "username") String username) throws Exception {
        Optional<User> user = service.findByUsername(username);
        return user.map(value -> new ResponseEntity<>(service.listMenuByUser(user.get().getId()), HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFound("Usuario", "username", username));
    }
}
