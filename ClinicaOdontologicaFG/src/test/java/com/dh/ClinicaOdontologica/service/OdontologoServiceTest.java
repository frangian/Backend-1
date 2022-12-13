package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.entity.Odontologo;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class OdontologoServiceTest {

    @Autowired
    private OdontologoService odontologoService;

    @Test
    @Order(1)
    void guardarOdontologo() {
        Odontologo odontologoAguardar = new Odontologo("Gian","Fran","111");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAguardar);

        assertEquals(1L,odontologoGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarOdontologoPorIdTest(){
        Long idABuscar = 1L;
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarOdontologo(idABuscar);

        assertNotNull(odontologoBuscado);
    }
    @Test
    @Order(3)
    public void buscarOdontologosTest(){
        List<Odontologo> odontologo = odontologoService.listarOdontologos();
        Integer cantidadEsperada=1;

        assertEquals(cantidadEsperada, odontologo.size());
    }
    @Test
    @Order(4)
    public void actualizarOdontologosTest(){
        Odontologo odontologoAActualizar = new Odontologo(1L,"Gian","Fiore","111");
        odontologoService.actualizarOdontologo(odontologoAActualizar);
        Optional<Odontologo> odontologoActualizado = odontologoService.buscarOdontologo(odontologoAActualizar.getId());

        assertEquals("Fiore",odontologoActualizado.get().getNombre());
    }
    @Test
    @Order(5)
    public void eliminarOdontologoTest(){
        Long idAEliminar=1L;
        odontologoService.eliminarOdontologo(idAEliminar);
        Optional<Odontologo> odontologoEliminado = odontologoService.buscarOdontologo(idAEliminar);

        assertFalse(odontologoEliminado.isPresent());
    }


}