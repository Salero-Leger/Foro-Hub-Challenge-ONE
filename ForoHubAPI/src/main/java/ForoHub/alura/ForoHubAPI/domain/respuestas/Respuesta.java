package ForoHub.alura.ForoHubAPI.domain.respuestas;

import ForoHub.alura.ForoHubAPI.domain.topicos.Topico;
import ForoHub.alura.ForoHubAPI.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "respuesta")
@Entity(name = "Respuesta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private boolean solucion = false;
    private boolean activo = true;

    // Relación ManyToOne con Topico
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id") // Nombre de la columna en la DB
    private Topico topico;

    // Relación ManyToOne con Usuario (Autor)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id") // Nombre de la columna en la DB
    private Usuario autor;
}
