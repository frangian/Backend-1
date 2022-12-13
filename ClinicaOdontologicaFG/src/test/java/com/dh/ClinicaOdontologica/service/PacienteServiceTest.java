package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.entity.Domicilio;
import com.dh.ClinicaOdontologica.entity.Paciente;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class PacienteServiceTest {
    @Autowired
    private PacienteService pacienteService;

    @Test
    @Order(1)
    public void guardarPacienteTest(){
        Paciente pacienteAGuardar = new Paciente("Fran","Gian","111", LocalDate.of(2022,12,8),new Domicilio("Belgrano",1,"Salta","Salta"));
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);

        assertEquals(1L,pacienteGuardado.getId());
    }
    @Test
    @Order(2)
    public void buscarPacientePorIdTest(){
        Long idABuscar = 1L;
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPaciente(idABuscar);

        assertNotNull(pacienteBuscado);
    }
    @Test
    @Order(3)
    public void buscarPaicnetesTest(){
        List<Paciente> pacientes = pacienteService.buscarTodosPacientes();
        Integer cantidadEsperada=1;

        assertEquals(cantidadEsperada, pacientes.size());
    }
    @Test
    @Order(4)
    public void actualizarPacientesTest(){
        Paciente pacienteAActualizar = new Paciente(1L,"Fiore","Gian","111", LocalDate.of(2022,11,28),new Domicilio("Belgrano",1,"Salta","Salta"));
        pacienteService.actualizarPaciente(pacienteAActualizar);
        Optional<Paciente> pacienteActualizado = pacienteService.buscarPaciente(pacienteAActualizar.getId());

        assertEquals("Fiore",pacienteActualizado.get().getNombre());
    }
    @Test
    @Order(5)
    public void eliminarPacienteTest(){
        Long idAEliminar=1L;
        pacienteService.eliminarPaciente(idAEliminar);
        Optional<Paciente> pacienteEliminado = pacienteService.buscarPaciente(idAEliminar);

        assertFalse(pacienteEliminado.isPresent());
    }


}