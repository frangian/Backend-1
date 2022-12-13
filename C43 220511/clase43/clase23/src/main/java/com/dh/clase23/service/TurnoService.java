package com.dh.clase23.service;

import com.dh.clase23.dto.TurnoDTO;
import com.dh.clase23.model.Odontologo;
import com.dh.clase23.model.Paciente;
import com.dh.clase23.model.Turno;
import com.dh.clase23.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    private TurnoRepository turnoRepository;
    @Autowired
    public TurnoService(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    public TurnoDTO guardarTurno (TurnoDTO turno){
        Turno turnoAGuardar=turnoDTOaTurno(turno);
        Turno turnoGuardado=turnoRepository.save(turnoAGuardar);
        return turnoATurnoDTO(turnoGuardado);
    }
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
    }
    public void actualizarTurno(TurnoDTO turno){
        Turno turnoAActualizar=turnoDTOaTurno(turno);
        turnoRepository.save(turnoAActualizar);
    }
    public Optional<TurnoDTO> buscarTurno(Long id){
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            //turno encontrado
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        }
        else{
            //no se encuentra el turno
            return Optional.empty();
        }
    }
    public List<TurnoDTO> buscarTodosTurno(){
        List<Turno>turnosEncontrados=turnoRepository.findAll();
        List<TurnoDTO> respuesta= new ArrayList<>();
        for (Turno t:turnosEncontrados) {
            respuesta.add(turnoATurnoDTO(t));
        }
        return respuesta;
    }
    private TurnoDTO turnoATurnoDTO(Turno turno){
        //convertir ese turno en un turno DTO
        TurnoDTO respuesta=new TurnoDTO();
        respuesta.setId(turno.getId());
        respuesta.setFecha(turno.getFecha());
        respuesta.setOdontologoId(turno.getOdontologo().getId());
        respuesta.setPacienteId(turno.getPaciente().getId());
        return respuesta;
    }
    private Turno turnoDTOaTurno(TurnoDTO turnoDTO){
        Turno turno= new Turno();
        Paciente paciente= new Paciente();
        Odontologo odontologo= new Odontologo();
        //cargar los elementos
        paciente.setId(turnoDTO.getPacienteId());
        odontologo.setId(turnoDTO.getOdontologoId());
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());
        //asociar cada elemento
        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);
        //salida
        return turno;
    }


}
