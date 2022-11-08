package org.example;

import dao.BD;
import model.Domicilio;
import model.Paciente;
import service.PacienteService;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        PacienteService pacienteService = new PacienteService();

        BD.crearTablas();

        Domicilio domicilio1 = new Domicilio("Los Arces",100,"Salta","Salta");
        Domicilio domicilio2 = new Domicilio("Jujuy",25,"Los Toldos","Buenos Aires");
        Domicilio domicilio3 = new Domicilio("Saravia",1250,"Rafaela","Santa Fe");

        Paciente paciente1 = new Paciente("Juan","Perez","30123456", LocalDate.of(1990,10,10),domicilio1);
        Paciente paciente2 = new Paciente("Andres","Garcia","20456789", LocalDate.of(1990,10,10),domicilio2);
        Paciente paciente3 = new Paciente("Ernesto","Melle","2222", LocalDate.of(1990,10,10),domicilio3);
        Paciente paciente4 = new Paciente("Sofia","Gonzalez","3333", LocalDate.of(1990,10,10),domicilio3);


        pacienteService.guardarPaciente(paciente1);
        System.out.println(paciente1);
        pacienteService.guardarPaciente(paciente2);
        System.out.println(paciente2);
        pacienteService.guardarPaciente(paciente3);
        System.out.println(paciente3);
        pacienteService.guardarPaciente(paciente4);
        System.out.println(paciente4);

    }
}