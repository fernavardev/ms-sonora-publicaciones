package com.example.mssonorapublicaciones.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.mssonorapublicaciones.model.Categoria;
import com.example.mssonorapublicaciones.repository.CategoriaRepository;

@Component
public class CategoriaDataLoader implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;

    public CategoriaDataLoader(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoriaRepository.count() == 0) {

            categoriaRepository.save(new Categoria(
                "Guitarras y Bajos",
                "Conversaciones sobre guitarras eléctricas, acústicas y bajos."
            ));

            categoriaRepository.save(new Categoria(
                "Batería y Percusión",
                "Temas relacionados con baterías e instrumentos de percusión."
            ));

            categoriaRepository.save(new Categoria(
                "Teclados y Sintetizadores",
                "Conversaciones sobre pianos, teclados y sintetizadores."
            ));

            categoriaRepository.save(new Categoria(
                "Producción Musical",
                "Grabación, mezcla, masterización y software de audio."
            ));

            categoriaRepository.save(new Categoria(
                "Teoría Musical",
                "Armonía, escalas, composición y aprendizaje musical."
            ));
        }
    }
}