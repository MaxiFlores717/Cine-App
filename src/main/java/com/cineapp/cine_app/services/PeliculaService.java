package com.cineapp.cine_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cineapp.cine_app.clases.Pelicula;
import com.cineapp.cine_app.repositories.IPeliculaRepository;

@Service
public class PeliculaService {

    @Autowired
    private IPeliculaRepository peliculaRepository;

    public List<Pelicula> obtenerCartelera() {
        return (List<Pelicula>) peliculaRepository.findAll();
    }

    public void guardarPelicula(Pelicula pelicula) {
        peliculaRepository.save(pelicula);
    }
    public Pelicula obtenerPorId(Integer id) {
        Optional<Pelicula> pelicula = peliculaRepository.findById(id);
        return pelicula.orElseThrow(() -> new RuntimeException("Película no encontrada con ID: " + id));
    }

}
