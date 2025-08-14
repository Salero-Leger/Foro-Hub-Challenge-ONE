package ForoHub.alura.ForoHubAPI.domain.usuarios;

import ForoHub.alura.ForoHubAPI.domain.perfiles.Perfil;
import ForoHub.alura.ForoHubAPI.domain.respuestas.Respuesta;
import ForoHub.alura.ForoHubAPI.domain.topicos.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String correoElectronico;
    private String contrasena;
    private boolean activo = true;

    // Relación ManyToOne con Perfil
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "perfiles") // El nombre de la columna en la DB
    private Perfil perfil;

    // Relación OneToMany con Topico (parte inversa)
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topico> topicos;

    // Relación OneToMany con Respuesta (parte inversa)
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Respuesta> respuestas;
}
