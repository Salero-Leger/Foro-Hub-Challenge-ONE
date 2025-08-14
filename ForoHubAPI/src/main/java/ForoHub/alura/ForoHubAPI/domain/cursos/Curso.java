package ForoHub.alura.ForoHubAPI.domain.cursos;

import ForoHub.alura.ForoHubAPI.domain.topicos.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "curso")
@Entity(name = "Curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String categoria;
    private boolean activo = true;

    // Relación OneToMany con Topico (parte inversa)
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Topico> topicos;
}
