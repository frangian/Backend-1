package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.dao.IDao;
import com.dh.ClinicaOdontologica.dao.TurnoDAOLista;
import com.dh.ClinicaOdontologica.entity.Turno;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService {
    private IDao<Turno> turnoDAOLista;

    public TurnoService(){
        turnoDAOLista = new TurnoDAOLista();
    };

    public Turno guardarTurno (Turno turno){
        return turnoDAOLista.guardar(turno);
    }
    public void eliminarTurno(Integer id){
        turnoDAOLista.eliminar(id);
    }
    public void actualizarTurno(Turno turno){
        turnoDAOLista.actualizar(turno);
    }
    public Turno buscarTurno(Integer id){
        return turnoDAOLista.buscar(id);
    }
    public List<Turno> buscarTodosTurnos(){
        return turnoDAOLista.buscarTodo();
    }

}
