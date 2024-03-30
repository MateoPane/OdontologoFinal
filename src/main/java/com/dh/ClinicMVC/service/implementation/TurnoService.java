package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.entity.DTO.TurnoRequestDTO;
import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.entity.Turno;
import com.dh.ClinicMVC.entity.DTO.TurnoDTO;
import com.dh.ClinicMVC.exception.ResourceNotFoundException;
import com.dh.ClinicMVC.repository.IOdontologoRepository;
import com.dh.ClinicMVC.repository.IPacienteRepository;
import com.dh.ClinicMVC.repository.ITurnoRepository;
import com.dh.ClinicMVC.service.ITurnoService;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TurnoService implements ITurnoService {
    private static final Logger LOGGER = Logger.getLogger(TurnoService.class);


    @Autowired
    private ITurnoRepository turnoRepository;
    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;


    @Override
    public TurnoDTO guardar(TurnoRequestDTO turnoRequestDTO) {
        Odontologo odontologo = odontologoRepository.findById(turnoRequestDTO.getOdontologoId())
                .orElseThrow(() -> new ResourceNotFoundException("El odontólogo no existe"));
        Paciente paciente = pacienteRepository.findById(turnoRequestDTO.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("El paciente no existe"));

        Turno turno = mapper.convertValue(turnoRequestDTO, Turno.class);
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);

        Turno turnoGuardado = turnoRepository.save(turno);
        return mapper.convertValue(turnoGuardado, TurnoDTO.class);
    }


    @Override
    public Set<TurnoDTO> listarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
        if (turnos.isEmpty()) {
            LOGGER.info("No se encontraron turnos para listar.");
            throw new ResourceNotFoundException("No se encontraron turnos para listar.");
        }

        Set<TurnoDTO> turnoDTO = new HashSet<>();
        for (Turno turno: turnos ) {
            turnoDTO.add(mapper.convertValue(turno, TurnoDTO.class));
        }
        return turnoDTO;
    }

    @Override
    public TurnoDTO buscarPorId(Long id) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        TurnoDTO turnoDTO = null;
        if (turnoOptional.isPresent()) {
            turnoDTO = mapper.convertValue(turnoOptional, TurnoDTO.class);
        }else {
            LOGGER.info("No se encontro el turno con id " + id);
            throw new ResourceNotFoundException("No se encontro el turno con el id " + id);
        }
            return turnoDTO;
    }

    @Override
    public void eliminar(Long id) {
        Optional<Turno> optionalTurno = turnoRepository.findById(id);
        if (optionalTurno.isPresent()) {
            LOGGER.info("Se elimino correctamente.");
            turnoRepository.deleteById(id);
        } else {
            LOGGER.info("No se encontro el turno con id " + id + " para eliminar.");
            throw new ResourceNotFoundException("No se encontro el turno con el id " + id + " para eliminar.");
        }
    }
    @Override
    public TurnoDTO actualizar(Long id, TurnoRequestDTO turnoRequestDTO) {
        Optional<Turno> turnoOptional = turnoRepository.findById(id);
        if (turnoOptional.isPresent()) {
            Turno turno = turnoOptional.get();
            Odontologo odontologo = odontologoRepository.findById(turnoRequestDTO.getOdontologoId())
                    .orElseThrow(() -> new ResourceNotFoundException("El odontólogo no existe"));
            Paciente paciente = pacienteRepository.findById(turnoRequestDTO.getPacienteId())
                    .orElseThrow(() -> new ResourceNotFoundException("El paciente no existe"));

            turno.setOdontologo(odontologo);
            turno.setPaciente(paciente);
            turno.setFecha(turnoRequestDTO.getFecha());

            Turno turnoActualizado = turnoRepository.save(turno);
            return mapper.convertValue(turnoActualizado, TurnoDTO.class);
        } else {
            throw new ResourceNotFoundException("No se encontró el turno con id " + id + " para actualizar.");
        }
    }
}