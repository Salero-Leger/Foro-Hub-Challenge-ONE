package ForoHub.alura.ForoHubAPI.domain.topicos;

import java.time.LocalDateTime;
import ForoHub.alura.ForoHubAPI.domain.usuarios.Usuario;
import ForoHub.alura.ForoHubAPI.domain.cursos.Curso;

public record DatosListadoTopicos(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Status status,
        String autorNombre,
        String cursoNombre
) {
    public DatosListadoTopicos(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha_creacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso().getNombre()
        );
    }
}