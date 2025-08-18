package ForoHub.alura.ForoHubAPI.domain.usuarios;

import jakarta.validation.constraints.NotBlank;

public record DatosAutenticacion(
        @NotBlank
        String username,
        @NotBlank
        String password
) {}
