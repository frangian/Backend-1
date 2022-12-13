package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.dto.TurnoDTO;
import com.dh.ClinicaOdontologica.entity.Domicilio;
import com.dh.ClinicaOdontologica.entity.Odontologo;
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
class TurnoServiceTest {

    @Autowired
    private TurnoService turnoService;

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private  PacienteService pacienteService;

    @Test
    @Order(1)
    void guardarTurnoTest() {
        Paciente pacienteAGuardar = new Paciente("Fran","Gian","111", LocalDate.of(2022,12,8),new Domicilio("Belgrano",1,"Salta","Salta"));
        Paciente pacienteGuardado = pacienteService.guardarPaciente(pacienteAGuardar);
        Odontologo odontologoAguardar = new Odontologo("Gian","Fran","111");
        Odontologo odontologoGuardado = odontologoService.guardarOdontologo(odontologoAguardar);
        TurnoDTO turnoAGuardar = new TurnoDTO();
        turnoAGuardar.setFecha(LocalDate.of(2022,12,12));
        turnoAGuardar.setPacienteId(pacienteGuardado.getId());
        turnoAGuardar.setOdontologoId(odontologoGuardado.getId());
        TurnoDTO turnoGardado = turnoService.guardarTurno(turnoAGuardar);

        assertEquals(1L,turnoGardado.getId());
    }

    @Test
    @Order(2)
    void buscarTurnoTest() {
        Long idAbuscar = 1L;
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarTurno(idAbuscar);

        assertNotNull(turnoBuscado);
    }

    @Test
    @Order(3)
    void buscarTodosTurnosTest() {
        List<TurnoDTO> todosTurnos = turnoService.buscarTodosTurnos();
        Integer cantidadEsperada = 1;

        assertEquals(cantidadEsperada, todosTurnos.size());
    }

    @Test
    @Order(4)
    void actualizarTurnoTest() {
        TurnoDTO turnoDTOaActualizar = new TurnoDTO();
        turnoDTOaActualizar.setId(1L);
        turnoDTOaActualizar.setFecha(LocalDate.of(2022,12,10));
        turnoDTOaActualizar.setPacienteId(1L);
        turnoDTOaActualizar.setOdontologoId(1L);
        turnoService.actualizarTurno(turnoDTOaActualizar);
        Optional<TurnoDTO> turnoDTOactualizado = turnoService.buscarTurno(turnoDTOaActualizar.getId());

        assertEquals(LocalDate.of(2022,12,10),turnoDTOactualizado.get().getFecha());
    }

    @Test
    @Order(5)
    void eliminarTurnoTest() {
        Long idAEliminar=1L;
        turnoService.eliminarTurno(idAEliminar);
        Optional<TurnoDTO> turnoEliminado = turnoService.buscarTurno(idAEliminar);

        assertFalse(turnoEliminado.isPresent());

    }
}