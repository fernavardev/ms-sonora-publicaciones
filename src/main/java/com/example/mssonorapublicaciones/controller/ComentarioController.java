package com.example.mssonorapublicaciones.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.mssonorapublicaciones.model.Comentario;
import com.example.mssonorapublicaciones.service.ComentarioService;

@RestController
@RequestMapping("/comentarios")
public class ComentarioController {

    private final ComentarioService comentarioService;

    public ComentarioController(ComentarioService comentarioService) {
        this.comentarioService = comentarioService;
    }

    @GetMapping
    public List<Comentario> listarComentarios() {
        return comentarioService.listarComentarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> obtenerComentarioPorId(@PathVariable Long id) {
        return comentarioService.obtenerComentarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/publicacion/{publicacionId}")
    public List<Comentario> listarPorPublicacion(@PathVariable Long publicacionId) {
        return comentarioService.listarPorPublicacion(publicacionId);
    }

    @PostMapping
    public ResponseEntity<Comentario> crearComentario(
            @Valid @RequestBody Comentario comentario) {

        Comentario nuevoComentario = comentarioService.crearComentario(comentario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoComentario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> actualizarComentario(
            @PathVariable Long id,
            @RequestBody Comentario comentario) {

        Comentario actualizado = comentarioService.actualizarComentario(id, comentario);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarComentario(@PathVariable Long id) {
        comentarioService.eliminarComentario(id);
        return ResponseEntity.noContent().build();
    }
}