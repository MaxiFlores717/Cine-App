package com.cineapp.cine_app.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cineapp.cine_app.clases.Pelicula;

@Repository
public interface IPeliculaRepository extends CrudRepository <Pelicula,Integer>{

}
