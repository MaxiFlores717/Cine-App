package com.cineapp.cine_app.clases;

import java.util.List;

public class Sala {

    private Integer id;

    private int numero;
    private List<Asiento> asientos;


    public Sala(Integer id, int numero, List<Asiento> asientos) {
        this.id = id;
        this.numero = numero;
        this.asientos = asientos;
    }


    
}
