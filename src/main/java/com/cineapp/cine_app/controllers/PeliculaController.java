package com.cineapp.cine_app.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cineapp.cine_app.clases.Pelicula;
import com.cineapp.cine_app.services.PeliculaService;

@Controller
public class PeliculaController {


     @Autowired
    private PeliculaService peliculaService;

    @Value("${upload.dir}") // Se configura en application.properties
    private String uploadDir;

    @GetMapping("/nueva")
    public String mostrarFormulario() {
        return "formPelicula";
    }

    @PostMapping("guardar")
    public String guardarPelicula(@RequestParam("titulo") String titulo,
                                  @RequestParam("genero") String genero,
                                  @RequestParam("descripcion") String descripcion,
                                  @RequestParam("duracion") int duracion,
                                  @RequestParam("poster") MultipartFile file) {

        String posterPath = guardarImagen(file);
        Pelicula pelicula = new Pelicula(null, titulo, genero, descripcion, duracion, posterPath);
        peliculaService.guardarPelicula(pelicula);;

        return "redirect:/cartelera";
    }

    private String guardarImagen(MultipartFile file) {

        try {
            // Crear directorio si no existe
            File directorio = new File("C://cineapp//uploads");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Guardar archivo en el directorio
            String nombreUnico = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path rutaCompleta = Paths.get("C://cineapp//uploads" + "//" + nombreUnico);

            Files.write(rutaCompleta, file.getBytes());


            return nombreUnico;
        } catch (IOException e) {
            e.printStackTrace();
            return "img/default.jpg";
        }
    }


}
