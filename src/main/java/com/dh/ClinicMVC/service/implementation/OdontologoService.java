package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.entity.DTO.OdontologoDTO;
import com.dh.ClinicMVC.exception.ResourceNotFoundException;
import com.dh.ClinicMVC.repository.IOdontologoRepository;
import com.dh.ClinicMVC.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OdontologoService implements IOdontologoService {
    private static final Logger LOGGER = Logger.getLogger(OdontologoService.class);

    @Autowired
    private IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;


    @Override
    public void guardar(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        odontologoRepository.save(odontologo);
    }

    @Override
    public Set<OdontologoDTO> listarTodos() {
        List<Odontologo> odontologos = odontologoRepository.findAll();
        if (odontologos.isEmpty()){
            LOGGER.info("No se encontraron odontologos para listar.");
            throw new ResourceNotFoundException("No se encontraron odontologos para listar.");
        }
        Set<OdontologoDTO> odontologoDTO = new HashSet<>();

        for (Odontologo odontologo : odontologos) {
            odontologoDTO.add(mapper.convertValue(odontologo, OdontologoDTO.class));
        }
        return odontologoDTO;
    }

    @Override
    public OdontologoDTO buscarPorId(Long id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        OdontologoDTO odontologoDTO = null;
        if(odontologo.isPresent()) {
            odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);
        } else {
            LOGGER.info("No se encontro el odontologo con id " + id);
            throw new ResourceNotFoundException("No se encontro el odontologo con id " + id);

        }
            return odontologoDTO;
    }

    @Override
    public void eliminar(Long id) {
        Optional<Odontologo> optionalOdontologo = odontologoRepository.findById(id);
        if (optionalOdontologo.isPresent()){
            LOGGER.info("Se elimino correctamente.");
            odontologoRepository.deleteById(id);
        } else {
            LOGGER.info("No se encontro el odontologo con id " + id + " para eliminar.");
            throw new ResourceNotFoundException("No se encontro el odontologo con el id " + id + " para eliminar.");
        }

    }

    @Override
    public void actualizar(OdontologoDTO odontologoDTO) {
        Optional<Odontologo> optionalOdontologo = odontologoRepository.findById(odontologoDTO.getId());
        if (optionalOdontologo.isPresent()) {
            Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
            odontologoRepository.save(odontologo);
        } else {
            LOGGER.info("No se encontro el odontologo con id " + odontologoDTO.getId() + " para actualizar.");
            throw new ResourceNotFoundException("No se encontro el odontologo con id " + odontologoDTO.getId() + " para actualizar.");
        }

    }
}