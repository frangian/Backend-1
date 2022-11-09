package com.example.entrenador.service;

import com.example.entrenador.model.Entrenador;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class EntrenadorServiceImp implements EntrenadorService{


    @Override
    public List<Entrenador> listaEntrenadores() {
        return Arrays.asList(
                new Entrenador("Francisco"),
                new Entrenador("Rodolfo")
        );
    }
}
