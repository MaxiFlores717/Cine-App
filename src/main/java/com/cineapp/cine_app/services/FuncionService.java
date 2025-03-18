package com.cineapp.cine_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cineapp.cine_app.clases.Funcion;
import com.cineapp.cine_app.repositories.IFuncionRepository;

@Service
public class FuncionService {

    @Autowired
    private IFuncionRepository funcionRepository;

    public List<Funcion> obtenerFunciones() {
        return (List<Funcion>) funcionRepository.findAll();
    }

    public void guardarFuncion(Funcion funcion) {
        funcionRepository.save(funcion);
    }

}
