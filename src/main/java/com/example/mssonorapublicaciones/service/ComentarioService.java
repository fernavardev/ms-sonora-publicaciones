package com.example.mssonorapublicaciones.service;

import java.util.List;
import java.util.Optional;
import com.example.mssonorapublicaciones.model.Comentario;

public interface ComentarioService {

    List<Comentario> listarComentarios();

    Optional<Comentario> obtenerComentarioPorId(Long id);

    List<Comentario> listarPorPublicacion(Long publicacionId);

    Comentario crearComentario(Comentario comentario);

    Comentario actualizarComentario(Long id, Comentario comentario);

    void eliminarComentario(Long id);
}