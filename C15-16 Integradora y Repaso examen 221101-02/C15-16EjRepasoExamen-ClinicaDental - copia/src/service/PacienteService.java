package service;

import dao.PacienteDAOH2;
import dao.iDao;
import model.Paciente;

import java.util.List;

public class PacienteService {
    private iDao<Paciente> pacienteiDao;

    public PacienteService(){
        this.pacienteiDao = new PacienteDAOH2();
    }

    public Paciente guardarPaciente(Paciente paciente){
        return pacienteiDao.guardar(paciente);
    }
    public void eliminarPaciente (int id){
        pacienteiDao.eliminar(id);
    }
    public void actualizarPaciente (Paciente paciente){
        pacienteiDao.actualizar(paciente);
    }
    public Paciente buscarPaciente (int id){
        return pacienteiDao.buscar(id);
    }
    public List<Paciente> buscarTodosPacientes(){
        return pacienteiDao.buscarTodo();
    }


}
