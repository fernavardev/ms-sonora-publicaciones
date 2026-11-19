package com.example.mssonorapublicaciones.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mssonorapublicaciones.model.Comentario;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {

    List<Comentario> findByPublicacionId(Long publicacionId);
}