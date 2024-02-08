package com.ventasx.SistemaVentas.Configuration.Security.Auth;

import com.ventasx.SistemaVentas.Configuration.Security.Token.Token;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/ventasx/v1/authorization")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        authenticationService.refreshToken(request, response);
    }

    @PostMapping("/get-tokens-user/{username}&{tokenSessionStorage}")
    public ResponseEntity<Boolean> findAllValidTokenByUser(@PathVariable("username") String username, @PathVariable("tokenSessionStorage") String tokenSessionStorage){
        return new ResponseEntity<>(authenticationService.findAllValidTokenByUser(username, tokenSessionStorage), HttpStatus.OK);
    }
}
