package com.ventasx.SistemaVentas.Configuration.Security;

import com.ventasx.SistemaVentas.Persistence.Entity.Permission;
import com.ventasx.SistemaVentas.Persistence.Entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig{

    private static final String[] WHITE_LIST_URL = {"/ventasx/v1/authorization/**",
            //"/ventasx/v1/**",
            "/ventasx/v1/utils/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()

                                .requestMatchers("/ventasx/v1/customers/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
                                .requestMatchers(GET, "/ventasx/v1/customers/**").hasAnyAuthority(Permission.ADMIN_READ.name(), Permission.MANAGER_READ.name())
                                .requestMatchers(POST, "/ventasx/v1/customers/**").hasAnyAuthority(Permission.ADMIN_CREATE.name(), Permission.MANAGER_CREATE.name())
                                .requestMatchers(PUT, "/ventasx/v1/customers/**").hasAnyAuthority(Permission.ADMIN_UPDATE.name(), Permission.MANAGER_UPDATE.name())
                                .requestMatchers(DELETE, "/ventasx/v1/customers/**").hasAnyAuthority(Permission.ADMIN_DELETE.name(), Permission.MANAGER_DELETE.name())

                                .requestMatchers("/ventasx/v1/products/**").hasAnyRole(Role.ADMIN.name(), Role.MANAGER.name())
                                .requestMatchers(GET, "/ventasx/v1/products/**").hasAnyAuthority(Permission.ADMIN_READ.name(), Permission.MANAGER_READ.name())
                                .requestMatchers(POST, "/ventasx/v1/products/**").hasAnyAuthority(Permission.ADMIN_CREATE.name(), Permission.MANAGER_CREATE.name())
                                .requestMatchers(PUT, "/ventasx/v1/products/**").hasAnyAuthority(Permission.ADMIN_UPDATE.name(), Permission.MANAGER_UPDATE.name())
                                .requestMatchers(DELETE, "/ventasx/v1/products/**").hasAnyAuthority(Permission.ADMIN_DELETE.name(), Permission.MANAGER_DELETE.name())

//                                .requestMatchers("/api/v1/admin/**").hasRole(Role.ADMIN.name())
//                                .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_READ.name())
//                                .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_CREATE.name())
//                                .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_UPDATE.name())
//                                .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(Permission.ADMIN_DELETE.name())

                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/ventasx/v1/authorization/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )
        ;

        return http.build();
    }
}
