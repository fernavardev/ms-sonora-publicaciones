package com.example.mssonorapublicaciones.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "COMENTARIO")
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "La publicación es obligatoria")
    @ManyToOne
    @JoinColumn(name = "PUBLICACION_ID")
    private Publicacion publicacion;

    @NotBlank(message = "El nickname del autor es obligatorio")
    private String nicknameAutor;

    @NotBlank(message = "El contenido es obligatorio")
    private String contenido;

    private Boolean baneado = false;

    public Comentario() {
    }

    public Comentario(Publicacion publicacion, String nicknameAutor, String contenido) {
        this.publicacion = publicacion;
        this.nicknameAutor = nicknameAutor;
        this.contenido = contenido;
        this.baneado = false;
    }

    public Long getId() {
        return id;
    }

    public Publicacion getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(Publicacion publicacion) {
        this.publicacion = publicacion;
    }

    public String getNicknameAutor() {
        return nicknameAutor;
    }

    public void setNicknameAutor(String nicknameAutor) {
        this.nicknameAutor = nicknameAutor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Boolean getBaneado() {
        return baneado;
    }

    public void setBaneado(Boolean baneado) {
        this.baneado = baneado;
    }
}