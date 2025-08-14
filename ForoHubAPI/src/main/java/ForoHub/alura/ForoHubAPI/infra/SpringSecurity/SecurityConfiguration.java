package ForoHub.alura.ForoHubAPI.infra.SpringSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // Este es el bean que configura la cadena de filtros de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Desactiva la protección CSRF (Cross-Site Request Forgery).
        // No es necesaria en APIs REST stateless (sin estado).
        http.csrf(csrf -> csrf.disable());

        // Configura la gestión de sesiones para que sea stateless.
        // La API no mantendrá estado de sesión, cada petición será independiente.
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Define las reglas de autorización para las peticiones HTTP.
        http.authorizeHttpRequests(authorize -> {
            // Permite todas las peticiones POST al endpoint /topicos/registrar sin autenticación.
            authorize.requestMatchers(HttpMethod.POST, "/topicos/registrar").permitAll();
            // Cualquier otra petición (a otros endpoints) requiere autenticación.
            authorize.anyRequest().authenticated();
        });

        // Desactiva la autenticación básica y el formulario de login.
        // No los necesitamos en una API REST que usará tokens.
        http.httpBasic(httpBasic -> httpBasic.disable());
        http.formLogin(formLogin -> formLogin.disable());

        return http.build();
    }
}
