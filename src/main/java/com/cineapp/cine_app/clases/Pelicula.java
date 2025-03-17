package com.cineapp.cine_app.clases;

public class Pelicula {

    private Integer id;
    private String titulo;
    private String genero;
    private String descripcion;
    private int duracion;// en minutos

    
    public Pelicula(Integer id, String titulo, String genero, String descripcion, int duracion) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.descripcion = descripcion;
        this.duracion = duracion;
    }

    
}
