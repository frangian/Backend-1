package com.dh.ClinicaOdontologica.controller;

import com.dh.ClinicaOdontologica.model.Turno;
import com.dh.ClinicaOdontologica.service.OdontologoService;
import com.dh.ClinicaOdontologica.service.PacienteService;
import com.dh.ClinicaOdontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private TurnoService turnoService;

    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }
    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos(){
        return ResponseEntity.ok(turnoService.buscarTodosTurnos());
    }
    @PostMapping
    public ResponseEntity<Turno> registarTurno(@RequestBody Turno turno){
        PacienteService pacienteService = new PacienteService();
        OdontologoService odontologoService = new OdontologoService();
        ResponseEntity<Turno> respuesta;

        if(pacienteService.buscarPaciente(turno.getPaciente().getId())!=null &&
                odontologoService.buscarOdontologoXId(turno.getOdontologo().getId())!=null){
            respuesta=ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        else {
            respuesta=ResponseEntity.badRequest().build();
        }
        return respuesta;
    }


}
