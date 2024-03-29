package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.entity.Paciente;
import com.dh.ClinicMVC.entity.DTO.PacienteDTO;
import com.dh.ClinicMVC.exception.ResourceNotFoundException;
import com.dh.ClinicMVC.repository.IPacienteRepository;
import com.dh.ClinicMVC.service.IPacienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PacienteService implements IPacienteService {
    private static final Logger LOGGER = Logger.getLogger(PacienteService.class);
    @Autowired
    private IPacienteRepository pacienteRepository;

    @Autowired
    ObjectMapper mapper;

    @Override
    public void guardar(PacienteDTO pacienteDTO) {
        Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
        if (paciente == null) {
            LOGGER.error("Error al guardar el paciente: El objeto Paciente es nulo.");
            throw new ResourceNotFoundException("Error al guardar el paciente: El objeto Paciente es nulo.");
        }
        pacienteRepository.save(paciente);
    }

    @Override
    public Set<PacienteDTO> listarTodos() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        if (pacientes.isEmpty()) {
            LOGGER.info("No se encontraron pacientes para listar.");
            throw new ResourceNotFoundException("No se encontraron pacientes para listar.");
        }
        Set<PacienteDTO> pacienteDTO = new HashSet<>();

        for (Paciente paciente : pacientes) {
            pacienteDTO.add(mapper.convertValue(paciente, PacienteDTO.class));
        }
        return pacienteDTO;
    }


    @Override
    public PacienteDTO buscarPorId(Long id) throws ResourceNotFoundException{
        Optional<Paciente> pacienteOptional = pacienteRepository.findById(id);
        PacienteDTO pacienteDTO = null;
        if (pacienteOptional.isPresent()){
            pacienteDTO = mapper.convertValue(pacienteOptional, PacienteDTO.class);
        }else {
            LOGGER.info("No se encontro el paciente con id " + id);
            throw new ResourceNotFoundException("No se encontro el paciente con id " + id);
        }
        return pacienteDTO;
    }

    @Override
    public void eliminar(Long id) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(id);
        if (optionalPaciente.isPresent()) {
            LOGGER.info("Se elimino correctamente.");
            pacienteRepository.deleteById(id);
        } else {
            LOGGER.info("No se encontro el paciente con id: " + id + " para eliminar");
            throw new ResourceNotFoundException("No se encontro el paciente con id: " + id + " para eliminar");
        }
    }

    @Override
    public void actualizar(PacienteDTO pacienteDTO) {
        Optional<Paciente> optionalPaciente = pacienteRepository.findById(pacienteDTO.getId());
        if (optionalPaciente.isPresent()) {
            Paciente paciente = mapper.convertValue(pacienteDTO, Paciente.class);
            pacienteRepository.save(paciente);
        } else {
            LOGGER.info("No se encontró el paciente con ID: " + pacienteDTO.getId() + " para actualizar");
            throw new ResourceNotFoundException("No se encontró el paciente con ID: " + pacienteDTO.getId() + " para actualizar");
        }
    }
}
