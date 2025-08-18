package ForoHub.alura.ForoHubAPI.controller;

import ForoHub.alura.ForoHubAPI.domain.usuarios.DatosAutenticacion;
import ForoHub.alura.ForoHubAPI.domain.usuarios.Usuario;
import ForoHub.alura.ForoHubAPI.infra.SpringSecurity.DatosJWTToken;
import ForoHub.alura.ForoHubAPI.infra.SpringSecurity.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJWTToken> autenticarUsuario(@RequestBody @Valid DatosAutenticacion datosAutenticacion) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacion.username(),
                datosAutenticacion.password()
        );

        Authentication usuarioAutenticado = authenticationManager.authenticate(authToken);

        String jwtToken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());

        return ResponseEntity.ok(new DatosJWTToken(jwtToken));
    }
}
