package com.dh.ClinicMVC.service.implementation;

import com.dh.ClinicMVC.entity.Odontologo;
import com.dh.ClinicMVC.entity.DTO.OdontologoDTO;
import com.dh.ClinicMVC.repository.IOdontologoRepository;
import com.dh.ClinicMVC.service.IOdontologoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OdontologoService implements IOdontologoService {

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
        if(odontologo.isPresent())
            odontologoDTO = mapper.convertValue(odontologo, OdontologoDTO.class);

            return odontologoDTO;
    }

    @Override
    public void eliminar(Long id) {
        odontologoRepository.deleteById(id);
    }

    @Override
    public void actualizar(OdontologoDTO odontologoDTO) {
        Odontologo odontologo = mapper.convertValue(odontologoDTO, Odontologo.class);
        odontologoRepository.save(odontologo);
    }
}