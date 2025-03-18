package com.cineapp.cine_app.controllers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cineapp.cine_app.clases.Pelicula;
import com.cineapp.cine_app.services.PeliculaService;

@Controller
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @GetMapping("/formPelicula")
    public String mostrarFormulario() {
        return "formPelicula";
    }

    @PostMapping("guardar")
    public String guardarPelicula(@RequestParam("titulo") String titulo,
            @RequestParam("genero") String genero,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("duracion") int duracion,
            @RequestParam("poster") MultipartFile file) {

        String posterPath = guardarImagen(file, titulo);
        Pelicula pelicula = new Pelicula(null, titulo, genero, descripcion, duracion, posterPath);
        peliculaService.guardarPelicula(pelicula);
        ;

        return "redirect:/peliculas/cartelera";
    }

    private String guardarImagen(MultipartFile file, String titulo) {

        try {
            // Crear directorio si no existe
            File directorio = new File("C://cineapp//uploads");
            if (!directorio.exists()) {
                directorio.mkdirs();
            }

            // Obtener la extensión del archivo (ejemplo: .jpg, .png)
            String extension = "";
            String originalFilename = file.getOriginalFilename();
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            // Limpiar el título: quitar acentos y caracteres especiales
            String tituloLimpio = Normalizer.normalize(titulo, Normalizer.Form.NFD)
                    .replaceAll("[^\\p{ASCII}]", "") // Eliminar acentos
                    .replaceAll("[^a-zA-Z0-9]", "_") // Reemplazar caracteres no permitidos por "_"
                    .toLowerCase(); // Convertir a minúsculas

            // Crear un nombre único con el título y un UUID corto
            String nombreUnico = tituloLimpio + "_" + UUID.randomUUID().toString().substring(0, 8) + extension;

            // Definir la ruta completa del archivo
            Path rutaCompleta = Paths.get("C://cineapp//uploads" + "//" + nombreUnico);

            // Guardar el archivo
            Files.write(rutaCompleta, file.getBytes());

            // Retornar la ruta accesible desde el frontend
            return nombreUnico;

        } catch (IOException e) {
            e.printStackTrace();
            return "img/default.jpg";
        }
    }

    @GetMapping("/cartelera")
    public String mostrarCartelera(Model model) {
        model.addAttribute("peliculas", peliculaService.obtenerCartelera());
        return "cartelera";
    }

}
