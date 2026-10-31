package com.example.mssonorapublicaciones.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.mssonorapublicaciones.model.Categoria;
import com.example.mssonorapublicaciones.model.Publicacion;
import com.example.mssonorapublicaciones.repository.CategoriaRepository;
import com.example.mssonorapublicaciones.repository.PublicacionRepository;

@Service
public class PublicacionServiceImpl implements PublicacionService {

    private static final Logger logger = LoggerFactory.getLogger(PublicacionServiceImpl.class);

    private final PublicacionRepository publicacionRepository;
    private final CategoriaRepository categoriaRepository;

    public PublicacionServiceImpl(
            PublicacionRepository publicacionRepository,
            CategoriaRepository categoriaRepository) {

        this.publicacionRepository = publicacionRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Publicacion> listarPublicaciones() {
        return publicacionRepository.findAll();
    }

    @Override
    public Optional<Publicacion> obtenerPublicacionPorId(Long id) {
        return publicacionRepository.findById(id);
    }

    @Override
    public List<Publicacion> listarPorCategoria(Long categoriaId) {
        return publicacionRepository.findByCategoriaId(categoriaId);
    }

    @Override
    public Publicacion crearPublicacion(Publicacion publicacion) {

        Categoria categoria = categoriaRepository
                .findById(publicacion.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        publicacion.setCategoria(categoria);

        Publicacion nuevaPublicacion = publicacionRepository.save(publicacion);

        logger.info("Publicación creada con ID: {}", nuevaPublicacion.getId());

        return nuevaPublicacion;
    }

    @Override
    public Publicacion actualizarPublicacion(
            Long id, Publicacion publicacion) {

        Publicacion existente = publicacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Publicación no encontrada"));

        Categoria categoria = categoriaRepository
                .findById(publicacion.getCategoria().getId())
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        existente.setUsuarioId(publicacion.getUsuarioId());
        existente.setCategoria(categoria);
        existente.setTitulo(publicacion.getTitulo());
        existente.setContenido(publicacion.getContenido());
        existente.setBaneado(publicacion.getBaneado());

        Publicacion actualizada = publicacionRepository.save(existente);

        logger.info("Publicación actualizada con ID: {}", id);

        return actualizada;
    }

    @Override
    public void eliminarPublicacion(Long id) {
        if (!publicacionRepository.existsById(id)) {
            throw new RuntimeException("Publicación no encontrada");
        }

        publicacionRepository.deleteById(id);
        logger.info("Publicación eliminada con ID: {}", id);
    }
}