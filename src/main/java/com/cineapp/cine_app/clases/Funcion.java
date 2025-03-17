package com.cineapp.cine_app.clases;

import java.time.LocalDateTime;

public class Funcion {

    private Integer id;
    private Pelicula pelicula;
    private Sala sala;
    private LocalDateTime fechaHora;
    private double precio;


    
    public Funcion(Integer id, Pelicula pelicula, Sala sala, LocalDateTime fechaHora, double precio) {
        this.id = id;
        this.pelicula = pelicula;
        this.sala = sala;
        this.fechaHora = fechaHora;
        this.precio = precio;
    }



    public Funcion(Pelicula pelicula, Sala sala, LocalDateTime fechaHora, double precio) {
        this.pelicula = pelicula;
        this.sala = sala;
        this.fechaHora = fechaHora;
        this.precio = precio;
    }


    

}
