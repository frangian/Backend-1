package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.entity.Paciente;
import com.dh.ClinicaOdontologica.respository.PacienteRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {
    private static final Logger logger = Logger.getLogger(PacienteService.class);
    PacienteRepository pacienteRepository;
    @Autowired
    public PacienteService (PacienteRepository pacienteRepository){
        this.pacienteRepository = pacienteRepository;
    }
    public Paciente guardarPaciente (Paciente paciente){
        logger.info("Se guardo el paciente en la BD correctamente");
        return pacienteRepository.save(paciente);
    }
    public void actualizarPaciente(Paciente paciente){
        pacienteRepository.save(paciente);
        logger.info("Se actualizo el odontologo: "+paciente.getId()+", en la BD correctamente");
    }
    public void eliminarPaciente(Long id){
        pacienteRepository.deleteById(id);
        logger.info("Se elimino el paciente con id: "+id+", de la BD correctamente");
    }
    public Optional<Paciente> buscarPaciente(Long id){
        return pacienteRepository.findById(id);
    }
    public List<Paciente> buscarTodosPacientes(){
        logger.info("Se realizo correctamente la busqueda de todos los pacientes existentes en la BD");
        return pacienteRepository.findAll();
    }
}
