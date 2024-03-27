package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.entity.DTO.TurnoRequestDTO;
import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.entity.Turno;
import com.dh.ClinicMVC.entity.DTO.TurnoDTO;
import com.dh.ClinicMVC.repository.IOdontologoRepository;
import com.dh.ClinicMVC.repository.IPacienteRepository;
import com.dh.ClinicMVC.repository.ITurnoRepository;
import com.dh.ClinicMVC.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TurnoService implements ITurnoService {


    @Autowired
    private ITurnoRepository turnoRepository;
    @Autowired
    private IPacienteRepository pacienteRepository;
    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public TurnoDTO guardar(TurnoRequestDTO turnoDTO) {
        Odontologo odontologo = odontologoRepository.findById(turnoDTO.getOdontologoId())
                .orElseThrow();
        Paciente paciente = pacienteRepository.findById(turnoDTO.getPacienteId()).orElseThrow();

        Turno turno = new Turno();
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        turno.setFecha(turnoDTO.getFecha());
        Turno turnoGuardado = turnoRepository.save(turno);

        return mapper.convertValue(turnoGuardado, TurnoDTO.class);
    }

    @Override
    public Set<TurnoDTO> listarTodos() {
        List<Turno> turnos = turnoRepository.findAll();
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
        }
            return turnoDTO;
    }

    @Override
    public void eliminar(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public void actualizar(TurnoDTO turnoDTO) {
        Turno turno = mapper.convertValue(turnoDTO, Turno.class);
        turnoRepository.save(turno);
    }

}