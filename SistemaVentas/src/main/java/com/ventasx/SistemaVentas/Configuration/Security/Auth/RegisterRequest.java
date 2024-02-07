package com.ventasx.SistemaVentas.Configuration.Security.Auth;

import com.ventasx.SistemaVentas.Persistence.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String name;
    private String lastname;
    private String password;
    private Role role;
}
