package com.dh.ClinicaOdontologica.dao;

import com.dh.ClinicaOdontologica.model.Turno;

import java.util.ArrayList;
import java.util.List;

public class TurnoDAOLista implements IDao<Turno> {
    private List<Turno> turnosBD = new ArrayList<>();

    @Override
    public Turno guardar(Turno turno) {
        turnosBD.add(turno);
        return turno;
    }

    @Override
    public Turno buscar(Integer id) {
        for (Turno turno: turnosBD) {
            if (turno.getId().equals(id)){
                return turno;
            }
        }
        return null;
    }

    @Override
    public void actualizar(Turno turno) {
        eliminar(turno.getId());
        guardar(turno);
    }

    @Override
    public void eliminar(Integer id) {
        Turno turnoAeliminar = buscar(id);
        if(turnoAeliminar!=null){
            turnosBD.remove(turnoAeliminar);
        }
    }

    @Override
    public List<Turno> buscarTodo() {
        return turnosBD;
    }

    @Override
    public Turno buscarXString(String valor) { return null; }
}
