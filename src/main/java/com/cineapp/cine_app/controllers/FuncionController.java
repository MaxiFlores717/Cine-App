package com.cineapp.cine_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cineapp.cine_app.services.PeliculaService;



@Controller
public class FuncionController {

    @Autowired
    private PeliculaService peliculaService;


    @RequestMapping(value = {"/paginaInicio", "/"})
    public String index(Model model) {
        return "index";
    }

    @GetMapping("/cartelera")
    public String mostrarCartelera(Model model) {
        model.addAttribute("peliculas", peliculaService.obtenerCartelera());
        return "cartelera";
    }

}
