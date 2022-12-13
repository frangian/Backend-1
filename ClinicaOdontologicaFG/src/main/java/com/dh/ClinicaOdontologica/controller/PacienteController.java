package com.dh.ClinicaOdontologica.controller;


import com.dh.ClinicaOdontologica.entity.Paciente;
import com.dh.ClinicaOdontologica.exceptions.BadRequestException;
import com.dh.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.ClinicaOdontologica.service.PacienteService;
import com.dh.ClinicaOdontologica.service.TurnoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private static final Logger logger = Logger.getLogger(PacienteController.class);
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.buscarTodosPacientes());
    };
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(id);
        if(pacienteBuscado.isPresent()){
            logger.info("El paciente con id: "+id+", fue encontrado en la BD");
            return ResponseEntity.ok(pacienteBuscado.get());
        }
        else {
            throw new ResourceNotFoundException("El paciente con id: "+id+" no existe en la BD");
        }
    };
    @PostMapping
    public ResponseEntity<Paciente> registrarNuevoPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizo el paciente con ID: "+paciente.getId());
        }
        else {
            throw new ResourceNotFoundException("No se puede actualizar los datos del paciente con id: "+paciente.getId()+" porque no existe en la BD");
        }
    }
    @DeleteMapping
    public ResponseEntity<String> eliminarPaciente(@RequestBody Paciente paciente) throws ResourceNotFoundException{
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if(pacienteBuscado.isPresent()){
            pacienteService.eliminarPaciente(paciente.getId());
            return ResponseEntity.ok("Se elimino el paciente con id: "+paciente.getId());
        }
        else {
            throw new ResourceNotFoundException("El paciente con id: "+paciente.getId()+" no existe en la BD");
        }
    }

}
