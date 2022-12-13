package com.dh.ClinicaOdontologica.dto;

import java.time.LocalDate;

public class TurnoDTO {
    private Long id;
    private LocalDate fecha;
    private Long pacienteId;
    private String nombrePac, apellidoPac;
    private Long odontologoId;
    private String nombreOdo, apellidoOdo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getNombrePac() {
        return nombrePac;
    }

    public void setNombrePac(String nombrePac) {
        this.nombrePac = nombrePac;
    }

    public String getApellidoPac() {
        return apellidoPac;
    }

    public void setApellidoPac(String apellidoPac) {
        this.apellidoPac = apellidoPac;
    }

    public Long getOdontologoId() {
        return odontologoId;
    }

    public void setOdontologoId(Long odontologoId) {
        this.odontologoId = odontologoId;
    }

    public String getNombreOdo() {
        return nombreOdo;
    }

    public void setNombreOdo(String nombreOdo) {
        this.nombreOdo = nombreOdo;
    }

    public String getApellidoOdo() {
        return apellidoOdo;
    }

    public void setApellidoOdo(String apellidoOdo) {
        this.apellidoOdo = apellidoOdo;
    }
}
