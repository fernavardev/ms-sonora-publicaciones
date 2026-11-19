package com.example.mssonorapublicaciones.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mssonorapublicaciones.model.Publicacion;

public interface PublicacionRepository extends JpaRepository<Publicacion, Long> {

    List<Publicacion> findByCategoriaId(Long categoriaId);
}