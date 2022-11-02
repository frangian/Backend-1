package service;

import dao.iDao;
import model.Paciente;

import java.util.List;

public class PacienteService {
    private iDao<Paciente> pacienteiDao;

    public PacienteService(iDao<Paciente> pacienteiDao){
        this.pacienteiDao = pacienteiDao;
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
