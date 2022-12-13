package com.dh.clase23.service;

import com.dh.clase23.exception.ResourceNotFoundException;
import com.dh.clase23.model.Odontologo;
import com.dh.clase23.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    private OdontologoRepository odontologoRepository;
    @Autowired
    public OdontologoService(OdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public List<Odontologo> listarOdontologos(){
        return odontologoRepository.findAll();
    }
    public Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoRepository.save(odontologo);
    }
    public void actualizarOdontologo(Odontologo odontologo){
        odontologoRepository.save(odontologo);
    }
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException{
        //el se va a encargar de emitir la exception
        Optional<Odontologo> odontologoAEliminar=buscarOdontologoXId(id);
        if (odontologoAEliminar.isPresent()){
            odontologoRepository.deleteById(id);
        }
        else{
            throw new ResourceNotFoundException("El odontologo a eliminar no existe" +
                    " en la base de datos, se intentó encontrar sin éxito en id= "+id);
        }

    }
    public Optional<Odontologo> buscarOdontologoXId(Long id){
        return odontologoRepository.findById(id);
    }


}
