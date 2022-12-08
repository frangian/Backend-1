package com.dh.ClinicaOdontologica.controller;


import com.dh.ClinicaOdontologica.entity.Odontologo;
import com.dh.ClinicaOdontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

//    @GetMapping
//    public String buscarOdontologoPorID(Model model, @RequestParam("id")Integer id){
//        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(id);
//        model.addAttribute("nombre",odontologoBuscado.getNombre());
//        model.addAttribute("apellido",odontologoBuscado.getApellido());
//        model.addAttribute("matricula",odontologoBuscado.getMatricula());
//        return "busOdontologo";
//    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos (){
        return ResponseEntity.ok(odontologoService.listarOdontologos()) ;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Integer id){
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(id);
        if (odontologoBuscado!=null){
            return ResponseEntity.ok(odontologoBuscado);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    };
    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo){
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }
    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo){
        if(odontologoService.buscarOdontologoXId(odontologo.getId())!=null){
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("El odontologo con el id: " + odontologo.getId() + " fue actualizado");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    @DeleteMapping
    public ResponseEntity<String> eliminarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologoBuscado = odontologoService.buscarOdontologoXId(odontologo.getId());
        if(odontologoBuscado!=null){
            odontologoService.eliminarOdontologo(odontologo.getId());
            return ResponseEntity.ok().body("Se elimino el odontologo con id: "+odontologo.getId());
        }
        else {
            return ResponseEntity.badRequest().body("No se puede eliminar el odontologo con id: "+odontologo.getId()+" ya que el mismo no existe en la base de datos.");
        }
    }

}
