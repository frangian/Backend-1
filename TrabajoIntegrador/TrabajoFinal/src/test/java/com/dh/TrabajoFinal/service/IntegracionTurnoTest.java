package com.dh.TrabajoFinal.service;

import com.dh.TrabajoFinal.entity.Domicilio;
import com.dh.TrabajoFinal.entity.Odontologo;
import com.dh.TrabajoFinal.entity.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class IntegracionTurnoTest {
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private MockMvc mockMvc;

    private void cargarTurnoInicial(){
        Domicilio domicilio = new Domicilio("Calle a",23,"Salta","Salta");
        Paciente paciente = new Paciente("Fran","Gian",domicilio,"123123", LocalDate.of(2022,12,7));
        pacienteService.guardarPaciente(paciente);
        Odontologo odontologo = new Odontologo("N12","Juan","Garcia");

    }


}
