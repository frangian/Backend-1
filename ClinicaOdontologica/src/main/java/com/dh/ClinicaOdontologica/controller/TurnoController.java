package com.dh.ClinicaOdontologica.controller;

import com.dh.ClinicaOdontologica.entity.Turno;
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
    @GetMapping("buscar/{id}")
        public ResponseEntity<Turno> buscarTurno(@PathVariable Integer id){
        Turno turnoBuscado = turnoService.buscarTurno(id);
        if(turnoBuscado!=null){
            return ResponseEntity.ok(turnoBuscado);
        }
        else {
            return ResponseEntity.notFound().build();
        }
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
    @PutMapping
    public ResponseEntity<String> actualizarTurno (@RequestBody Turno turno){
        PacienteService pacienteService = new PacienteService();
        OdontologoService odontologoService = new OdontologoService();
        Turno turnoBuscado = turnoService.buscarTurno(turno.getId());
        ResponseEntity<Turno> respuesta;
        if(turnoBuscado!=null){
            if(pacienteService.buscarPaciente(turno.getPaciente().getId())!=null &&
                    odontologoService.buscarOdontologoXId(turno.getOdontologo().getId())!=null) {
                turnoService.actualizarTurno(turno);
                return ResponseEntity.ok("Se actualizo el turno con id: "+turno.getId());
            }
            else {
                return ResponseEntity.badRequest().body("Error al actualizar, verificar si el odontolo y/o el paciente existen en la base de datos.");
            }
        }
        else {
            return ResponseEntity.badRequest().body("No se puede actualizar un turno que no exista en la base de datos.");
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id){
        if(turnoService.buscarTurno(id)!=null){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok().body("Se elimino el turno con id: "+id);
        }
        else {
            return ResponseEntity.badRequest().body("No se puede eliminar el turno con id: "+id+" ya que el mismo no existe en la base de datos.");
        }
    }


}
