package com.dh.ClinicaOdontologica.controller;


import com.dh.ClinicaOdontologica.entity.Odontologo;
import com.dh.ClinicaOdontologica.exceptions.ResourceNotFoundException;
import com.dh.ClinicaOdontologica.service.OdontologoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private static final Logger logger = Logger.getLogger(OdontologoService.class);
    private OdontologoService odontologoService;
    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos (){
        return ResponseEntity.ok(odontologoService.listarOdontologos()) ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) throws ResourceNotFoundException{
        Optional<Odontologo> resultado = odontologoService.buscarOdontologo(id);
        if (resultado.isPresent()) {
            logger.info("El paciente con id: "+id+", fue encontrado en la BD");
            return ResponseEntity.ok(resultado.get());
        } else {
            throw new ResourceNotFoundException("El odontologo con id: "+id+" no existe en la BD");
        }
    };
    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        Optional<Odontologo> buscado = odontologoService.buscarOdontologo(odontologo.getId());
        if (buscado.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("El odontologo con el id: " + odontologo.getId() + " fue actualizado");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping
    public ResponseEntity<String> eliminarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> resultado = odontologoService.buscarOdontologo(odontologo.getId());
        if (resultado.isPresent()) {
            odontologoService.eliminarOdontologo(odontologo.getId());
            return ResponseEntity.ok("Se eliminó el odontologo correctamente");
        } else {
            throw new ResourceNotFoundException("El odontologo con id: "+odontologo.getId()+" no existe en la BD");
        }
    }

}
