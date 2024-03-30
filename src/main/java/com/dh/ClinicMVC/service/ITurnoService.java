package com.dh.ClinicMVC.service;

import com.dh.ClinicMVC.entity.DTO.TurnoDTO;
import com.dh.ClinicMVC.entity.DTO.TurnoRequestDTO;
import com.fasterxml.jackson.databind.JsonMappingException;

import java.util.Set;

public interface ITurnoService {

    Set<TurnoDTO> listarTodos();

    TurnoDTO buscarPorId(Long id);

    void eliminar(Long id);

    TurnoDTO guardar(TurnoRequestDTO turnoDTO);
    TurnoDTO actualizar(Long id, TurnoRequestDTO turnoRequestDTO) throws JsonMappingException;
}

