package com.ventasx.SistemaVentas.Controller;

import com.ventasx.SistemaVentas.Controller.Dto.UserDto;
import com.ventasx.SistemaVentas.Controller.Mapper.MapperBetweenDtoAndEntity;
import com.ventasx.SistemaVentas.Persistence.Entity.Menu;
import com.ventasx.SistemaVentas.Persistence.Entity.SubMenu;
import com.ventasx.SistemaVentas.Persistence.Entity.User;
import com.ventasx.SistemaVentas.Service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (service.findByUsername(username).isPresent()){
            User user = service.findByUsername(username).get();
            return new ResponseEntity<>(mapFromEntityToDto(user), HttpStatus.OK);
        }
//		if (usuario == null) {
//			throw new ResourceNotFound("Usuario", "username", username);
//		}
        return null;
    }

    @GetMapping("/submenus-by-username/{username}")
    public ResponseEntity<List<SubMenu>> listSubmenuByUser(@PathVariable(value = "username") String username) throws Exception {
        if (service.findByUsername(username).isPresent()){
            User user = service.findByUsername(username).get();
            List<SubMenu> list = service.listSubmenuByUser(user.getId());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
//		if (usuario == null) {
//			throw new ResourceNotFound("Usuario", "username", username);
//		}
        return null;
    }

    @GetMapping("/menus-by-username/{username}")
    public ResponseEntity<List<Menu>> listMenuByUser(@PathVariable(value = "username") String username) throws Exception {
        if (service.findByUsername(username).isPresent()){
            User user = service.findByUsername(username).get();
            List<Menu> list = service.listMenuByUser(user.getId());
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
//		if (usuario == null) {
//			throw new ResourceNotFound("Usuario", "username", username);
//		}
        return null;
    }
}
