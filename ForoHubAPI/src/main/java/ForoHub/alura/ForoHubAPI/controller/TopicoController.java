package ForoHub.alura.ForoHubAPI.controller;

import ForoHub.alura.ForoHubAPI.domain.cursos.Curso;
import ForoHub.alura.ForoHubAPI.domain.cursos.CursoRepository;
import ForoHub.alura.ForoHubAPI.domain.topicos.*;
import ForoHub.alura.ForoHubAPI.domain.usuarios.Usuario;
import ForoHub.alura.ForoHubAPI.domain.usuarios.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public Page<DatosListadoTopicos> listar(@PageableDefault(size = 10 , sort = {"titulo"})Pageable paginacion){
         return  topicoRepository.findAllByActivoTrue(paginacion).map(DatosListadoTopicos::new);
    }

    @GetMapping("/{id}")
    public  ResponseEntity<DatosDetalleTopico> listarPorId(@PathVariable Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()){
            Topico topico = topicoOptional.get();
            DatosDetalleTopico datosDetalleTopico = new DatosDetalleTopico(topico);
            return ResponseEntity.ok(datosDetalleTopico);
        }else {
            return  ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/actualizar/{id}")
    @Transactional
    public ResponseEntity<DatosDetalleTopico> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datosActualizarTopico
    ) {

        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.actualizarDatos(datosActualizarTopico);
            topicoRepository.save(topico);
            DatosDetalleTopico datosDetalleTopico = new DatosDetalleTopico(topico);
            return ResponseEntity.ok(datosDetalleTopico);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isPresent()) {
            Topico topico = topicoOptional.get();
            topico.desactivarTopico();
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    }

