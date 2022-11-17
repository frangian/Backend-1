package com.dh.ClinicaOdontologica.controller;


import com.dh.ClinicaOdontologica.model.Paciente;
import com.dh.ClinicaOdontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/{id}")
    public Paciente buscarPaciente(@PathVariable("id") Integer id){
        Paciente pacienteBuscado = pacienteService.buscarPaciente(id);
        if(pacienteBuscado!=null){
            return pacienteService.buscarPaciente(id);
        }
        return null;
    };

    @GetMapping
    public List<Paciente> listarPacientes(){
        return pacienteService.buscarTodosPacientes();
    };

    @PostMapping
    public Paciente registrarNuevoPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    };

    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if(pacienteBuscado!=null){
            pacienteService.actualizarPaciente(paciente);
            return "Se actualizo el paciente con apellido: "+paciente.getApellido();
        }
        else {
            return "El paciente con id: "+paciente.getId()+" no existe en la BD";
        }
    }

    @DeleteMapping
    public String eliminarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteBuscado = pacienteService.buscarPaciente(paciente.getId());
        if(pacienteBuscado!=null){
            pacienteService.eliminarPaciente(paciente.getId());
            return "Se elimino el paciente con id: "+paciente.getId();
        }
        else {
            return "El paciente con id: "+paciente.getId()+" no existe en la BD";
        }
    }


}
