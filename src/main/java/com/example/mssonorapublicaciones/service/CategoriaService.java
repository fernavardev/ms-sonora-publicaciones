package com.example.mssonorapublicaciones.service;

import java.util.List;
import java.util.Optional;
import com.example.mssonorapublicaciones.model.Categoria;

public interface CategoriaService {

    List<Categoria> listarCategorias();

    Optional<Categoria> obtenerCategoriaPorId(Long id);

    Categoria crearCategoria(Categoria categoria);

    Categoria actualizarCategoria(Long id, Categoria categoria);

    void eliminarCategoria(Long id);
}