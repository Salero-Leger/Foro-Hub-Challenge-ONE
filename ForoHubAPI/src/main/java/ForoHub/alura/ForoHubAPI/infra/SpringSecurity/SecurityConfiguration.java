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

        http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers(HttpMethod.POST, "/topicos/registrar").permitAll();
            authorize.requestMatchers(HttpMethod.GET, "/topicos").permitAll();
            authorize.requestMatchers(HttpMethod.GET, "/topicos/**").permitAll();
            authorize.requestMatchers(HttpMethod.PUT, "/topicos/actualizar/**").permitAll();
            authorize.requestMatchers(HttpMethod.DELETE, "/topicos/eliminar/**").permitAll();

            authorize.anyRequest().authenticated();
        });


        http.httpBasic(httpBasic -> httpBasic.disable());
        http.formLogin(formLogin -> formLogin.disable());

        return http.build();
    }
}
