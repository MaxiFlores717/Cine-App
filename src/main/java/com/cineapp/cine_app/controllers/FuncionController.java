package com.cineapp.cine_app.controllers;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cineapp.cine_app.clases.Funcion;
import com.cineapp.cine_app.clases.Pelicula;
import com.cineapp.cine_app.services.FuncionService;
import com.cineapp.cine_app.services.PeliculaService;



@Controller
@RequestMapping("/funciones")
public class FuncionController {


    @Autowired
    private FuncionService funcionService;

    @Autowired
    private PeliculaService peliculaService;


    @RequestMapping(value = {"/paginaInicio", "/"})
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/nueva")
    public String mostrarFormulario(Model model) {
        List<Pelicula> peliculas = peliculaService.obtenerCartelera();
        model.addAttribute("peliculas", peliculas);
        return "formFuncion";
    }

    @PostMapping("/guardar")
    public String guardarFuncion(@RequestParam("peliculaId") Integer peliculaId,
                                 @RequestParam("fechaHora") String fechaHora,
                                 @RequestParam("precio") double precio) {

        Pelicula pelicula = peliculaService.obtenerPorId(peliculaId);
        LocalDateTime fechaHoraParsed = LocalDateTime.parse(fechaHora);

        Funcion funcion = new Funcion(null,pelicula, fechaHoraParsed, precio);
        funcionService.guardarFuncion(funcion);

        return "redirect:/funciones/listado";
    }

    @GetMapping("/listado")
    public String mostrarFunciones(Model model) {
        model.addAttribute("funciones", funcionService.obtenerFunciones());
        return "listado";
    }



}
