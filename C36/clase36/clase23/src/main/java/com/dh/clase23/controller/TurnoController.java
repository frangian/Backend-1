package com.dh.clase23.controller;

import com.dh.clase23.model.Turno;
import com.dh.clase23.service.OdontologoService;
import com.dh.clase23.service.PacienteService;
import com.dh.clase23.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    /*
    private TurnoService turnoService;
    @Autowired
    public TurnoController(TurnoService turnoService) {
        this.turnoService = turnoService;
    }
    @GetMapping
    public ResponseEntity<List<Turno>> listarLosTurnos(){
        return ResponseEntity.ok(turnoService.buscarTodosTurno());
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable("id") Integer id){
        //tengo dos alternativas.
        Turno turnoBuscado=turnoService.buscarTurno(id);
        if (turnoBuscado!=null){
            return ResponseEntity.ok(turnoBuscado);
        }
        else{
            //no existe el turno con el id ingresado
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Turno> registarTurno(@RequestBody Turno turno){
        PacienteService pacienteService= new PacienteService();
        OdontologoService odontologoService= new OdontologoService();
        ResponseEntity<Turno> respuesta;

        if (pacienteService.buscarPaciente(turno.getPaciente().getId())!=null&&
        odontologoService.buscarOdontologoXId(turno.getOdontologo().getId())!=null){
            //ambos existen en la BD
            //podemos registrar el turno sin problemas, indicamos ok (200)
            respuesta=ResponseEntity.ok(turnoService.guardarTurno(turno));
        }
        else{
            //uno o ambos no existen, debemos bloquear la operación
            respuesta=ResponseEntity.badRequest().build();
            //alternativa para seleccionar cualquier código
            //respuesta=ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return respuesta;

    }
    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){
        //verificar que el turno exista
        //control como el post
        PacienteService pacienteService= new PacienteService();
        OdontologoService odontologoService= new OdontologoService();
        ResponseEntity<Turno> respuesta;

        if(turnoService.buscarTurno(turno.getId())!=null){
            //es un id válido
            if (pacienteService.buscarPaciente(turno.getPaciente().getId())!=null&&
                    odontologoService.buscarOdontologoXId(turno.getOdontologo().getId())!=null){
                //ambos existen en la BD
                //podemos registrar el turno sin problemas, indicamos ok (200)
                turnoService.actualizarTurno(turno);
                return ResponseEntity.ok("Se actualizó el turno con id= "+turno.getId());
            }
            else{
                //uno o ambos no existen, debemos bloquear la operación
                return ResponseEntity.badRequest().body("Error al actualizar, verificar si el" +
                        " odontologo y/o el paciente existen en la base de datos.");
            }
        }
        else{
            //error con el id
            return ResponseEntity.badRequest().body("No se puede actualizar un turno" +
                    " que no exista en la base de datos.");
        }
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarTurno(@PathVariable Integer id){
        if (turnoService.buscarTurno(id)!=null){
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok().body("Se eliminó el turno con id= "+id);
        }
        else{
            return ResponseEntity.badRequest().body("No se puede eliminar el turno con id= "+id+
                    " ya que el mismo no existe en la base de datos.");
        }
    }

     */
}
