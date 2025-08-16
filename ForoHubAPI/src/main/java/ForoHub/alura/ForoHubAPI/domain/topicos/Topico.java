package ForoHub.alura.ForoHubAPI.domain.topicos;

import ForoHub.alura.ForoHubAPI.domain.cursos.Curso;
import ForoHub.alura.ForoHubAPI.domain.respuestas.Respuesta;
import ForoHub.alura.ForoHubAPI.domain.usuarios.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Table(name ="topico")
@Entity(name ="Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")


public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha_creacion = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private Status status = Status.ACTIVO;



    // Relación ManyToOne con Usuario (Autor)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    // Relación ManyToOne con Curso
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    // Relación OneToMany con Respuesta
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas;
    private boolean activo = true;

    public Topico(DatosRegistroTopico datosRegistroTopico, Usuario autor, Curso curso) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.autor = autor;
        this.curso = curso;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizarTopico) {
        if (datosActualizarTopico.titulo() != null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje() != null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.status() != null) {
            this.status = datosActualizarTopico.status();
        }
    }


    public void desactivarTopico(){
        this.activo = false;
    }
}
