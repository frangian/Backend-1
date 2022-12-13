package com.dh.clase23.service;
import com.dh.clase23.model.Paciente;
import com.dh.clase23.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private PacienteRepository pacienteRepository;
    @Autowired
    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }
    public Paciente guardarPaciente (Paciente paciente){
        return pacienteRepository.save(paciente);
    }
    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
    }
    public Optional<Paciente> buscarPaciente(Long id){
        return pacienteRepository.findById(id);
    }
    public List<Paciente> buscarTodosPacientes(){
        return pacienteRepository.findAll();
    }
    public Optional<Paciente> buscarPacienteByEmail(String email){
        return pacienteRepository.findByEmail(email);
    }
}
