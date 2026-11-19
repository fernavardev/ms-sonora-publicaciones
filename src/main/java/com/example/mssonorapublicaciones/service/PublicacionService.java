package com.example.mssonorapublicaciones.service;

import java.util.List;
import java.util.Optional;
import com.example.mssonorapublicaciones.model.Publicacion;

public interface PublicacionService {

    List<Publicacion> listarPublicaciones();

    Optional<Publicacion> obtenerPublicacionPorId(Long id);

    List<Publicacion> listarPorCategoria(Long categoriaId);

    Publicacion crearPublicacion(Publicacion publicacion);

    Publicacion actualizarPublicacion(Long id, Publicacion publicacion);

    void eliminarPublicacion(Long id);
}