package ForoHub.alura.ForoHubAPI.domain.topicos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DatosActualizarTopico(
    @NotBlank
    @Size(min = 1, max = 255)
    @NotNull
    String titulo,
    @NotBlank
    String mensaje,
    Status status
) {

}
