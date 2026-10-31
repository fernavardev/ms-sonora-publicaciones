package com.example.mssonorapublicaciones.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.mssonorapublicaciones.model.Comentario;
import com.example.mssonorapublicaciones.model.Publicacion;
import com.example.mssonorapublicaciones.repository.ComentarioRepository;
import com.example.mssonorapublicaciones.repository.PublicacionRepository;

@Service
public class ComentarioServiceImpl implements ComentarioService {

    private static final Logger logger = LoggerFactory.getLogger(ComentarioServiceImpl.class);

    private final ComentarioRepository comentarioRepository;
    private final PublicacionRepository publicacionRepository;

    public ComentarioServiceImpl(
            ComentarioRepository comentarioRepository,
            PublicacionRepository publicacionRepository) {

        this.comentarioRepository = comentarioRepository;
        this.publicacionRepository = publicacionRepository;
    }

    @Override
    public List<Comentario> listarComentarios() {
        return comentarioRepository.findAll();
    }

    @Override
    public Optional<Comentario> obtenerComentarioPorId(Long id) {
        return comentarioRepository.findById(id);
    }

    @Override
    public List<Comentario> listarPorPublicacion(Long publicacionId) {
        return comentarioRepository.findByPublicacionId(publicacionId);
    }

    @Override
    public Comentario crearComentario(Comentario comentario) {

        Publicacion publicacion = publicacionRepository
                .findById(comentario.getPublicacion().getId())
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        comentario.setPublicacion(publicacion);

        Comentario nuevoComentario = comentarioRepository.save(comentario);

        logger.info("Comentario creado con ID: {}", nuevoComentario.getId());

        return nuevoComentario;
    }

    @Override
    public Comentario actualizarComentario(
            Long id, Comentario comentario) {

        Comentario existente = comentarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

        Publicacion publicacion = publicacionRepository
                .findById(comentario.getPublicacion().getId())
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        existente.setPublicacion(publicacion);
        existente.setNicknameAutor(comentario.getNicknameAutor());
        existente.setContenido(comentario.getContenido());
        existente.setBaneado(comentario.getBaneado());

        Comentario actualizado = comentarioRepository.save(existente);

        logger.info("Comentario actualizado con ID: {}", id);

        return actualizado;
    }

    @Override
    public void eliminarComentario(Long id) {
        if (!comentarioRepository.existsById(id)) {
            throw new RuntimeException("Comentario no encontrado");
        }

        comentarioRepository.deleteById(id);
        logger.info("Comentario eliminado con ID: {}", id);
    }
}