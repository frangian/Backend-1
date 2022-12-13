package com.dh.ClinicaOdontologica.service;

import com.dh.ClinicaOdontologica.dto.TurnoDTO;
import com.dh.ClinicaOdontologica.entity.Odontologo;
import com.dh.ClinicaOdontologica.entity.Paciente;
import com.dh.ClinicaOdontologica.entity.Turno;
import com.dh.ClinicaOdontologica.respository.TurnoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    private static final Logger logger = Logger.getLogger(TurnoService.class);
    private TurnoRepository turnoRepository;
    @Autowired
    public TurnoService(TurnoRepository turnoRepository){
        this.turnoRepository = turnoRepository;
    }

    public TurnoDTO guardarTurno (TurnoDTO turnoDTO){
        Turno turnoAGuardar = turnoDTOaTurno(turnoDTO);
        Turno turnoGuardado = turnoRepository.save(turnoAGuardar);
        logger.info("Se guardo el turno en la BD correctamente");
        return turnoATurnoDTO(turnoGuardado);
    }
    public void actualizarTurno(TurnoDTO turno){
        Turno turnoAActualizar=turnoDTOaTurno(turno);
        turnoRepository.save(turnoAActualizar);
        logger.info("Se actualizo el turno: "+turno.getId()+", en la BD correctamente");
    }
    public void eliminarTurno(Long id){
        turnoRepository.deleteById(id);
        logger.info("Se elimino el turno con id: "+id+", de la BD correctamente");
    }
    public Optional<TurnoDTO> buscarTurno(Long id){
        Optional<Turno> turnoBuscado=turnoRepository.findById(id);
        if (turnoBuscado.isPresent()){
            logger.info("El turno con id: "+id+", fue encontrado en la BD");
            return Optional.of(turnoATurnoDTO(turnoBuscado.get()));
        }
        else{
            logger.info("El turno con id: "+id+", NO fue encontrado en la BD");
            return Optional.empty();
        }
    }
    public List<TurnoDTO> buscarTodosTurnos(){
        List<Turno>turnosEncontrados=turnoRepository.findAll();
        List<TurnoDTO> respuesta= new ArrayList<>();
        for (Turno t:turnosEncontrados) {
            respuesta.add(turnoATurnoDTO(t));
        }
        logger.info("Se realizo correctamente la busqueda de todos los turnos existentes en la BD");
        return respuesta;
    }
    private TurnoDTO turnoATurnoDTO(Turno turno){
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setPacienteId(turno.getPaciente().getId());
        turnoDTO.setNombrePac(turno.getPaciente().getNombre());
        turnoDTO.setApellidoPac(turno.getPaciente().getApellido());
        turnoDTO.setOdontologoId(turno.getOdontologo().getId());
        turnoDTO.setNombreOdo(turno.getOdontologo().getNombre());
        turnoDTO.setApellidoOdo(turno.getOdontologo().getApellido());
        return turnoDTO;
    }
    private Turno turnoDTOaTurno(TurnoDTO turnoDTO){
        Turno turno= new Turno();
        Paciente paciente= new Paciente();
        Odontologo odontologo= new Odontologo();

        paciente.setId(turnoDTO.getPacienteId());
        odontologo.setId(turnoDTO.getOdontologoId());
        turno.setId(turnoDTO.getId());
        turno.setFecha(turnoDTO.getFecha());

        turno.setPaciente(paciente);
        turno.setOdontologo(odontologo);

        return turno;
    }


}
