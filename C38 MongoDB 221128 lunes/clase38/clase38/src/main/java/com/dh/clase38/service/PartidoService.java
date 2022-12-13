package com.dh.clase38.service;

import com.dh.clase38.entity.Partido;
import com.dh.clase38.repository.PartidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidoService {
    private PartidoRepository partidoRepository;
    @Autowired
    public PartidoService(PartidoRepository partidoRepository) {
        this.partidoRepository = partidoRepository;
    }
    public Partido agregarPartido(Partido partido) {
        return partidoRepository.save(partido);
    }
}
