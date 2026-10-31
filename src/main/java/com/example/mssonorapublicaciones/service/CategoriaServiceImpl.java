package com.example.mssonorapublicaciones.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.mssonorapublicaciones.model.Categoria;
import com.example.mssonorapublicaciones.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private static final Logger logger =
            LoggerFactory.getLogger(CategoriaServiceImpl.class);

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> obtenerCategoriaPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria crearCategoria(Categoria categoria) {
        Categoria nuevaCategoria = categoriaRepository.save(categoria);
        logger.info("Categoría creada con ID: {}", nuevaCategoria.getId());
        return nuevaCategoria;
    }

    @Override
    public Categoria actualizarCategoria(Long id, Categoria categoria) {
        Categoria existente = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        existente.setNombre(categoria.getNombre());
        existente.setDescripcion(categoria.getDescripcion());

        Categoria actualizada = categoriaRepository.save(existente);
        logger.info("Categoría actualizada con ID: {}", id);
        return actualizada;
    }

    @Override
    public void eliminarCategoria(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new RuntimeException("Categoría no encontrada");
        }

        categoriaRepository.deleteById(id);
        logger.info("Categoría eliminada con ID: {}", id);
    }
}