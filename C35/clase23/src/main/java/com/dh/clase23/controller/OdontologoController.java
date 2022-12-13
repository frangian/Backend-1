package com.dh.clase23.controller;

import com.dh.clase23.model.Odontologo;
import com.dh.clase23.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//rest o no rest
@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }
    /*
    @GetMapping
    public String buscarOdontologoPorID(Model model, @RequestParam("id") Integer id){
        Odontologo odontologoBuscado=odontologoService.buscarOdontologoXId(id);
        model.addAttribute("nombre",odontologoBuscado.getNombre());
        model.addAttribute("apellido",odontologoBuscado.getApellido());
        model.addAttribute("matricula",odontologoBuscado.getMatricula());
        //como return, mandas el nombre de la vista
        return "busOdontologo";
    }



    @GetMapping
    public ResponseEntity<List<Odontologo>> buscarOdontologos(){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

     */
}
