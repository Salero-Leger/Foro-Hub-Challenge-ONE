package ForoHub.alura.ForoHubAPI.controller;

import ForoHub.alura.ForoHubAPI.domain.cursos.Curso;
import ForoHub.alura.ForoHubAPI.domain.cursos.CursoRepository;
import ForoHub.alura.ForoHubAPI.domain.topicos.DatosDetalleTopico;
import ForoHub.alura.ForoHubAPI.domain.topicos.DatosRegistroTopico;
import ForoHub.alura.ForoHubAPI.domain.topicos.Topico;
import ForoHub.alura.ForoHubAPI.domain.topicos.TopicoRepository;
import ForoHub.alura.ForoHubAPI.domain.usuarios.Usuario;
import ForoHub.alura.ForoHubAPI.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<DatosDetalleTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopicodatos){
        Optional<Usuario> autorOptional = usuarioRepository.findById(datosRegistroTopicodatos.autorId());
        Optional<Curso> cursoOptional = cursoRepository.findById(datosRegistroTopicodatos.cursoId());

        if (autorOptional.isPresent() && cursoOptional.isPresent()){
            Topico topico = new Topico(datosRegistroTopicodatos,autorOptional.get(),cursoOptional.get());
            topicoRepository.save(topico);
            DatosDetalleTopico datosDetalleTopico =new DatosDetalleTopico(topico);
            return ResponseEntity.ok(datosDetalleTopico);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
