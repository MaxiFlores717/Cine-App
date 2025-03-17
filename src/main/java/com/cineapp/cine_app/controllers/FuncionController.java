package com.cineapp.cine_app.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cineapp.cine_app.clases.Asiento;
import com.cineapp.cine_app.clases.Funcion;
import com.cineapp.cine_app.clases.Pelicula;
import com.cineapp.cine_app.clases.Sala;



@Controller
public class FuncionController {

    private List<Funcion> funciones = new ArrayList<>();
    private List<Asiento> asientos = new ArrayList<>();

    public FuncionController() {
        // Agregamos funciones de prueba
        funciones.add(new Funcion(1, new Pelicula(1, "Avatar 2", "Acción", "Película de ciencia ficción", 180), new Sala(1, 1, asientos), LocalDateTime.now().plusDays(1), 10.0));
        funciones.add(new Funcion(2, new Pelicula(2, "Batman", "Acción", "Película de superhéroes", 150), new Sala(2, 2, asientos), LocalDateTime.now().plusDays(2), 12.0));
    }

    @RequestMapping(value = {"/paginaInicio", "/"})
    public String index(Model model) {
        return "index";
    }

}
