package com.example.mssonorapublicaciones.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mssonorapublicaciones.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}