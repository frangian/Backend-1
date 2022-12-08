package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.dao.IDao;
import com.dh.ClinicaOdontologica.dao.OdontologoDAOH2;
import com.dh.ClinicaOdontologica.entity.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService {
    private IDao<Odontologo> odontologoiDAO;

    public OdontologoService(){
        this.odontologoiDAO = new OdontologoDAOH2();
    }

    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoiDAO.guardar(odontologo);
    }

    public void actualizarOdontologo(Odontologo odontologo){ odontologoiDAO.actualizar(odontologo);}

    public void eliminarOdontologo(Integer id){odontologoiDAO.eliminar(id);}

    public Odontologo buscarOdontologoXId(Integer id){
        return odontologoiDAO.buscar(id);
    }

    public List<Odontologo> listarOdontologos(){
        return odontologoiDAO.buscarTodo();
    }
}
