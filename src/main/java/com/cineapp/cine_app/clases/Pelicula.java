package com.cineapp.cine_app.clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private String genero;
    private String descripcion;
    private int duracion;// en minutos

    private String posterPath;
    
    
    public Pelicula(Integer id, String titulo, String genero, String descripcion, int duracion, String posterPath) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.descripcion = descripcion;
        this.duracion = duracion;
        this.posterPath = posterPath;
    }

    public Pelicula() {
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitulo() {
        return titulo;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public String getGenero() {
        return genero;
    }


    public void setGenero(String genero) {
        this.genero = genero;
    }


    public String getDescripcion() {
        return descripcion;
    }


    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public int getDuracion() {
        return duracion;
    }


    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    

    

    
}
