package com.dh.ClinicaOdontologica.controller;


import com.dh.ClinicaOdontologica.entity.Paciente;
import com.dh.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    /*
    @GetMapping
    public String traerPacienteXEmail(Model model, @RequestParam("email") String email){
        Paciente pacienteBuscado = pacienteService.buscarPacienteByEmail(email);
        model.addAttribute("nombre",pacienteBuscado.getNombre());
        model.addAttribute("apellido",pacienteBuscado.getApellido());
        return "index";
    } */

    @GetMapping
    public ResponseEntity<List<Paciente>> listarPacientes(){
        return ResponseEntity.ok(pacienteService.buscarTodosPacientes());
    };
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Integer id){
        Paciente pacienteBuscado = pacienteService.buscarPaciente(id);
        if(pacienteBuscado!=null){
            return ResponseEntity.ok(pacienteBuscado);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    };
    @PostMapping
    public ResponseEntity<Paciente> registrarNuevoPaciente(@RequestBody Paciente paciente){
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }
    @PutMapping
    public ResponseEntity<String> actualizarPaciente(@RequestBody Paciente paciente){
        if(pacienteService.buscarPaciente(paciente.getId())!=null){
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("Se actualizo el paciente con apellido: "+paciente.getApellido());
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping
    public ResponseEntity<String> eliminarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if(pacienteBuscado!=null){
            pacienteService.eliminarPaciente(paciente.getId());
            return ResponseEntity.ok("Se elimino el paciente con id: "+paciente.getId());
        }
        else {
            return ResponseEntity.badRequest().body("El paciente con id: "+paciente.getId()+" no existe en la BD");
        }
    }

}
